package Spike.JobDiscription.repository;

import Spike.JobDiscription.entity.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuestionRepositoryImplJpa extends CrudRepository<Question, Long>, QuestionRepository {
    @Override
    Question save(Question Question);

    @Override
    @Query(value = "SELECT * FROM QUESTION WHERE job_Id = :jobId", nativeQuery = true)
    List<Question> findByJobId(@Param("jobId") Long jobId);

    @Override
    Optional<Question> findById(Long id);
}