package Spike.JobDescription.service;


import Spike.JobDescription.dto.JobDto;
import Spike.JobDescription.dto.UserDto;
import Spike.JobDescription.entity.Job;
import Spike.JobDescription.entity.User;
import Spike.JobDescription.repository.CoverLetterRepositoryImplJpa;
import Spike.JobDescription.repository.JobRepositoryImplJpa;
import Spike.JobDescription.repository.UserRepositoryImplJpa;
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
    public JobDto create(JobDto dto, User loginUser) {
        User user = userRepository.findById(loginUser.getId()).orElse(null);
        Job job = dto.toEntity(user);
        Job saved = jobRepository.save(job);
        JobDto jobDto = JobDto.builder()
                .id(saved.getId())
                .companyName(saved.getCompanyName())
                .position(saved.getPosition())
                .url(saved.getUrl())
                .isApply(saved.getIsApply())
                .period(saved.getPeriod())
                .userId(saved.getUser().getId()).build();

        return jobDto;
    }

    public JobDto showJob(Long id) {
        Job found = jobRepository.findById(id).orElse(null);

        return JobDto.builder()
                .id(found.getId())
                .companyName(found.getCompanyName())
                .position(found.getPosition())
                .url(found.getUrl())
                .isApply(found.getIsApply())
                .period(found.getPeriod())
                .userId(found.getUser().getId()).build();

    }

    @Transactional
    public JobDto patch(JobDto dto, UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail());
        Job job = dto.toEntity(user);
        Job target = jobRepository.findById(job.getId()).orElse(null);
        if (target != null) {
            target.update(job);
            Job saved = jobRepository.save(target);
            return JobDto.builder()
                    .id(saved.getId())
                    .companyName(saved.getCompanyName())
                    .position(saved.getPosition())
                    .url(saved.getUrl())
                    .isApply(saved.getIsApply())
                    .period(saved.getPeriod())
                    .userId(saved.getUser().getId()).build();
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

    public Page<JobDto> paging(Pageable pageable, User user) {
        int pageNumber = pageable.getPageNumber() - 1; // 0부터 시작하도록
        int pageSize = 20;

        // 정렬 기준은 ID기준으로 내림차순으로 pageSize만큼 가져옴
        Page<Job> jobPages = jobRepository.findByUser(user, PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "id")));

        Page<JobDto> resultDtos = jobPages.
                map(job -> new JobDto(job.getId(),
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
