package lk.ijse.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.sql.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class Login {
    @Id
    private String login;
    private String userID;
    private Date date;
}
