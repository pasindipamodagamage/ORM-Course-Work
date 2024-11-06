package lk.ijse.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class UserDTO {

    private String user_id;
    private String username;
    private String Address;
    private String user_phone;
    private String user_email;
    private String Position;
    private String password;
}
