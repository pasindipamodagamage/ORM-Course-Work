package lk.ijse.Entity.EntityTm;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class SC_CO_Join_Table {
    private String Student_Course_ID;
    private String Student_ID;
    private String Student_Name;
    private String Course_ID;
    private String Course_Name;
    private String Payment_ID;
    private String Payment_date;
    private double Payment_Amount;
}
