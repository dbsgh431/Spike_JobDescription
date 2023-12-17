package Spike.JobDescription.dto;


import Spike.JobDescription.converter.entity.CoverLetter;
import Spike.JobDescription.converter.entity.Job;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoverLetterDto {

    private Long id;
    private String title;
    private String content;

    private Long job_id;

    public CoverLetter toEntity(Job job) {
        return new CoverLetter(null, title, content, job);
    }
}
