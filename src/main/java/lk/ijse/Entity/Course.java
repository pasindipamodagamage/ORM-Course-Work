package lk.ijse.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class Course {
    @Id
    private String course_id;
    private String course_name;
    private String duration;
    private double course_fee;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Student_Course> studentCourses;

    public Course(String courseId, String courseName, String duration, double courseFee) {
        this.course_id = courseId;
        this.course_name = courseName;
        this.duration = duration;
        this.course_fee = courseFee;
    }
}