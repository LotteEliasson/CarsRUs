package dat3.security.dto;


import lombok.*;
import lombok.NoArgsConstructor;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class UserWithRolesRequest {
    @NonNull
    String username;
    @NonNull
    String password;
    @NonNull
    String email;
}