package bssm.doorlock.domain.user.facade;

import bssm.doorlock.domain.user.domain.User;
import bssm.doorlock.domain.user.domain.repository.RedisUserRepository;
import bssm.doorlock.domain.user.domain.repository.UserRepository;
import bssm.doorlock.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;
    private final RedisUserRepository userRedisRepository;

    public User getByCode(Long code) {
        return userRepository.findById(code)
                .orElseThrow(UserNotFoundException::new);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getUserByStudentId(String studentId) {
        return userRepository.findByStudentId(studentId)
                .orElseThrow(UserNotFoundException::new);
    }

    public User getCachedUserByCode(long userCode) {
        return userRedisRepository.findById(userCode)
                .orElseGet(() -> {
                    User user = getByCode(userCode);
                    saveCacheUser(user);
                    return user.toUserRedis();
                })
                .toUser();
    }

    public void saveCacheUser(User user) {
        userRedisRepository.save(user.toUserRedis());
    }

}
