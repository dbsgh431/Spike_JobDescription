package Spike.JobDescription.api.domain;

import Spike.JobDescription.entity.Job;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Entity
@Data
@AllArgsConstructor
@Slf4j
public class JobDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @Column
    private String title;
    @Column
    private String body;

    public static JobDescription createDescription(JobDescriptionDto dto, Job job) {
        log.info("dto={}", dto);
        if (dto.getId() != null) {
            throw new IllegalArgumentException("이미 존재하는 자격 및 우대사항 입니다.");

        }
        if (dto.getJobId() != job.getId()) {
            throw new IllegalArgumentException("게시한 공고 정보가 다릅니다.");
        }

        return new JobDescription(dto.getId(), job, dto.getTitle(), dto.getBody());
    }
}
