package lk.ijse.DAO.Impl;

import lk.ijse.DAO.CrudDAO;
import lk.ijse.Entity.User;

import java.util.List;

public interface UserDAO extends CrudDAO<User> {
    public User searchByUsername(String username);

    List<String> getUserIds();
}
