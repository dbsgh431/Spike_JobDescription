package Spike.JobDescription.api.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDescriptionDto {

    private Long id;
    private Long jobId;
    private String title;
    private String body;

    public static JobDescriptionDto createJobDescriptionDto(JobDescription jobDescription) {
        return new JobDescriptionDto(
                jobDescription.getId(),
                jobDescription.getJob().getId(),
                jobDescription.getTitle(),
                jobDescription.getBody()
        );
    }
}
