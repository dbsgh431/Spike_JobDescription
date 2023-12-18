package Spike.JobDescription.entity;

import jakarta.persistence.*;
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

    @Column(length = 25)
    private String email;

    @Column(length = 12)
    private String password;

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }


}
