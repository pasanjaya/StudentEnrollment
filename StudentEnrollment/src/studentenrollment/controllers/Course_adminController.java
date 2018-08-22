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
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import studentenrollment.UGSubject;

import studentenrollment.ugCourse;
import studentenrollment.utilities.DBUtil;

/**
 * FXML Controller class
 *
 * @author bhadr
 */
public class Course_adminController implements Initializable {

    @FXML
    private TextField coursename;
    @FXML
    private ComboBox<String> facname;
    @FXML
    private ComboBox<String> coursetype;
    @FXML
    private TextField subjectId;
    @FXML
    private TextField subjectname;
    @FXML
    private TextField credits;
    @FXML
    private TextField price;
    @FXML
    private ComboBox<String> coursenamecombo;
    @FXML
    private ComboBox<String> sub_facname;
    @FXML
    private ComboBox<String> sub_coursetype;
    
    int course_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private boolean isFilledCourseAll(){
        if(!coursename.getText().equals("")){
            return true;
        }
        return false;
    }
    //check is all filled
    private boolean isFilledSubjectAll(){
        if(!subjectId.getText().equals("") && !subjectname.getText().equals("")
                && !credits.getText().equals("") && !price.getText().equals("")){
            return true;
        }
        return false;
    }
    //clear
    @FXML
    private void clear(ActionEvent event) {
        coursename.setText("");
        subjectId.setText("");
        subjectname.setText("");
        credits.setText("");
        price.setText("");
    }
    //add course controller method
    @FXML
    private void addCourse(ActionEvent event)throws IOException, SQLException, ClassNotFoundException {
        if(isFilledCourseAll()){
            ugCourse.insertCourse(coursename.getText(), facname.getValue(), coursetype.getValue());
            coursename.setText("");

        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("");
            alert.setContentText("Please add course name");
            alert.showAndWait();
        }

    }
    //retrive course id from db
    private void getCourseId() throws SQLException, ClassNotFoundException{
        String sql = "select course_id from ugcourse where course_name = '"+coursenamecombo.getValue()+"'";
        
        try{
            ResultSet rsSet = DBUtil.dbExcute(sql);
            if(rsSet.next()){
                course_id = rsSet.getInt("course_id");
            }
            
        } catch(SQLException e){
            System.out.println("Problem occurd when getting courseId");
        }
            
    }
    // fill with new subject details
    @FXML
    private void addSubject(ActionEvent event) throws SQLException, ClassNotFoundException {
        getCourseId();
        if(isFilledSubjectAll()){
            UGSubject.insertSubjects(subjectId.getText(), subjectname.getText(),
                    Integer.parseInt(credits.getText()), Integer.parseInt(price.getText()), course_id, sub_coursetype.getValue());
            
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION");
            alert.setHeaderText("");
            alert.setContentText("Subject added Successfully!");
            alert.showAndWait();
            
            // clearing text box
            subjectId.setText("");
            subjectname.setText("");
            credits.setText("");
            price.setText("");
            
            

        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("");
            alert.setContentText("Please insert subject details");
            alert.showAndWait();
        }

    }

    @FXML
    private void getSubjectLists(ActionEvent event) throws SQLException, ClassNotFoundException {
        ObservableList<String> list = FXCollections.observableArrayList();
        
        if(sub_coursetype.getValue().equals("Undergraduate")){
            String sql = "select course_name from ugcourse where faculty_name = '"+sub_facname.getValue()+"'";
            try{
                ResultSet rsSet = DBUtil.dbExcute(sql);
                while(rsSet.next()){
                    String course_name = rsSet.getString("course_name");
                    list.add(course_name);
                }
                coursenamecombo.setItems(list);
                
            }catch(SQLException e){
                System.out.println("Problem occured when filling subject list");
//                throw e;
            }
        } else if(sub_coursetype.getValue().equals("Postgraduate")){
            System.out.println(sub_coursetype.getValue() + " Post AddSub");
        } else{
            System.out.println(sub_coursetype.getValue() + " Error!!!! AddSub");
        }
    }

}
