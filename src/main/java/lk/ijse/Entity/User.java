
package lk.ijse.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity

public class User {
    @Id
    private String user_id;
    private String username;
    private String Address;
    private String user_phone;
    private String user_email;
    private String Position;
    private String password;
}


//
//ALTER TABLE Student_Course
//ADD CONSTRAINT FK_Student_1
//FOREIGN KEY (student_id) REFERENCES Student(stu_id)
//ON DELETE CASCADE
//ON UPDATE CASCADE;
//
//ALTER TABLE Student_Course
//ADD CONSTRAINT FK_Course_1
//FOREIGN KEY (course_id) REFERENCES Course(course_id)
//ON DELETE CASCADE
//ON UPDATE CASCADE;
//
//ALTER TABLE Payment
//ADD CONSTRAINT FK_Student_Course_1
//FOREIGN KEY (student_course_id) REFERENCES Student_Course(student_course_id)
//ON DELETE CASCADE
//ON UPDATE CASCADE;
//
//ALTER TABLE Student
//ADD CONSTRAINT FK_User_1
//FOREIGN KEY (user_id) REFERENCES User(user_id)
//ON DELETE CASCADE
//ON UPDATE CASCADE;
//
//ALTER TABLE Payment
//DROP FOREIGN KEY FK5iv5r015i40pc354k8cpoqvbv,  -- Drop existing foreign key constraint
//ADD CONSTRAINT FK_Student_Course_2
//FOREIGN KEY (student_course_id) REFERENCES Student_Course(student_course_id)
//ON DELETE CASCADE
//ON UPDATE CASCADE;
