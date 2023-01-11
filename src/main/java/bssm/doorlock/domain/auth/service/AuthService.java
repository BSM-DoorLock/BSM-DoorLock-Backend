package bssm.doorlock.domain.auth.service;

import bssm.doorlock.domain.auth.domain.repository.RefreshTokenRepository;
import bssm.doorlock.domain.auth.exception.AuthCodeNotFoundException;
import bssm.doorlock.domain.auth.exception.InvalidOauthClientException;
import bssm.doorlock.domain.auth.presentation.dto.res.AuthLoginRes;
import bssm.doorlock.domain.user.domain.Student;
import bssm.doorlock.domain.user.domain.User;
import bssm.doorlock.domain.user.domain.UserRole;
import bssm.doorlock.domain.user.domain.repository.StudentRepository;
import bssm.doorlock.domain.user.domain.repository.UserRepository;
import bssm.doorlock.domain.user.exception.StudentNotFoundException;
import bssm.doorlock.global.exception.BadRequestException;
import bssm.doorlock.global.jwt.JwtProvider;
import com.google.common.collect.ImmutableMap;
import leehj050211.bsmOauth.BsmOauth;
import leehj050211.bsmOauth.dto.response.BsmResourceResponse;
import leehj050211.bsmOauth.dto.response.BsmStudentResponse;
import leehj050211.bsmOauth.exceptions.BsmAuthCodeNotFoundException;
import leehj050211.bsmOauth.exceptions.BsmAuthInvalidClientException;
import leehj050211.bsmOauth.exceptions.BsmAuthTokenNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final BsmOauth bsmOauth;
    private final JwtProvider jwtProvider;

    @Value("${token.refresh-token.name}")
    private String REFRESH_TOKEN_NAME;

    public User bsmOauth(String authCode) throws IOException {
        String token;
        BsmResourceResponse resource;
        try {
            token = bsmOauth.getToken(authCode);
            resource = bsmOauth.getResource(token);
        } catch (BsmAuthCodeNotFoundException | BsmAuthTokenNotFoundException e) {
            throw new AuthCodeNotFoundException();
        } catch (BsmAuthInvalidClientException e) {
            throw new InvalidOauthClientException();
        }

        Optional<User> user = userRepository.findById(resource.getUserCode());

        // SignUp
        if (user.isEmpty()) {
            return switch (resource.getRole()) {
                case STUDENT -> studentSignUp(resource, token);
                case TEACHER -> teacherSignUp(resource, token);
            };
        }
        // Update
        return switch (resource.getRole()) {
            case STUDENT -> studentUpdate(resource, user.get());
            case TEACHER -> teacherUpdate(resource, user.get());
        };
    }

    public AuthLoginRes loginPostProcess(HttpServletResponse res, User user) {
        String token = jwtProvider.createAccessToken(user);
        String refreshToken = jwtProvider.createRefreshToken(user.getCode());

        return AuthLoginRes.builder()
                .accessToken(token)
                .refreshToken(refreshToken)
                .build();
    }

    @Transactional
    public void logout(HttpServletRequest req, HttpServletResponse res) {
        String refreshToken = req.getHeader(REFRESH_TOKEN_NAME);
        if (refreshToken == null) {
            throw new BadRequestException(ImmutableMap.<String, String>builder().
                    put("refresh-token", "리프레시 토큰이 없습니다").
                    build()
            );
        }
        try {
            refreshTokenRepository.findById(
                    jwtProvider.getRefreshToken(refreshToken)
            ).ifPresent(token -> token.setAvailable(false));
        } catch (Exception ignored) {}
    }

    @Transactional
    private User studentSignUp(BsmResourceResponse resource, String oauthToken) {
        BsmStudentResponse studentDto = resource.getStudent();
        Student student = studentRepository.findByEnrolledAtAndGradeAndClassNoAndStudentNo(
                studentDto.getEnrolledAt(),
                studentDto.getGrade(),
                studentDto.getClassNo(),
                studentDto.getStudentNo()
        ).orElseThrow(StudentNotFoundException::new);

        User user = User.builder()
                .code(resource.getUserCode())
                .nickname(resource.getNickname())
                .role(UserRole.STUDENT)
                .studentId(student.getStudentId())
                .oauthToken(oauthToken)
                .build();
        return userRepository.save(user);
    }

    @Transactional
    private User teacherSignUp(BsmResourceResponse resource, String oauthToken) {
        User user = User.builder()
                .code(resource.getUserCode())
                .nickname(resource.getNickname())
                .role(UserRole.TEACHER)
                .oauthToken(oauthToken)
                .build();

        return userRepository.save(user);
    }

    @Transactional
    private User studentUpdate(BsmResourceResponse dto, User user) {
        BsmStudentResponse studentDto = dto.getStudent();
        Student student = user.getStudent();
        student.setGrade(studentDto.getGrade());
        student.setClassNo(studentDto.getClassNo());
        student.setStudentNo(studentDto.getStudentNo());
        student.setEnrolledAt(studentDto.getEnrolledAt());
        user.setNickname(dto.getNickname());
        return userRepository.save(user);
    }

    @Transactional
    private User teacherUpdate(BsmResourceResponse dto, User user) {
        user.setNickname(dto.getNickname());
        return userRepository.save(user);
    }

}
