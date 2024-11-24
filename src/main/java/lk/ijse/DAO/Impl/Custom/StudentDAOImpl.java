package lk.ijse.DAO.Impl.Custom;

import lk.ijse.DAO.Impl.StudentDAO;
import lk.ijse.Entity.Course;

import java.sql.SQLException;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public boolean save(Course entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(Course entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String ID) throws Exception {
        return false;
    }

    @Override
    public List<Course> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public Course searchByIdCustomer(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
