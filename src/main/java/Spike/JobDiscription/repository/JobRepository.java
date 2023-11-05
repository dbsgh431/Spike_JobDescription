package Spike.JobDiscription.repository;

import Spike.JobDiscription.entity.Job;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface JobRepository extends CrudRepository<Job, Long> {

    @Override
    List<Job> findAll();
}
