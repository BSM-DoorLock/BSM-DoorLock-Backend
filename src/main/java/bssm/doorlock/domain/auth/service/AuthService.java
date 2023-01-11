package bssm.doorlock.domain.auth.service;

import bssm.doorlock.domain.auth.exception.AuthCodeNotFoundException;
import bssm.doorlock.domain.auth.exception.InvalidOauthClientException;
import bssm.doorlock.domain.auth.facade.AuthFacade;
import bssm.doorlock.domain.auth.presentation.dto.res.AuthTokenRes;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthFacade authFacade;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final BsmOauth bsmOauth;
    private final JwtProvider jwtProvider;

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

    public AuthTokenRes loginPostProcess(User user) {
        String token = jwtProvider.createAccessToken(user);
        String refreshToken = jwtProvider.createRefreshToken(user.getCode());

        return AuthTokenRes.builder()
                .accessToken(token)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthTokenRes tokenRefresh(String refreshToken) {
        User user = authFacade.getUserByRefreshToken(
                jwtProvider.getRefreshToken(refreshToken)
        );
        String newToken = jwtProvider.createAccessToken(user);
        return AuthTokenRes.builder()
                .accessToken(newToken)
                .build();
    }

    @Transactional
    public void logout(String refreshToken) {
        if (refreshToken == null) {
            throw new BadRequestException(ImmutableMap.<String, String>builder().
                    put("refresh-token", "리프레시 토큰이 없습니다").
                    build()
            );
        }

        authFacade.getRefreshToken(
                jwtProvider.getRefreshToken(refreshToken)
        ).setAvailable(false);
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
                .name(resource.getStudent().getName())
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
                .name(resource.getTeacher().getName())
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
        user.setName(dto.getStudent().getName());
        return userRepository.save(user);
    }

    @Transactional
    private User teacherUpdate(BsmResourceResponse dto, User user) {
        user.setName(dto.getTeacher().getName());
        return userRepository.save(user);
    }

}
