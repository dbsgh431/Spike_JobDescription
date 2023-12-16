package Spike.JobDiscription.service;


import Spike.JobDiscription.dto.JobDto;
import Spike.JobDiscription.dto.UserDto;
import Spike.JobDiscription.entity.CoverLetter;
import Spike.JobDiscription.entity.Job;
import Spike.JobDiscription.entity.User;
import Spike.JobDiscription.repository.CoverLetterRepositoryImplJpa;
import Spike.JobDiscription.repository.JobRepository;
import Spike.JobDiscription.repository.JobRepositoryImplJpa;
import Spike.JobDiscription.repository.UserRepositoryImplJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class JobService {

    private final JobRepositoryImplJpa jobRepository;
    private final UserRepositoryImplJpa userRepository;

    private final CoverLetterRepositoryImplJpa coverLetterRepository;

    public List<Job> showAll(User loginUser) {
        User user = userRepository.findById(loginUser.getId()).orElse(null);
        List<Job> byUserId = jobRepository.findByUserId(user.getId());
        return byUserId;
    }
    @Transactional
    public Job create(JobDto dto, User loginUser) {
        User user = userRepository.findById(loginUser.getId()).orElse(null);
        Job job = dto.toEntity(user);
        return jobRepository.save(job);
    }

    public Job showJob(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Transactional
    public Job patch(JobDto dto, UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail());
        Job job = dto.toEntity(user);
        Job target = jobRepository.findById(job.getId()).orElse(null);
        if (target != null) {
            target.update(job);
            return jobRepository.save(target);
        }
        return null;
    }
    @Transactional
    public boolean delete(Long id) {
        Job target = jobRepository.findById(id).orElse(null);
        if (target != null) {
            coverLetterRepository.DeleteByJobId(target.getId());

            jobRepository.delete(target);
            return true;
        }
        return false;
    }

    public Page<JobDto> paging(Pageable pageable) {
        int pageNumber = pageable.getPageNumber() - 1;
        int pageSize = 20;

        Page<Job> jobPages = jobRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC,"id")));

        Page<JobDto> resultDtos = jobPages.
                map(job ->
                        new JobDto(job.getId(),
                                job.getCompanyName(),
                                job.getPosition(),
                                job.getUrl(),
                                job.getIsApply(),
                                job.getPeriod(),
                                job.getUser().getId()));

        return resultDtos;
    }

    public boolean isCorrectUser(Long jodId, User loginUser) {
        Job job = jobRepository.findById(jodId).orElse(null);
        log.info("회원={}", loginUser);
        if (job.getUser().equals(loginUser)) {
            return true;
        }
        return false;
    }
}
