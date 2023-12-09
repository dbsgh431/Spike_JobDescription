package Spike.JobDiscription.dto;


import Spike.JobDiscription.entity.CoverLetter;
import Spike.JobDiscription.entity.Job;
import Spike.JobDiscription.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoverLetterDto {


    private String title;
    private String content;

    private Long job_id;

    private Long user_id;

    public CoverLetter toEntity(Job job) {
        return new CoverLetter(null, title, content, job, job.getUser());
    }
}
