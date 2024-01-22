package Spike.JobDescription.repository;

import Spike.JobDescription.entity.Job;
import Spike.JobDescription.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface JpaJobRepository extends JpaRepository<Job, Long>, JobRepository {

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

    @Override
    Page<Job> findByUser(User user, Pageable pageable);

    @Override
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM job WHERE user_Id = :userId", nativeQuery = true)
    void DeleteByUserId(@Param("userId") Long userId);
}
