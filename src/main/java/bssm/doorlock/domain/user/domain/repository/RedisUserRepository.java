package bssm.doorlock.domain.user.domain.repository;

import org.springframework.data.repository.CrudRepository;
import bssm.doorlock.domain.user.domain.UserRedis;

public interface RedisUserRepository extends CrudRepository<UserRedis, Long> {}
