/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentenrollment.controllers;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import studentenrollment.UGSubjectRecords;
import studentenrollment.utilities.DBUtil;
import studentenrollment.UGSubject;

/**
 * FXML Controller class
 *
 * @author bhadr
 */


// student enroll to subject
public class FXMLStudentSubjectDetailsController implements Initializable {

    @FXML
    private RadioButton febintake;
    @FXML
    private ToggleGroup intakeToggle;
    @FXML
    private RadioButton julyintake;
    @FXML
    private ComboBox<String> semester;
    @FXML
    private ComboBox<String> subjectcombo;
    @FXML
    private TableView<UGSubjectRecords> subjectTable;
    @FXML
    private TableColumn<UGSubjectRecords, String> colSub;
    @FXML
    private TableColumn<UGSubjectRecords, String> colsubname;
    @FXML
    private TableColumn<UGSubjectRecords, Integer> colsubcost;
    @FXML
    private TextField subidTxt;

    static int studentID;
    String subjID;

    /**
     * Initializes the controller class.
     */
    
    // populate combo box with available subjects according to semester
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        intakeToggle = new ToggleGroup();
        this.febintake.setToggleGroup(intakeToggle);
        this.febintake.setUserData("February");
        this.julyintake.setToggleGroup(intakeToggle);
        this.julyintake.setUserData("July");
        
        colSub.setCellValueFactory(cellData -> cellData.getValue().getSubjectCode());
        colsubname.setCellValueFactory(cellData -> cellData.getValue().getSubjectName());
        colsubcost.setCellValueFactory(cellData -> cellData.getValue().getSubjectCost().asObject());
        
        try{
            ObservableList<UGSubjectRecords> subList = UGSubject.getRecords(studentID);
            populateTable(subList);
        } catch(Exception e){
            System.out.println("Somthin " + e);
        }
        

    }
    
    private void populateTable(ObservableList<UGSubjectRecords> subList) {
        subjectTable.setItems(subList);
    }

    public static void getStuId(int id) {
        studentID = id;
    }
    public void getSubId() throws SQLException, ClassNotFoundException{
        String sql = "select sub_id from ugsubject where sub_name ='"+subjectcombo.getValue()+"' and course_id=(select course_id from ugstudent where stu_id='"+studentID+"');";
        
        try{
            ResultSet rsSet = DBUtil.dbExcute(sql);
            if(rsSet.next()){
                subjID = rsSet.getString("sub_id");
            }
            
        } catch(SQLException e){
            System.out.println("Problem occured when getting subject id " + e);
//            throw e;
        }
    }
    
    
    //enroll to a subject
    @FXML
    private void enrollsub(ActionEvent event) throws IOException, SQLException, ClassNotFoundException{
        getSubId();
        String sql = "insert into ugstudentsubject values('"+studentID+"', '"+subjID+"', 0)";
        
        try{
            DBUtil.dbExcuteQuery(sql);
            ObservableList<UGSubjectRecords> subList = UGSubject.getRecords(studentID);
            populateTable(subList);

            
        } catch(MySQLIntegrityConstraintViolationException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Duplication Error");
            alert.setHeaderText("");
            alert.setContentText("Subject Already Selected!");
            alert.showAndWait();
            
        } catch(SQLException e){
            System.out.println("Problem occurd when inserting into ugstudentsubject " + e);
//            throw e;
        }
    }
    
    // unenroll from a subject

    @FXML
    private void removesub(ActionEvent event) throws SQLException, ClassNotFoundException{
        String sql = "delete from ugstudentsubject where stu_id='"+studentID+"' and sub_id='"+subidTxt.getText()+"';";
        try{
            System.out.println(subidTxt.getText());
            DBUtil.dbExcuteQuery(sql);
            subidTxt.setText("");
            
            ObservableList<UGSubjectRecords> subList = UGSubject.getRecords(studentID);
            populateTable(subList);
        } catch(SQLException e){
            System.out.println("Error occured when deleting subject");
//            throw e;
        }
    }


    ObservableList<String> list = FXCollections.observableArrayList();

    @FXML
    private void febIntakeAction(ActionEvent event) {
        if (semester.getItems() != null) {
            semester.getItems().clear();
        }
        list.addAll("Semester 1", "Semester 2");
        semester.setItems(list);
        semester.setMaxHeight(2);
    }

    @FXML
    private void julyIntakeAction(ActionEvent event) {
        if (semester.getItems() != null) {
            semester.getItems().clear();
        }
        list.add("Semester 2");
        semester.setItems(list);
        semester.setMaxHeight(1);
    }
    // select subjects semester via
    @FXML
    private void getCourseSubjects(ActionEvent event) throws SQLException, ClassNotFoundException {

        ObservableList<String> slist = FXCollections.observableArrayList();

        if (semester.getValue() != null && semester.getValue().equals("Semester 1")) {
            String sql = "select sub_name from ugsubject where sub_id like '%/11%' AND course_id=(select course_id from ugstudent where stu_id='" + studentID + "');";
            try {
                ResultSet rsSet = DBUtil.dbExcute(sql);
                while (rsSet.next()) {
                    String cname = rsSet.getString("sub_name");
                    slist.add(cname);
                }
                subjectcombo.setItems(slist);
            } catch (SQLException e) {
                System.out.println("Problem occurd when retriving course subjects " + e);
            }
        } else if (semester.getValue() != null && semester.getValue().equals("Semester 2")) {
            String sql = "select sub_name from ugsubject where sub_id like '%/12%' AND course_id=(select course_id from ugstudent where stu_id='" + studentID + "');";
            try {
                ResultSet rsSet = DBUtil.dbExcute(sql);
                while (rsSet.next()) {
                    String cname = rsSet.getString("sub_name");
                    slist.add(cname);
                }
                subjectcombo.setItems(slist);
            } catch (SQLException e) {
                System.out.println("Problem occurd when retriving course subjects " + e);
            }
        } else {
            System.out.println(semester.getValue());
        }
    }
    
    //logout from student subject registraion login

    @FXML
    private void logout_reg(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/studentenrollment/FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }

}
