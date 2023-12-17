package Spike.JobDescription.service;


import Spike.JobDescription.dto.UserDto;
import Spike.JobDescription.entity.User;
import Spike.JobDescription.repository.UserRepositoryImplJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepositoryImplJpa userRepository;
    @Transactional
    public User signUp(UserDto userDto) {
        User toEntity = userDto.toEntity();
        User user = userRepository.findByEmail(toEntity.getEmail());
        if (user != null) {
            return null;
        }
        return userRepository.save(userDto.toEntity());
    }

    public User signIn(UserDto userDto) {
        log.info(userDto.toString());
        User toEntity = userDto.toEntity();
        User user = userRepository.findByEmail(toEntity.getEmail());
        if (user == null || !userDto.toEntity().checkPassword(user.getPassword())) {
            return null;
        }
        return user;
    }
}
