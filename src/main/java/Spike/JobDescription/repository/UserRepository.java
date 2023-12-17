package Spike.JobDescription.repository;

import Spike.JobDescription.entity.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    User findByEmail(String email);

    Optional<User> findById(Long id);

}
