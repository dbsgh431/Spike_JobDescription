package Spike.JobDiscription.dto;


import Spike.JobDiscription.entity.Job;
import Spike.JobDiscription.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {

    private String question;

    private Long job_id;

    public Question toEntity(Job job) {
        return new Question(null, question, job);
    }
}
