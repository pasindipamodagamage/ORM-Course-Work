package lk.ijse.DTO;

import lk.ijse.Entity.Student_Course;
import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class PaymentDTO {
    private String pay_id;
    private Date pay_date;
    private double pay_amount;
    private Student_CourseDTO studentCourse;
}
