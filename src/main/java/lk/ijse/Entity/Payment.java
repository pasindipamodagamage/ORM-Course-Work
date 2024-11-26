
package lk.ijse.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Payment {
    @Id
    private String pay_id;
    private Date pay_date;
    private double pay_amount;

    @ManyToOne
    @JoinColumn(name = "student_course_id")
    private Student_Course student_course;
}
