package lk.ijse.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class StudentRegisterPlaceDTO {
    private Student_CourseDTO student_course;
    private PaymentDTO student_payment;
}
