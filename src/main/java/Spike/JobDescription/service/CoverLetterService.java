package Spike.JobDescription.service;


import Spike.JobDescription.dto.CoverLetterDto;
import Spike.JobDescription.dto.UserDto;
import Spike.JobDescription.entity.CoverLetter;
import Spike.JobDescription.entity.Job;
import Spike.JobDescription.entity.User;
import Spike.JobDescription.repository.CoverLetterRepositoryImplJpa;
import Spike.JobDescription.repository.JobRepositoryImplJpa;
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
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new IllegalArgumentException());
        CoverLetter coverLetter = dto.toEntity(job);
        CoverLetter saved = coverLetterRepository.save(coverLetter);
        return saved;
    }
    public CoverLetter show(Long coverLettersId) {
        CoverLetter coverLetter = coverLetterRepository.findById(coverLettersId).orElseThrow(() -> new IllegalArgumentException());
        return  coverLetter;
    }
    @Transactional
    public CoverLetter update(Long jobId, CoverLetterDto dto, Long coverLettersId) {
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new IllegalArgumentException());
        CoverLetter target = coverLetterRepository.findById(coverLettersId).orElseThrow(() -> new IllegalArgumentException());
        target.patch(dto.toEntity(job));
        return coverLetterRepository.save(target);
    }

    @Transactional
    public boolean remove(Long coverLettersId) {
        CoverLetter coverLetter = coverLetterRepository.findById(coverLettersId).orElseThrow(() -> new IllegalArgumentException());
        coverLetterRepository.delete(coverLetter);
        return true;

    }

    public boolean isCorrectUser(Long jobId, UserDto loginUser) {
        Job job = jobRepository.findById(jobId).orElse(null);

        if (job.getUser().equals(loginUser.toEntity())) {
            return true;
        }
        return false;
    }
}
