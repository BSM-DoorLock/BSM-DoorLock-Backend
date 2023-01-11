package bssm.doorlock.domain.user.facade;

import bssm.doorlock.domain.user.domain.User;
import bssm.doorlock.domain.user.domain.repository.RedisUserRepository;
import bssm.doorlock.domain.user.domain.repository.UserRepository;
import bssm.doorlock.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;
    private final RedisUserRepository userRedisRepository;

    public User getUserByStudentId(String studentId) {
        return userRepository.findByStudentId(studentId)
                .orElseThrow(UserNotFoundException::new);
    }

    public User getCachedUserByCode(long userCode) {
        return userRedisRepository.findById(userCode)
                .orElseThrow(UserNotFoundException::new)
                .toUser();
    }

    public void saveCacheUser(User user) {
        userRedisRepository.save(user.toUserRedis());
    }

}
