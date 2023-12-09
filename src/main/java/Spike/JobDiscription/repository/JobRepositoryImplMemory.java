package Spike.JobDiscription.repository;

import Spike.JobDiscription.entity.Job;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
public class JobRepositoryImplMemory implements JobRepository {
    private static Map<Long, Job> store = new HashMap<>();

    private long sequence = 0L;

    @Override
    public List<Job> findAll() {
        List<Job> jobs = new ArrayList<>(store.values());
        return jobs;
    }

    @Override
    public List<Job> findByUserId(Long userId) {

        return store.values().stream()
                .filter(job -> job.getUser().getId().equals(userId))
                .toList();
    }

    @Override
    public Job save(Job job) {

        job.setId(++sequence);
        store.put(job.getId(), job);
        return job;
    }

    @Override
    public Optional<Job> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public void delete(Job job) {
        if (store.containsKey(job.getId())) {
            store.remove(job.getId());
        }
    }

    @Override
    public void deleteAll() {
        store.clear();
    }

}
