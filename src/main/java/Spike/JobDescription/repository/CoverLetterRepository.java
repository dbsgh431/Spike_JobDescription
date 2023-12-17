package Spike.JobDescription.repository;


import Spike.JobDescription.converter.entity.CoverLetter;


import java.util.List;
import java.util.Optional;

public interface CoverLetterRepository {

    CoverLetter save(CoverLetter CoverLetter);

    Optional<CoverLetter> findById(Long id);

    void DeleteByJobId(Long jobId);

    List<CoverLetter> findByJobId(Long jobId);

}
