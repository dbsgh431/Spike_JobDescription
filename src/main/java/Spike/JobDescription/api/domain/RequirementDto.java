package Spike.JobDescription.api.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequirementDto {

    private Long id;
    private Long jobId;
    private String title;
    private String body;

    public static RequirementDto createRequirementDto(Requirement requirement) {
        return new RequirementDto(
                requirement.getId(),
                requirement.getJob().getId(),
                requirement.getTitle(),
                requirement.getBody()
        );
    }
}
