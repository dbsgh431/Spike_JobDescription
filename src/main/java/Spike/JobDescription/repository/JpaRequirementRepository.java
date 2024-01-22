package Spike.JobDescription.repository;


import Spike.JobDescription.api.domain.Requirement;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaRequirementRepository extends JpaRepository<Requirement, Long> {

    // 특정 공고의 모든 자격요건 및 우대사항 조회
    @Query(value = "SELECT * FROM requirement WHERE job_id = :jobId", nativeQuery = true)
    List<Requirement> findByJobId(@Param("jobId") Long jobId);


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM requirement WHERE job_id = :jobId", nativeQuery = true)
    void DeleteByJobId(@Param("jobId") Long jobId);
}
