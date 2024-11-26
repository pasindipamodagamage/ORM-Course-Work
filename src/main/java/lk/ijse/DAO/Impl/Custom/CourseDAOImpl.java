package lk.ijse.DAO.Impl.Custom;

import lk.ijse.DAO.Impl.CourseDAO;
import lk.ijse.Entity.Course;
import lk.ijse.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {
    @Override
    public boolean save(Course entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
        return true;

    }

    @Override
    public boolean update(Course entity) throws Exception {
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
        Course course = new Course();
        course.setCourse_id(ID);
        session.delete(course);
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public List<Course> getAll() throws SQLException, ClassNotFoundException {
        List<Course> all = new ArrayList<>();
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        all = session.createQuery("from Course").list();
        transaction.commit();
        session.close();
        return all;
    }

    @Override
    public Course searchByName(String id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Course course = session.createQuery("FROM Course WHERE course_name = :course_name", Course.class).setParameter("course_name",id)
                .uniqueResult();
        transaction.commit();
        session.close();
        return course;
    }
    @Override
    public Course searchByID(String id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Course course = session.createQuery("FROM Course WHERE course_id = :course_id", Course.class).setParameter("course_id",id)
                .uniqueResult();
        transaction.commit();
        session.close();
        return course;
    }
    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String nextId = "";

        try {
            Object item = session.createQuery("SELECT course_id FROM Course ORDER BY course_id DESC").setMaxResults(1).uniqueResult();

            if (item != null) {
                String itemCode = item.toString();


                if (itemCode.startsWith("C") && itemCode.length() > 1) {

                    int idNum = Integer.parseInt(itemCode.substring(1));
                    nextId = "C" + String.format("%03d", ++idNum);
                } else {

                    nextId = "C001";
                }
            } else {
                nextId = "C001";
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
        List<String> courseIds = new ArrayList<>();

        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            courseIds = session.createQuery("SELECT c.course_name FROM Course c", String.class).list();

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
        return courseIds;
    }

}
