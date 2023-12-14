package Spike.JobDiscription.dto;


import Spike.JobDiscription.entity.CoverLetter;
import Spike.JobDiscription.entity.Job;
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
