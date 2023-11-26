package Spike.JobDiscription.service;


import Spike.JobDiscription.dto.JobDto;
import Spike.JobDiscription.dto.UserDto;
import Spike.JobDiscription.entity.Job;
import Spike.JobDiscription.entity.User;
import Spike.JobDiscription.repository.JobRepositoryImplJpa;
import Spike.JobDiscription.repository.UserRepositoryImplJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class JobService {

    private final JobRepositoryImplJpa jobRepositoryImplJpa;
    private final UserRepositoryImplJpa userRepositoryImplJpa;

    public List<Job> showAll(User loginUser) {
        User user = userRepositoryImplJpa.findById(loginUser.getId()).orElse(null);
        List<Job> byUserId = jobRepositoryImplJpa.findByUserId(user.getId());
        return byUserId;
    }

    public Job create(JobDto dto, User loginUser) {
        User user = userRepositoryImplJpa.findById(loginUser.getId()).orElse(null);
        Job job = dto.toEntity(user);
        return jobRepositoryImplJpa.save(job);
    }

    public Job showJob(Long id) {
        return jobRepositoryImplJpa.findById(id).orElse(null);
    }

    public Job patch(JobDto dto, UserDto userDto) {
        User user = userRepositoryImplJpa.findByEmail(userDto.getEmail());
        Job target = jobRepositoryImplJpa.findById(dto.toEntity(user).getId()).orElse(null);
        if (target != null) {
            target.update(dto);
            return jobRepositoryImplJpa.save(target);
        }
        return null;
    }

    public boolean delete(Long id) {
        Job target = jobRepositoryImplJpa.findById(id).orElse(null);
        if (target != null) {
            jobRepositoryImplJpa.delete(target);
            return true;
        }
        return false;
    }
}
