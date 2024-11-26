package lk.ijse.BO.Impl;

import lk.ijse.BO.Student_CourseBO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.DAO.Impl.Student_CourseDAO;
import lk.ijse.DTO.CourseDTO;
import lk.ijse.DTO.StudentDTO;
import lk.ijse.DTO.Student_CourseDTO;
import lk.ijse.DTO.UserDTO;
import lk.ijse.Entity.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Student_CourseBOImpl implements Student_CourseBO {
   Student_CourseDAO studentCourseDAO = (Student_CourseDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DaoType.Student_Course);

   @Override
    public boolean save(Student_CourseDTO dto) throws Exception {
       return studentCourseDAO.save(new Student_Course(
               dto.getStudent_course_id(),
               new Student(
                       dto.getStudent().getStu_id(),
                       dto.getStudent().getStu_name(),
                       dto.getStudent().getStu_phone(),
                       dto.getStudent().getStu_email(),
                       dto.getStudent().getStu_address(),
                       new ArrayList<>(),
                       new User()
               ),
               new Course(
                       dto.getCourse().getCourse_id(),
                       dto.getCourse().getCourse_name(),
                       dto.getCourse().getDuration(),
                       dto.getCourse().getCourse_fee(),
                       new ArrayList<>()
               ),
               dto.getRegistration_date(),
               new ArrayList<>()
       ));
   }

    @Override
    public boolean update(Student_CourseDTO dto) throws Exception {
        return studentCourseDAO.update(new Student_Course(
                dto.getStudent_course_id(),
                new Student(
                        dto.getStudent().getStu_id(),
                        dto.getStudent().getStu_name(),
                        dto.getStudent().getStu_phone(),
                        dto.getStudent().getStu_email(),
                        dto.getStudent().getStu_address(),
                        new ArrayList<>(),
                        new User()
                ),
                new Course(
                        dto.getCourse().getCourse_id(),
                        dto.getCourse().getCourse_name(),
                        dto.getCourse().getDuration(),
                        dto.getCourse().getCourse_fee(),
                        new ArrayList<>()
                ),
                dto.getRegistration_date(),
                new ArrayList<>()
        ));    }

    @Override
    public boolean delete(String ID) throws Exception {
        return studentCourseDAO.delete(ID);
    }
    @Override
    public List<Student_CourseDTO> getAll() throws SQLException, ClassNotFoundException {
        List<Student_Course> SC = studentCourseDAO.getAll();
        List<Student_CourseDTO> dtoList = new ArrayList<>();
        for (Student_Course student_course : SC) {
            Student_CourseDTO dto = new Student_CourseDTO(
                    student_course.getStudent_course_id(),
                    new StudentDTO(
                          student_course.getStudent().getStu_id(),
                          student_course.getStudent().getStu_name(),
                          student_course.getStudent().getStu_phone(),
                          student_course.getStudent().getStu_email(),
                          student_course.getStudent().getStu_address(),
                          new UserDTO()),
                    new CourseDTO(
                            student_course.getCourse().getCourse_id(),
                            student_course.getCourse().getCourse_name(),
                            student_course.getCourse().getDuration(),
                            student_course.getCourse().getCourse_fee()
                    ),
                    student_course.getRegistration_date()
            );
            dtoList.add(dto);
        }
        return dtoList;
   }
    public String generateNextId() throws SQLException, ClassNotFoundException {
        return studentCourseDAO.generateNextId();
    }
}
