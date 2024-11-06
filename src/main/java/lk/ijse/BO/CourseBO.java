package lk.ijse.BO;

import lk.ijse.DTO.CourseDTO;
import lk.ijse.Entity.Course;

public interface CourseBO extends SuperBO{

    public boolean save(CourseDTO dto) throws Exception;

    public boolean update(CourseDTO dto) throws Exception;

    public boolean delete(String ID)throws Exception;
}
