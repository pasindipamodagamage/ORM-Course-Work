package lk.ijse.DAO.Impl.Custom;

import lk.ijse.DAO.Impl.StudentDAO;
import lk.ijse.Entity.Student;
import lk.ijse.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public boolean save(Student entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }


    @Override
    public boolean delete(String ID) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        Student student = new Student();
        student.setStu_id(ID);
        session.delete(student);
        tx.commit();
        session.close();
        return true;

    }

    @Override
    public List<Student> getAll() throws SQLException, ClassNotFoundException {
        List<Student> all = new ArrayList<>();
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        all = session.createQuery("from Student").list();
        transaction.commit();
        session.close();
        return all;
    }

    @Override
    public Student searchByID(String id) throws SQLException, ClassNotFoundException {

        return null;
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String nextId = "";

        try {
            Object item = session.createQuery("SELECT stu_id FROM Student ORDER BY stu_id DESC").setMaxResults(1).uniqueResult();

            if (item != null) {
                String itemCode = item.toString();


                if (itemCode.startsWith("S") && itemCode.length() > 1) {

                    int idNum = Integer.parseInt(itemCode.substring(1));
                    nextId = "S" + String.format("%03d", ++idNum);
                } else {

                    nextId = "S001";
                }
            } else {
                nextId = "S001";
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
        Session session = null;
        Transaction transaction = null;
        List<String> stu_id = new ArrayList<>();

        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            stu_id = session.createQuery("SELECT s.stu_phone FROM Student s", String.class).list();

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
        return stu_id;
    }

    @Override
    public Student searchByContact(String id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Student student = session.createQuery("FROM Student WHERE stu_phone = :stu_phone", Student.class).setParameter("stu_phone",id)
                .uniqueResult();
        transaction.commit();
        session.close();
        return student;    }
}
