package Spike.JobDescription.dto;

import Spike.JobDescription.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {

    private Long id;
    @Email
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    private String email;
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Size(min = 8, max = 12)
    private String password;

    public User toEntity() {
        return new User(id, email, password);
    }


}
