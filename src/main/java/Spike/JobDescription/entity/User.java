package Spike.JobDescription.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@Setter // test 시에만 적용
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Column(length = 25)
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Column(length = 12)
    private String password;

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }


}
