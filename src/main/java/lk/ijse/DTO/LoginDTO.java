package lk.ijse.DTO;

import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class LoginDTO {
    private String login;
    private String UserID;
    private Date date;
}
