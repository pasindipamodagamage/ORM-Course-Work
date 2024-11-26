package lk.ijse.BO;

import lk.ijse.DTO.LoginDTO;
import lk.ijse.DTO.StudentDTO;

import java.sql.SQLException;

public interface LoginBO extends SuperBO{
    public boolean save(LoginDTO dto) throws Exception;

    String generateNextId() throws SQLException, ClassNotFoundException;
}
