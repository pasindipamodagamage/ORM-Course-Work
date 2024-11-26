package lk.ijse.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Getter
@Setter
public class Student {
    @Id
    private String stu_id;
    private String stu_name;
    private String stu_phone;
    private String stu_email;
    private String stu_address;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Student_Course> studentCourses;

    @ManyToOne
    private User user;

    /*public String getUser_id() {
        return user != null ? user.getUser_id() : null;
    }*/




}