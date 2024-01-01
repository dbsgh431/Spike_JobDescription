package Spike.JobDescription.repository;

import Spike.JobDescription.api.domain.JobDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobDescriptionRepositoryImplJpa extends JpaRepository<JobDescription, Long> {

    // 특정 공고의 모든 자격요건 및 우대사항 조회
    @Query(value = "SELECT * FROM job_d escription WHERE job_id = :jobId", nativeQuery = true)
    List<JobDescription> findByJobId(@Param("jobId") Long jobId);
}
