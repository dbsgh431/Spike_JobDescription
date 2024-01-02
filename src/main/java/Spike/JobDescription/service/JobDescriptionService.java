package Spike.JobDescription.service;

import Spike.JobDescription.api.domain.JobDescription;
import Spike.JobDescription.api.domain.JobDescriptionDto;
import Spike.JobDescription.entity.Job;
import Spike.JobDescription.repository.JobDescriptionRepositoryImplJpa;
import Spike.JobDescription.repository.JobRepositoryImplJpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import java.util.stream.Collectors;


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
    @Transactional
    public JobDescriptionDto create(JobDescriptionDto jobDescriptionDto, Long jobId) {
        Job findJob = jobRepository.findById(jobId).orElseThrow(() -> new IllegalArgumentException("jobId 오류"));
        JobDescription description = JobDescription.createDescription(jobDescriptionDto, findJob);
        JobDescription save = jobDescriptionRepository.save(description);
        return JobDescriptionDto.createJobDescriptionDto(save);
    }

    // 수정
    @Transactional
    public JobDescriptionDto patch(JobDescriptionDto dto, Long jobId) {
        JobDescription target = jobDescriptionRepository.findById(jobId).orElseThrow(() -> new IllegalArgumentException("잘못된 요청 id입니다."));
        target.patch(dto);
        JobDescription updated = jobDescriptionRepository.save(target);
        return JobDescriptionDto.createJobDescriptionDto(updated);
    }
    // 삭제


}
