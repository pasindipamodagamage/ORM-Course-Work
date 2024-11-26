package lk.ijse.DAO.Impl.Custom;

import lk.ijse.DAO.Impl.LoginDAO;
import lk.ijse.Entity.Login;
import lk.ijse.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class LoginDAOImpl implements LoginDAO {
    @Override
    public boolean save(Login entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Login entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String ID) throws Exception {
        return false;
    }

    @Override
    public List<Login> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public Login searchByID(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String nextId = "";

        try {
            Object item = session.createQuery("SELECT login FROM Login ORDER BY login DESC").setMaxResults(1).uniqueResult();

            if (item != null) {
                String itemCode = item.toString();


                if (itemCode.startsWith("L") && itemCode.length() > 1) {

                    int idNum = Integer.parseInt(itemCode.substring(1));
                    nextId = "L" + String.format("%03d", ++idNum);
                } else {

                    nextId = "L001";
                }
            } else {
                nextId = "L001";
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return nextId;

    }


    @Override
    public List<String> getIds() {
        return List.of();
    }


    @Override
    public Login getLastLogin() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Login lastLogin = null;

        try {
            lastLogin = (Login) session.createQuery("FROM Login ORDER BY login DESC")
                    .setMaxResults(1)
                    .uniqueResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return lastLogin;
    }
}
