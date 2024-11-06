package lk.ijse.DAO;

import lk.ijse.Entity.Course;
import lk.ijse.Entity.User;

public interface UserDAO extends CrudDAO<User> {
    public User searchByUsername(String username);
}
