package Spike.JobDescription.repository;

import Spike.JobDescription.entity.Job;

import java.util.List;
import java.util.Optional;

public interface JobRepository {

    List<Job> findAll();

    List<Job> findByUserId(Long userId);

    Job save(Job job);

    Optional<Job> findById(Long id);

    void delete(Job job);

    void deleteAll();

}
