package lk.ijse.DAO.Impl;

import lk.ijse.DAO.CrudDAO;
import lk.ijse.Entity.Course;

import java.sql.SQLException;

public interface CourseDAO extends CrudDAO<Course> {
    Course searchByName(String id) throws SQLException, ClassNotFoundException;
}
