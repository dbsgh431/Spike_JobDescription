package Spike.JobDiscription.entity;

import Spike.JobDiscription.dto.JobDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String companyName;
    @Column
    private String position;
    @Column
    private String url;
    @Column
    private Boolean isApply;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public void update(JobDto dto) {
        if (this.companyName != null) {
            this.companyName = dto.getCompanyName();
        }
        if (this.position != null) {
            this.position = dto.getPosition();
        }
        if (this.url != null) {
            this.url = dto.getUrl();
        }
        this.isApply = dto.getIsApply();
    }
}
