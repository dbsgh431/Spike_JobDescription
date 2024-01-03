package Spike.JobDescription.repository;

import Spike.JobDescription.entity.CoverLetter;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JpaCoverLetterRepository extends CrudRepository<CoverLetter, Long>, CoverLetterRepository {


    @Override
    CoverLetter save(CoverLetter CoverLetter);

    @Override
    @Query(value = "SELECT * FROM cover_letter WHERE job_Id = :jobId", nativeQuery = true)
    List<CoverLetter> findByJobId(@Param("jobId") Long jobId);

    @Override
    @Modifying
    @Query(value = "DELETE FROM cover_letter WHERE job_Id = :jobId", nativeQuery = true)
    void DeleteByJobId(@Param("jobId") Long jobId);

    @Override
    Optional<CoverLetter> findById(Long id);
}