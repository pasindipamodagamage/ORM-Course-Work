package lk.ijse.Entity.EntityTM;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class StudentCourseJoinTable {
    private String Student_Course_ID;
    private String Student_ID;
    private String Student_Name;
    private String Course_ID;
    private String Course_Name;
    private String Payment_ID;
    private String Payment_Date;
    private double Payment_Amount;
}
