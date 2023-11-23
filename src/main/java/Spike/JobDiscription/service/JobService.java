package Spike.JobDiscription.service;


import Spike.JobDiscription.dto.JobDto;
import Spike.JobDiscription.dto.UserDto;
import Spike.JobDiscription.entity.Job;
import Spike.JobDiscription.entity.User;
import Spike.JobDiscription.repository.JobRepository;
import Spike.JobDiscription.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public List<Job> showAll(User loginUser) {
        User user = userRepository.findById(loginUser.getId()).orElse(null);
        List<Job> byUserId = jobRepository.findByUserId(user.getId());
        return byUserId;
    }

    public Job create(JobDto dto, User loginUser) {
        User user = userRepository.findById(loginUser.getId()).orElse(null);
        Job job = dto.toEntity(user);
        return jobRepository.save(job);
    }

    public Job showJob(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    public Job patch(JobDto dto, UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail());
        Job target = jobRepository.findById(dto.toEntity(user).getId()).orElse(null);
        if (target != null) {
            target.update(dto);
            return jobRepository.save(target);
        }
        return null;
    }

    public boolean delete(Long id) {
        Job target = jobRepository.findById(id).orElse(null);
        if (target != null) {
            jobRepository.delete(target);
            return true;
        }
        return false;
    }
}
