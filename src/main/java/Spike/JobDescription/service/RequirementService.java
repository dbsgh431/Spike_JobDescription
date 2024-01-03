package Spike.JobDescription.service;

import Spike.JobDescription.api.domain.Requirement;
import Spike.JobDescription.api.domain.RequirementDto;
import Spike.JobDescription.entity.Job;
import Spike.JobDescription.repository.JpaRequirementRepository;
import Spike.JobDescription.repository.JpaJobRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import java.util.stream.Collectors;


@Service
public class RequirementService {


    JpaRequirementRepository RequirementRepository;
    JpaJobRepository jobRepository;

    @Autowired
    public RequirementService(JpaRequirementRepository RequirementRepository, JpaJobRepository jobRepository) {
        this.RequirementRepository = RequirementRepository;
        this.jobRepository = jobRepository;
    }

    // 조회
    public List<RequirementDto> showAll(Long jobId) {
        List<Requirement> requirements = RequirementRepository.findByJobId(jobId);
        List<RequirementDto> RequirementDtos = requirements.stream().
                map(Requirement -> RequirementDto.createRequirementDto(Requirement)
                ).collect(Collectors.toList());

        return RequirementDtos;
    }


    // 생성
    @Transactional
    public RequirementDto create(RequirementDto RequirementDto, Long jobId) {
        Job findJob = jobRepository.findById(jobId).orElseThrow(() -> new IllegalArgumentException("jobId 오류"));
        Requirement description = Requirement.createDescription(RequirementDto, findJob);
        Requirement save = RequirementRepository.save(description);
        return RequirementDto.createRequirementDto(save);
    }

    // 수정
    @Transactional
    public RequirementDto patch(RequirementDto dto, Long jdId) {
        Requirement target = RequirementRepository.findById(jdId).orElseThrow(() -> new IllegalArgumentException("잘못된 요청 id입니다. 요청 id : " + jdId));
        target.patch(dto);
        Requirement updated = RequirementRepository.save(target);
        return RequirementDto.createRequirementDto(updated);
    }


    // 삭제
    @Transactional
    public RequirementDto remove(Long jdId) {
        Requirement Requirement = RequirementRepository.findById(jdId).orElseThrow(() -> new IllegalArgumentException("잘못된 요청 id입니다. 요청 id : " + jdId));
        RequirementRepository.delete(Requirement);
        return RequirementDto.createRequirementDto(Requirement);
    }
}
