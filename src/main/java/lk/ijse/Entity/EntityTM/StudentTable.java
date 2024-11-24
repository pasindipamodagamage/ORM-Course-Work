package lk.ijse.Entity.EntityTM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentTable {
    private String stu_id;
    private String stu_name;
    private String stu_phone;
    private String stu_email;
    private String stu_address;
    private String user_id;
}
