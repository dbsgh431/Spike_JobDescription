package Spike.JobDescription.dto;

import Spike.JobDescription.converter.entity.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {

    private Long id;
    private String email;
    private String password;

    public User toEntity() {
        return new User(id, email, password);
    }


}
