package Spike.JobDiscription.repository;

import Spike.JobDiscription.entity.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    User findByEmail(String email);

    Optional<User> findById(Long id);

}
