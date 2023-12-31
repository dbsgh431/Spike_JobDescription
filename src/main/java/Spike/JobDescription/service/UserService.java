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
    public UserDto signUp(UserDto userDto) {
        User toEntity = userDto.toEntity();
        User user = userRepository.findByEmail(toEntity.getEmail());
        if (user != null) {
            return null;
        }
        User saved = userRepository.save(userDto.toEntity());
        return UserDto.builder()
                .id(saved.getId())
                .email(saved.getEmail())
                .password(saved.getPassword())
                .build();
    }

    public UserDto signIn(UserDto userDto) {
        log.info(userDto.toString());
        User toEntity = userDto.toEntity();
        User found = userRepository.findByEmail(toEntity.getEmail());
        if (found == null || !userDto.toEntity().checkPassword(found.getPassword())) {
            return null;
        }
        return UserDto.builder()
                .id(found.getId())
                .email(found.getEmail())
                .password(found.getPassword())
                .build();
    }
}
