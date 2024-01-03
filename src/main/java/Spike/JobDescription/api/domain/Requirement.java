package Spike.JobDescription.api.domain;

import Spike.JobDescription.entity.Job;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class Requirement {

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

    public static Requirement createDescription(RequirementDto dto, Job job) {
        log.info("dto={}", dto);
        if (dto.getId() != null) {
            throw new IllegalArgumentException("이미 존재하는 자격 및 우대사항 입니다.");

        }
        if (dto.getJobId() != job.getId()) {
            throw new IllegalArgumentException("게시한 공고 정보가 다릅니다.");
        }

        return new Requirement(dto.getId(), job, dto.getTitle(), dto.getBody());
    }


    public void patch(RequirementDto dto) {
        // 예외 처리
        if (this.id != dto.getId()) {
            throw new IllegalArgumentException("잘못된 요청 id입니다.");
        }
        // 수정 처리
        if (dto.getTitle() != null) {
            this.title = dto.getTitle();
        }

        if (dto.getBody() != null) {
            this.body = dto.getBody();
        }

    }
}
