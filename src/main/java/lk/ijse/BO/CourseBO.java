package lk.ijse.BO;

import lk.ijse.DTO.CourseDTO;
import lk.ijse.DTO.UserDTO;
import lk.ijse.Entity.Course;

import java.sql.SQLException;
import java.util.List;

public interface CourseBO extends SuperBO{

    public boolean save(CourseDTO dto) throws Exception;

    public boolean update(CourseDTO dto) throws Exception;

    public boolean delete(String ID)throws Exception;

    public String generateNextId() throws SQLException, ClassNotFoundException;

    public List<CourseDTO> getAll() throws SQLException, ClassNotFoundException;

    List<String> getIds();

    public Course searchById(String id) throws SQLException, ClassNotFoundException;

    Course searchByName(String courseName) throws SQLException, ClassNotFoundException;
}
