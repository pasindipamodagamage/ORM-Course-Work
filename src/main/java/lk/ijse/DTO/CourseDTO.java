package lk.ijse.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class CourseDTO {
    private String course_id;
    private String course_name;
    private String duration;
    private double course_fee;
}
