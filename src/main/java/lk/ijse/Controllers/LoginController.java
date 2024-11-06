package lk.ijse.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.DAO.UserDAO;
import lk.ijse.Entity.User;
import lk.ijse.util.PasswordVerifier;

public class LoginController {
UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DaoType.User);
    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void handleLoginAction(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        try {
            checkCredential(username, password, event);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An error occurred during login.").show();
        }
    }

    private void checkCredential(String username, String password, ActionEvent event) throws Exception {

        User user = userDAO.searchByUsername(username);

        if (user != null) {

            if (PasswordVerifier.verifyPassword(password, user.getPassword())) {

                new Alert(Alert.AlertType.CONFIRMATION, "Login successful!").show();

                // Assuming a Dashboard.fxml exists for the dashboard scene
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DashBoard.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) btnLogin.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.centerOnScreen();
                stage.show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid password. Please try again.").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "User not found. Please check your username.").show();
        }
    }
}
