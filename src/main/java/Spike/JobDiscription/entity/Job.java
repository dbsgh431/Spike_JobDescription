package Spike.JobDiscription.entity;

import Spike.JobDiscription.dto.JobDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter // test 시에만 사용
@EqualsAndHashCode
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

    @Column
    private LocalDate period;

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
