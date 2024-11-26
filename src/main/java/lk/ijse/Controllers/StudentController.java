package lk.ijse.Controllers;

import javafx.beans.property.SimpleStringProperty;
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
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.Impl.UserBOImpl;
import lk.ijse.BO.StudentBO;
import lk.ijse.BO.UserBO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.DAO.Impl.LoginDAO;
import lk.ijse.DAO.Impl.StudentDAO;
import lk.ijse.DAO.Impl.UserDAO;
import lk.ijse.DTO.StudentDTO;
import lk.ijse.DTO.UserDTO;
import lk.ijse.Entity.Login;
import lk.ijse.Entity.User;
import javafx.scene.input.KeyEvent;
import lk.ijse.util.Regex.Regex;


import java.sql.SQLException;
import java.util.List;

public class StudentController {
    @FXML
    private ComboBox cmbUser;
    @FXML
    private TableColumn <StudentDTO,String> colUserID;
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
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhoneNumber;

    @FXML
    private TableColumn<?, ?> colStudentID;

    @FXML
    private Label lblStudentID;

    @FXML
    private TableView<StudentDTO> tblStudents;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhoneNumber;

    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBo(BOFactory.BoType.Student);
    UserBO userBO = (UserBOImpl) BOFactory.getBoFactory().getBo(BOFactory.BoType.User);
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DaoType.User);
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DaoType.Student);
    LoginDAO loginDAO = (LoginDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DaoType.Login);

    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactory();
        loadAll();
        generateNextId();
        getIds();
        lastLoginID();

       /* lblUserID(id);*/


        tblStudents.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                lblStudentID.setText(newSelection.getStu_id());
                txtName.setText(newSelection.getStu_name());
                txtAddress.setText(newSelection.getStu_address());
                txtPhoneNumber.setText(newSelection.getStu_phone());
                txtEmail.setText(newSelection.getStu_email());
                cmbUser.setValue(newSelection.getUser().getUser_id());
            }
        });
    }

    /*login table eke log una last kenage user id ek aragannw*/
    private void lastLoginID() throws SQLException, ClassNotFoundException {
        Login login = loginDAO.getLastLogin();
        UserID(login.getUserID());

    }
    /*Access denn security ekak danamw*/
    public void UserID(String ID) throws SQLException, ClassNotFoundException {
        String UserID = ID;
        User user = userBO.searchByIdUser(UserID);
        String position = user.getPosition();

        if (position.equals("Admin")) {
            btnBack.setDisable(false);
            btnClear.setDisable(false);
            btnAdd.setDisable(true);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);

        } else if (position.equals("Admissions Coordinator")) {
            btnAdd.setDisable(false);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
            btnBack.setDisable(false);
            btnClear.setDisable(false);
        }
    }
    private void generateNextId() throws SQLException, ClassNotFoundException {
        String code = studentBO.generateNextId();
        lblStudentID.setText(code);
    }

    private void loadAll() {
        ObservableList<StudentDTO> obList = FXCollections.observableArrayList();
        try {
            List<StudentDTO> StudentDTOList = studentBO.getAll();
            for (StudentDTO studentDTO : StudentDTOList) {
                StudentDTO tm = new StudentDTO(
                        studentDTO.getStu_id(),
                        studentDTO.getStu_name(),
                        studentDTO.getStu_phone(),
                        studentDTO.getStu_email(),
                        studentDTO.getStu_address(),
                         new UserDTO(
                                 studentDTO.getUser().getUser_id(),
                                 studentDTO.getUser().getUsername(),
                                 studentDTO.getUser().getAddress(),
                                 studentDTO.getUser().getUser_phone(),
                                 studentDTO.getUser().getUser_email(),
                                 studentDTO.getUser().getPosition(),
                                 studentDTO.getUser().getPassword())
                );


                obList.add(tm);
            }

            tblStudents.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colAddress.setCellValueFactory(new PropertyValueFactory<>("stu_address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("stu_email"));
        colName.setCellValueFactory(new PropertyValueFactory<>("stu_name"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("stu_phone"));
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("stu_id"));

        colUserID.setCellValueFactory(cellData -> {
            StudentDTO student = cellData.getValue();
            return new SimpleStringProperty(
                    student.getUser() != null ? student.getUser().getUser_id() : "N/A"
            );
        });
    }


    @FXML
    void btnAddOnAction(ActionEvent event) throws Exception {
    try {
        String UserID = String.valueOf(cmbUser.getValue());
        String S_id = lblStudentID.getText();
        String S_Name = txtName.getText();
        String Email = txtEmail.getText();
        String phone = txtPhoneNumber.getText();
        String Address = txtAddress.getText();

        User user = userBO.searchByIdUser(UserID);
        UserDTO userDTO = new UserDTO(user.getUser_id(),user.getUsername(),user.getAddress(),user.getUser_phone(),user.getUser_email(),user.getPosition(),user.getPassword());


        StudentDTO studentDTO = new StudentDTO(S_id,S_Name,phone,Email,Address,userDTO);

        if(isValied()){
            boolean isSave = studentBO.save(studentDTO);

            if (isSave){
                new Alert(Alert.AlertType.CONFIRMATION, "User saved successfully!").show();
                clear();
                loadAll();
                generateNextId();

            }
        }
         else {
            new Alert(Alert.AlertType.ERROR, "User not saved successfully!").show();
        }
    
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    }

    private void clear() throws SQLException, ClassNotFoundException {
        txtName.clear();
        txtAddress.clear();
        txtEmail.clear();
        txtPhoneNumber.clear();
        cmbUser.getSelectionModel().clearSelection();
        generateNextId();
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
    void btnClearOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
clear();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
  String S_id = lblStudentID.getText();
        try {
            boolean isDeleted = studentBO.delete(S_id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student deleted successfully!").show();
                clear();
                loadAll();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete Student!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            String UserID = String.valueOf(cmbUser.getValue());
            String S_id = lblStudentID.getText();
            String S_Name = txtName.getText();
            String Email = txtEmail.getText();
            String phone = txtPhoneNumber.getText();
            String Address = txtAddress.getText();

            User user = userBO.searchByIdUser(UserID);
            UserDTO userDTO = new UserDTO(user.getUser_id(),user.getUsername(),user.getAddress(),user.getUser_phone(),user.getUser_email(),user.getPosition(),user.getPassword());


            StudentDTO studentDTO = new StudentDTO(S_id,S_Name,phone,Email,Address,userDTO);

            if (isValied()){
                boolean isSave = studentBO.update(studentDTO);

                if (isSave){
                    new Alert(Alert.AlertType.CONFIRMATION, "User update successfully!").show();
                    clear();
                    loadAll();
                    generateNextId();

                }
            }
             else {
                new Alert(Alert.AlertType.ERROR, "Student not update successfully!").show();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbUserOnAction(ActionEvent actionEvent) {

    }

    private void getIds() throws ClassNotFoundException {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> UID = userBO.getUserIds();

            for (String s : UID) {
                obList.add(s);
            }
            cmbUser.setItems(obList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public boolean isValied(){
        if (!Regex.setTextColor(lk.ijse.util.Regex.TextField.NAME,txtName)) return false;
        if (!Regex.setTextColor(lk.ijse.util.Regex.TextField.ADDRESS,txtAddress)) return false;
        if (!Regex.setTextColor(lk.ijse.util.Regex.TextField.EMAIL,txtEmail)) return false;
        if (!Regex.setTextColor(lk.ijse.util.Regex.TextField.CONTACT,txtPhoneNumber)) return false;

        return true;
    }

    @FXML
    void Address(KeyEvent event) {
        Regex.setTextColor(lk.ijse.util.Regex.TextField.ADDRESS,txtAddress);

    }

    @FXML
    void Email(KeyEvent event) {
        Regex.setTextColor(lk.ijse.util.Regex.TextField.EMAIL,txtEmail);

    }

    @FXML
    void Name(KeyEvent event) {
        Regex.setTextColor(lk.ijse.util.Regex.TextField.NAME,txtName);
    }

    @FXML
    void Phone(KeyEvent event) {
        Regex.setTextColor(lk.ijse.util.Regex.TextField.CONTACT,txtPhoneNumber);
    }

}
