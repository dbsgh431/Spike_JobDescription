package Spike.JobDiscription.entity;

import Spike.JobDiscription.dto.JobDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.convert.PeriodFormat;
import org.springframework.format.annotation.DateTimeFormat;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;




    public void update(Job job) {
        if (job.companyName != null) {
            this.companyName = job.companyName;
        }
        if (job.position != null) {
            this.position = job.position;
        }
        if (job.url != null) {
            this.url = job.url;
        }
        
        this.period = job.period;

        this.isApply = job.isApply;
    }
}
