package lk.ijse.DAO;

import lk.ijse.DTO.UserDTO;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO {
    public boolean save(T entity) throws Exception;
    public boolean update(T entity) throws Exception;
    public boolean delete(String ID) throws Exception;
    public List<T> getAll() throws SQLException, ClassNotFoundException;
    T searchByIdCustomer(String id) throws SQLException, ClassNotFoundException;
}
