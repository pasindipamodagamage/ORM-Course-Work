package lk.ijse.DAO.Impl.Custom;

import lk.ijse.DAO.Impl.Student_CourseDAO;
import lk.ijse.Entity.Student;
import lk.ijse.Entity.Student_Course;
import lk.ijse.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Student_CourseDAOImpl implements Student_CourseDAO {
    @Override
    public boolean save(Student_Course entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student_Course entity) throws Exception {
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
        Student_Course student_course = new Student_Course();
        student_course.setStudent_course_id(ID);
        session.delete(student_course);
        tx.commit();
        session.close();
        return true;     }

    @Override
    public List<Student_Course> getAll() throws SQLException, ClassNotFoundException {

        List<Student_Course> all = new ArrayList<>();
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        all = session.createQuery("from Student_Course").list();
        transaction.commit();
        session.close();
        return all;
    }

    @Override
    public Student_Course searchByID(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String nextId = "";

        try {
            Object item = session.createQuery("SELECT student_course_id FROM Student_Course ORDER BY student_course_id DESC")
                    .setMaxResults(1)
                    .uniqueResult();

            if (item != null) {
                String itemCode = item.toString();

                if (itemCode.startsWith("SC") && itemCode.length() > 2) {
                    int idNum = Integer.parseInt(itemCode.substring(2));
                    nextId = "SC" + String.format("%03d", ++idNum);
                } else {

                    nextId = "SC001";
                }
            } else {
                nextId = "SC001";
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
}
