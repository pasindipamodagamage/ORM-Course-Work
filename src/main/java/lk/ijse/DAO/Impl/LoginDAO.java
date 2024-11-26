package lk.ijse.DAO.Impl;

import lk.ijse.DAO.CrudDAO;
import lk.ijse.Entity.Login;

public interface LoginDAO extends CrudDAO<Login> {


    Login getLastLogin();
}
