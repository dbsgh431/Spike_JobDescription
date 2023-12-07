package Spike.JobDiscription.repository;


import Spike.JobDiscription.entity.Question;


import java.util.List;
import java.util.Optional;

public interface QuestionRepository {

    Question save(Question Question);

    Optional<Question> findById(Long id);


    List<Question> findByJobId(Long jobId);

}
