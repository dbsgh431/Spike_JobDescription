package Spike.JobDiscription.repository;



import Spike.JobDiscription.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositoryImplJpa extends JpaRepository<User, Long>, UserRepository {
    User findByEmail(String email);

    @Override
    Optional<User> findById(Long userId);

    @Override
    User save(User user);
}
