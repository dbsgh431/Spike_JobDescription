package Spike.JobDescription.repository;

import Spike.JobDescription.entity.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemoryUserRepository implements UserRepository{

    private long sequence = 0L;
    private static Map<Long, User> userMap = new HashMap<>();
    @Override
    public User save(User user) {
        user.setId(++sequence);
        userMap.put(user.getId(), user);
        return user;
    }

    @Override
    public User findByEmail(String email) {
        for (User user : userMap.values()) {
            if (user.getEmail() == email) {
                return user;
            }
        }
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        for (User user : userMap.values()) {
            if (user.getId() == id) {
                return Optional.ofNullable(user);
            }
        }
        return Optional.empty();
    }
}
