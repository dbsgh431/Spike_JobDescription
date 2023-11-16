package Spike.JobDiscription.dto;

import Spike.JobDiscription.entity.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {

    private Long userId;
    private String email;
    private String password;

    public User toEntity() {
        return new User(userId, email, password);
    }


}
