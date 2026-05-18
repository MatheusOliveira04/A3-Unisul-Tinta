package git.matheusoliveira04.api.store.models;

import git.matheusoliveira04.api.store.models.dtos.requests.UserRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "users")
public class User {

    @Id
    @Setter
    @Column(name = "us_id")
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, name = "us_name")
    private String name;

    @Column(nullable = false, unique = true, name = "us_email")
    private String email;

    @Column(nullable = false, name = "us_password")
    private String password;

    @Column(nullable = false, name = "us_roles")
    private String roles;

    public User(UserRequest userRequest) {
        this(null, userRequest.name(), userRequest.email(), userRequest.password(), userRequest.roles());
    }
}
