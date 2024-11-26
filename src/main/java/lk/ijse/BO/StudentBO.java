package lk.ijse.BO;

import lk.ijse.DTO.StudentDTO;
import lk.ijse.DTO.UserDTO;
import lk.ijse.Entity.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentBO extends SuperBO {
    public boolean save(StudentDTO dto) throws Exception;

    public boolean update(StudentDTO dto) throws Exception;

    public boolean delete(String ID)throws Exception;

    public String generateNextId() throws SQLException, ClassNotFoundException;
    public List<StudentDTO> getAll() throws SQLException, ClassNotFoundException;

    List<String> getIds();

    Student searchByContact(String id) throws SQLException, ClassNotFoundException;
}
