package Spike.JobDiscription.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import static jakarta.persistence.FetchType.LAZY;

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
    @Column(length = 25)
    private String companyName;
    @Column(length = 25)
    private String position;
    @Column
    private String url;
    @Column
    private Boolean isApply;

    @Column
    private LocalDate period;

    @ManyToOne(fetch = LAZY)
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
