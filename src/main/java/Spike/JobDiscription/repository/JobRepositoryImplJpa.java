package Spike.JobDiscription.repository;

import Spike.JobDiscription.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface JobRepositoryImplJpa extends JpaRepository<Job, Long>, JobRepository {

    @Override
    Job save(Job job);

    @Override
    Optional<Job> findById(Long Id);

    @Override
    List<Job> findAll();

    @Override
    void deleteAll();

    @Override
    void delete(Job entity);

    @Override
    @Query(value = "SELECT * FROM job WHERE user_id = :userId", nativeQuery = true)
    List<Job> findByUserId(@Param("userId") Long userId);
}
