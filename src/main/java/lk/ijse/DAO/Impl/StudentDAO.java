package lk.ijse.DAO.Impl;

import lk.ijse.DAO.CrudDAO;
import lk.ijse.DTO.StudentDTO;
import lk.ijse.Entity.Student;

import java.sql.SQLException;

public interface StudentDAO extends CrudDAO<Student> {
    public Student searchByContact(String id) throws SQLException, ClassNotFoundException;
    }
