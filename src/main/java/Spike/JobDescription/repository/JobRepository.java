package Spike.JobDescription.repository;

import Spike.JobDescription.entity.CoverLetter;
import Spike.JobDescription.entity.Job;
import Spike.JobDescription.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JobRepository {

    List<Job> findAll();

    List<Job> findByUserId(Long userId);

    Job save(Job job);

    Optional<Job> findById(Long id);

    void delete(Job job);

    void deleteAll();

    Page<Job> findByUser(User user, Pageable pageable);



    void DeleteByUserId(@Param("userId") Long userId);

}
