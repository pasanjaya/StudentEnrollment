/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentenrollment.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import studentenrollment.UGStudent;
import studentenrollment.utilities.DBUtil;

/**
 * FXML Controller class
 *
 * @author bhadr
 */
//undergraduate registration control
public class FXMLUGRegistrationController implements Initializable {

    @FXML private AnchorPane ugregistration;
    @FXML private ToggleGroup gender, syllabus;
    @FXML private RadioButton male, female, local, london;
    @FXML private TextField nameIn, fullname, nic, address, telephone;
    @FXML private DatePicker dob;
    @FXML private TextField year, index, zscore, rank;
    @FXML private TextField subject1, subject2, subject3; 
    @FXML private TextField grade1, grade2, grade3;
    @FXML private ComboBox<String> facname;
    @FXML private ComboBox<String> coursename;
    
   
    //retrive faculty courses from db
    @FXML
    public void getFacultyCourses(ActionEvent event) throws SQLException, ClassNotFoundException{
        
        ObservableList<String> list = FXCollections.observableArrayList();
        String sql = "select course_name from ugcourse where faculty_name ='SCHOOL OF "+facname.getValue()+"';";
        
        try {
            ResultSet rsSet = DBUtil.dbExcute(sql);
            while(rsSet.next()){
                String cname = rsSet.getString("course_name");
                list.add(cname); 
            }
            coursename.setItems(list);
        } catch(SQLException e){
            System.out.println("Problem occurd when retriving faculty courses " + e);
        }
    }
    
    // get course id from the selected faculty
    int courseId;
    public void getCourseId() throws SQLException, ClassNotFoundException {
        String sql = "select max(course_id) from ugcourse "
                + "where faculty_name ='SCHOOL OF "+facname.getValue()+"' and course_name = '"+coursename.getValue()+"';";
        
        try {
            ResultSet rsSet = DBUtil.dbExcute(sql);
            rsSet.last();
            courseId = rsSet.getInt(1);
        } catch(SQLException e){
            System.out.println("Problem occurd when retriving course ID " + e);
//            throw e;
        }
    }
    
    
    // validation check
    private boolean isFilledAll(){
        if(gender.getSelectedToggle() != null && syllabus.getSelectedToggle() != null &&
                !nameIn.getText().equals("") && !fullname.getText().equals("") && 
                !nic.getText().equals("") && !address.getText().equals("") && 
                !telephone.getText().equals("") && dob.getValue() != null && 
                !year.getText().equals("") && !index.getText().equals("") &&
                !zscore.getText().equals("") && !rank.getText().equals("") &&
                !subject1.getText().equals("") && !subject2.getText().equals("") &&
                !subject3.getText().equals("") && !grade1.getText().equals("") &&
                !grade2.getText().equals("") && !grade3.getText().equals("")){
            return true;
        }
        return false;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gender = new ToggleGroup();
        this.male.setToggleGroup(gender);
        this.male.setUserData("Male");
        this.female.setToggleGroup(gender);
        this.female.setUserData("Female");
        
        syllabus = new ToggleGroup();
        local.setToggleGroup(syllabus);
        local.setUserData("Local");
        london.setToggleGroup(syllabus);
        london.setUserData("London");
    }
    // cancel btn get to the home login
    @FXML
    private void cancelBtn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/studentenrollment/FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }
    // next btn get you to account creating pane
    @FXML
    private void nextBtn(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        getCourseId();
        if(isFilledAll()){
            UGStudent.insertStudent(nameIn.getText(), fullname.getText(), dob.getValue().toString(), gender.getSelectedToggle().getUserData().toString(),
                nic.getText(), address.getText(), year.getText(), syllabus.getSelectedToggle().getUserData().toString(), index.getText(), 
                zscore.getText(), rank.getText(), subject1.getText(), grade1.getText(), subject2.getText(), 
                grade2.getText(), subject3.getText(), grade3.getText(), courseId);
            
            
            Parent create_account = FXMLLoader.load(getClass().getResource("/studentenrollment/fxmls/FXMLUGCreateAccount.fxml"));
            Scene scene = new Scene(create_account);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Unfinished");
            alert.setHeaderText("");
            alert.setContentText("Please complete all fields");
            alert.showAndWait();
        }
    }

}
