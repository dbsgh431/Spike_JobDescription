package Spike.JobDiscription.service;


import Spike.JobDiscription.dto.JobDto;
import Spike.JobDiscription.entity.Job;
import Spike.JobDiscription.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    public List<Job> showAll() {
        return jobRepository.findAll();
    }

    public Job create(JobDto dto) {
        return jobRepository.save(dto.toEntity());
    }

    public Job showJob(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    public Job patch(JobDto dto) {
        Job target = jobRepository.findById(dto.toEntity().getId()).orElse(null);
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
