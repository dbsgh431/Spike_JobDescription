package Spike.JobDescription.converter.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "기업명은 필수 입력 값입니다.")
    @Column(length = 25)
    private String companyName;
    @NotBlank(message = "직무는 필수 입력 값입니다.")
    @Column(length = 25)
    private String position;

    @NotBlank(message = "페이지 주소는 필수 입력 값입니다.")
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
