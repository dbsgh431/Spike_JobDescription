package Spike.JobDiscription.dto;


import Spike.JobDiscription.entity.Job;
import Spike.JobDiscription.entity.User;
import lombok.*;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class JobDto {
    private Long id;
    private String companyName;
    private String position;
    private String url;

    private Boolean isApply;

    private LocalDate period;

    private Long userId;

    public Job toEntity(User user) {

        return new Job(id, companyName, position, url, isApply, period, user);
    }
}
