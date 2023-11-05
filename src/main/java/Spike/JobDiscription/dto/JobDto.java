package Spike.JobDiscription.dto;


import Spike.JobDiscription.entity.Job;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class JobDto {

    private String companyName;
    private String role;
    private String url;

    private Boolean isApply;

    public Job toEntity() {
        return new Job(null, companyName, role, url, isApply);
    }
}
