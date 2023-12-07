package Spike.JobDiscription.service;


import Spike.JobDiscription.dto.QuestionDto;
import Spike.JobDiscription.entity.Job;
import Spike.JobDiscription.entity.Question;
import Spike.JobDiscription.repository.JobRepositoryImplJpa;
import Spike.JobDiscription.repository.QuestionRepositoryImplJpa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepositoryImplJpa questionRepository;
    private final JobRepositoryImplJpa jobRepository;

    public List<Question> showAll(Long job_id) {
        List<Question> questions = questionRepository.findByJobId(job_id);
        return questions;
    }

    public Question create(QuestionDto dto, Long jobId) {
        Job job = jobRepository.findById(jobId).orElse(null);
        Question question = dto.toEntity(job);
        Question saved = questionRepository.save(question);
        return saved;
    }
}
