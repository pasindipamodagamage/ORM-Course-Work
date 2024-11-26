package lk.ijse.BO.Impl;

import lk.ijse.BO.LoginBO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.DAO.Impl.LoginDAO;
import lk.ijse.DTO.LoginDTO;
import lk.ijse.Entity.Login;

import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {
    LoginDAO loginDAO = (LoginDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DaoType.Login);

    @Override
    public boolean save(LoginDTO dto) throws Exception {
        return loginDAO.save(new Login(dto.getLogin(),dto.getUserID(),dto.getDate()));
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        return loginDAO.generateNextId();
    }
}
