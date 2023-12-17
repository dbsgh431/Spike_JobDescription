package Spike.JobDescription.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CoverLetter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "항목 이름은 필수 입력 값입니다.")
    @Column(length = 50)
    private String title;

    @NotBlank(message = "자기소개서 내용은 필수 입력 값입니다.")
    @Column(length = 1500)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Job job;

    public void patch(CoverLetter coverLetter) {
        if (coverLetter.getTitle() != null) {
            this.title = coverLetter.getTitle();
        }
        if (coverLetter.getContent() != null) {
            this.content = coverLetter.getContent();
        }

    }
}
