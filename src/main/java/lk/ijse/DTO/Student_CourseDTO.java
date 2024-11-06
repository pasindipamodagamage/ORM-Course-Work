package lk.ijse.DTO;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data

public class Student_CourseDTO {
    private String student_course_id;
    private Date registration_date;
}
