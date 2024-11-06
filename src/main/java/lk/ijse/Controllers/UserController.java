package lk.ijse.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.BO.Impl.BOFactory;
import lk.ijse.BO.Impl.UserBOImpl;
import lk.ijse.BO.UserBO;
import lk.ijse.DTO.UserDTO;
import lk.ijse.util.PasswordEncrypt;
import lk.ijse.util.PasswordVerifier;

import java.sql.SQLException;
import java.util.List;

public class UserController {
    @FXML
    private TextField UserID;
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<?> cmbPosition;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhone;

    @FXML
    private TableColumn<?, ?> colPosition;

    @FXML
    private TableColumn<?, ?> colUserID;


    @FXML
    private TableView<UserDTO> tblUsers;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtPhone;

UserBO userBO = (UserBOImpl) BOFactory.getBoFactory().getBo(BOFactory.BoType.User);

    public void initialize(){
        setCellValueFactory();
        loadAll();

        tblUsers.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                UserID.setText(newSelection.getUser_id());
                txtName.setText(newSelection.getUsername());
                txtAddress.setText(newSelection.getAddress());
                txtPhone.setText(newSelection.getUser_phone());
                txtEmail.setText(newSelection.getUser_email());
                cmbPosition.setPromptText(newSelection.getPosition());
                txtPassword.setText(newSelection.getPassword());
            }
        });
    }

    private void setCellValueFactory() {
        colUserID.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("username"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("user_phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("user_email"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("Position"));

    }

    private void loadAll() {
        ObservableList<UserDTO> obList = FXCollections.observableArrayList();
        try {
            List<UserDTO> userDTOList = userBO.getAll();
            for (UserDTO userDTO : userDTOList) {
                UserDTO tm = new UserDTO(
                       userDTO.getUser_id(),
                        userDTO.getUsername(),
                        userDTO.getAddress(),
                        userDTO.getUser_phone(),
                        userDTO.getUser_email(),
                        userDTO.getPosition(),
                        userDTO.getPassword()
                );

                obList.add(tm);
            }

            tblUsers.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        try {
            String id = UserID.getText();
            String name = txtName.getText();
            String address = txtAddress.getText();
            String phone = txtPhone.getText();
            String email = txtEmail.getText();
            String position = String.valueOf(cmbPosition.getValue());
            String password = txtPassword.getText();

            String encryptedPassword = PasswordEncrypt.hashPassword(password);


            if (PasswordVerifier.verifyPassword(password, encryptedPassword)) {
                UserDTO userDTO = new UserDTO(id, name, address, phone, email, position, encryptedPassword);


                boolean isSaved = userBO.save(userDTO);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "User saved successfully!").show();
                    clear();
                    loadAll();

                } else {
                    new Alert(Alert.AlertType.ERROR, "User not saved successfully!").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Password verification failed!").show();
            }
        } catch (Exception e) {

            new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage()).show();
            e.printStackTrace();
        }
    }


    @FXML
    void btnBackOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DashBoard.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnBack.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
    clear();

    }

    private void clear() {
        UserID.clear();
        txtName.clear();
        txtAddress.clear();
        txtPhone.clear();
        txtEmail.clear();
        txtPassword.clear();
        cmbPosition.getSelectionModel().clearSelection();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = UserID.getText();

        try {
            boolean isDeleted = userBO.delete(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "User deleted successfully!").show();
                clear();
                loadAll();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete user!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            String id = UserID.getText();
            String name = txtName.getText();
            String address = txtAddress.getText();
            String phone = txtPhone.getText();
            String email = txtEmail.getText();
            String position = String.valueOf(cmbPosition.getValue());
            String password = txtPassword.getText();

            String encryptedPassword = PasswordEncrypt.hashPassword(password);


            if (PasswordVerifier.verifyPassword(password, encryptedPassword)) {
                UserDTO userDTO = new UserDTO(id, name, address, phone, email, position, encryptedPassword);


                boolean isUpdate = userBO.update(userDTO);
                if (isUpdate) {
                    new Alert(Alert.AlertType.CONFIRMATION, "User Update successfully!").show();
                    clear();
                    loadAll();

                } else {
                    new Alert(Alert.AlertType.ERROR, "User not Update successfully!").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Password update verification failed!").show();
            }
        } catch (Exception e) {

            new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage()).show();
            e.printStackTrace();
        }
    }

    @FXML
    void cmbPositionOnAction(ActionEvent actionEvent) {
    }
}
