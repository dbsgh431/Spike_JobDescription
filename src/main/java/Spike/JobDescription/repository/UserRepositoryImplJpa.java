package Spike.JobDescription.repository;



import Spike.JobDescription.converter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositoryImplJpa extends JpaRepository<User, Long>, UserRepository {
    User findByEmail(String email);

    @Override
    Optional<User> findById(Long userId);

    @Override
    User save(User user);
}
