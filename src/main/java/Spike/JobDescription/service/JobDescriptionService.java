package Spike.JobDescription.service;

import Spike.JobDescription.api.domain.JobDescription;
import Spike.JobDescription.api.domain.JobDescriptionDto;
import Spike.JobDescription.entity.Job;
import Spike.JobDescription.repository.JobDescriptionRepositoryImplJpa;
import Spike.JobDescription.repository.JobRepositoryImplJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class JobDescriptionService {


    JobDescriptionRepositoryImplJpa jobDescriptionRepository;

    JobRepositoryImplJpa jobRepository;

    @Autowired
    public JobDescriptionService(JobDescriptionRepositoryImplJpa jobDescriptionRepository, JobRepositoryImplJpa jobRepository) {
        this.jobDescriptionRepository = jobDescriptionRepository;
        this.jobRepository = jobRepository;
    }
    // 조회
    public List<JobDescriptionDto> showAll(Long jobId) {
        List<JobDescription> jobDescriptions = jobDescriptionRepository.findByJobId(jobId);
        List<JobDescriptionDto> jobDescriptionDtos = jobDescriptions.stream().
                map(jobDescription -> JobDescriptionDto.createJobDescriptionDto(jobDescription)
                ).collect(Collectors.toList());

        return jobDescriptionDtos;
    }

    // 생성
    public JobDescriptionDto create(JobDescriptionDto jobDescriptionDto, Long jobId) {
        Job findJob = jobRepository.findById(jobId).orElseThrow(() -> new IllegalArgumentException("jobId 오류"));
        JobDescription description = JobDescription.createDescription(jobDescriptionDto, findJob);
        JobDescription save = jobDescriptionRepository.save(description);
        return JobDescriptionDto.createJobDescriptionDto(save);
    }
    // 수정
    // 삭제


}
