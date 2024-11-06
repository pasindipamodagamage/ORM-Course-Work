package lk.ijse.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Getter
@Setter
public class Student_Course {
    @Id
    private String student_course_id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private Date registration_date;

    @OneToMany(mappedBy = "student_course", cascade = CascadeType.ALL)
    private List<Payment> payments;
}

/*Student register join query*/