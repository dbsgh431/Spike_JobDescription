package Spike.JobDiscription.repository;

import Spike.JobDiscription.entity.Job;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface JobRepository extends CrudRepository<Job, Long> {

    @Override
    List<Job> findAll();

    @Query(value = "SELECT * FROM job WHERE user_id = :userId", nativeQuery = true)
    List<Job> findByUserId(@Param("userId") Long userId);

}
