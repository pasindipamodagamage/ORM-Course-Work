package lk.ijse.DAO.Impl.Custom;

import lk.ijse.DAO.Impl.UserDAO;
import lk.ijse.Entity.User;
import lk.ijse.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean save(User entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
        return true;
        }

    @Override
    public boolean update(User entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.update(entity);
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String ID) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        User user = new User();
        user.setUser_id(ID);
        session.delete(user);
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException {
        List<User> all = new ArrayList<>();
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        all = session.createQuery("from User").list();
        transaction.commit();
        session.close();
        return all;
    }

    @Override
    public User searchByID(String id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, id);
        transaction.commit();
        session.close();
        return user;
    }
    @Override
    public User searchByUsername(String username) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        User user = session.createQuery("FROM User WHERE username = :username", User.class).setParameter("username", username)
                .uniqueResult();
        transaction.commit();
        session.close();
        return user;
    }


    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String nextId = "";

        try {
            Object item = session.createQuery("SELECT user_id FROM User ORDER BY user_id DESC").setMaxResults(1).uniqueResult();

            if (item != null) {
                String itemCode = item.toString();


                if (itemCode.startsWith("U") && itemCode.length() > 1) {

                    int idNum = Integer.parseInt(itemCode.substring(1));
                    nextId = "U" + String.format("%03d", ++idNum);
                } else {

                    nextId = "U001";
                }
            } else {
                nextId = "U001";
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
    public List<String> getUserIds() {
        Session session = null;
        Transaction transaction = null;
        List<String> userIds = new ArrayList<>();

        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            userIds = session.createQuery("SELECT u.user_id FROM User u", String.class).list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return userIds;
    }

}
