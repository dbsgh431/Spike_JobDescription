package Spike.JobDiscription.repository;

import Spike.JobDiscription.entity.CoverLetter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CoverLetterRepositoryImplJpa extends CrudRepository<CoverLetter, Long>, CoverLetterRepository {


    @Override
    CoverLetter save(CoverLetter CoverLetter);

    @Override
    @Query(value = "SELECT * FROM COVER_LETTER WHERE job_Id = :jobId", nativeQuery = true)
    List<CoverLetter> findByJobId(@Param("jobId") Long jobId);

    @Override
    @Query(value = "DELETE FROM COVER_LETTER WHERE job_Id = :jobId", nativeQuery = true)
    void deleteByJobId(@Param("jobId") Long jobId);

    @Override
    Optional<CoverLetter> findById(Long id);
}