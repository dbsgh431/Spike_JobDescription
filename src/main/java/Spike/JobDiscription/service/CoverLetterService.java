package Spike.JobDiscription.service;


import Spike.JobDiscription.dto.CoverLetterDto;
import Spike.JobDiscription.entity.CoverLetter;
import Spike.JobDiscription.entity.Job;
import Spike.JobDiscription.entity.User;
import Spike.JobDiscription.repository.CoverLetterRepositoryImplJpa;
import Spike.JobDiscription.repository.JobRepositoryImplJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CoverLetterService {

    private final CoverLetterRepositoryImplJpa coverLetterRepository;
    private final JobRepositoryImplJpa jobRepository;

    public List<CoverLetter> showAll(Long job_id) {
        List<CoverLetter> coverLetters = coverLetterRepository.findByJobId(job_id);
        return coverLetters;
    }

    @Transactional
    public CoverLetter create(CoverLetterDto dto, Long jobId) {
        Job job = jobRepository.findById(jobId).orElse(null);
        CoverLetter coverLetter = dto.toEntity(job);
        CoverLetter saved = coverLetterRepository.save(coverLetter);
        return saved;
    }

    public boolean remove(Long coverLettersId) {
        CoverLetter coverLetter = coverLetterRepository.findById(coverLettersId).orElse(null);
        if (coverLetter != null) {
            coverLetterRepository.delete(coverLetter);
            return true;
        }
        return false;
    }

    public boolean checkUser(Long jobId, User loginUser) {
        Job job = jobRepository.findById(jobId).orElse(null);
        log.info("회원={}", loginUser);
        if (job.getUser().equals(loginUser)) {
            return true;
        }
        return false;
    }
}
