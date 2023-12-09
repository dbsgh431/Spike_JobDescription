package Spike.JobDiscription.repository;


import Spike.JobDiscription.entity.CoverLetter;


import java.util.List;
import java.util.Optional;

public interface CoverLetterRepository {

    CoverLetter save(CoverLetter CoverLetter);

    Optional<CoverLetter> findById(Long id);


    List<CoverLetter> findByJobId(Long jobId);

    void deleteByJobId(Long jobId);

}
