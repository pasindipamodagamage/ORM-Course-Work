package lk.ijse.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.DAO.Impl.LoginDAO;
import lk.ijse.Entity.Login;

import java.sql.SQLException;

public class DashBoardController {

    public Label LblUserID;
    @FXML
    private Button btnCourse;
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnPayment;
    @FXML
    private Button btnStudent;
    @FXML
    private Button btnStudentRegister;
    @FXML
    private Button btnUsers;

    LoginDAO loginDAO = (LoginDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DaoType.Login);
    public void initialize() throws SQLException, ClassNotFoundException {
        lastLoginID();
    }

    private void lastLoginID() {
        Login login = loginDAO.getLastLogin();
        System.out.println(login.getUserID());
        LblUserID.setText(login.getUserID());
    }


    @FXML
    void btnCourseOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CoursePage.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnCourse.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/loginPage.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnLogout.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PaymentHistoryPage.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnPayment.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnStudentOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/StudentPage.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnStudent.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnStudentRegisterOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Student_Course.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnStudentRegister.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnUsersOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/User.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnUsers.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
