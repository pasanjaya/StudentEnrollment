/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentenrollment.controllers;

import java.io.IOException;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import studentenrollment.UGStudent;
import studentenrollment.UGStudentRecords;

/**
 * FXML Controller class
 *
 * @author bhadr
 */
public class FXMLAdminPanelController {

    @FXML private BorderPane adminpanel;
    @FXML private VBox vbox;
    @FXML private TextField stuid;
    @FXML private TableColumn<UGStudentRecords, Integer> colID;
    @FXML private TableColumn<UGStudentRecords, String> colName;
    @FXML private TableColumn<UGStudentRecords, String> colDOB;
    @FXML private TableColumn<UGStudentRecords, String> colGender;
    @FXML private TableColumn<UGStudentRecords, String> colNIC;
    @FXML private TableColumn<UGStudentRecords, String> colAddress;
    @FXML private TableColumn<UGStudentRecords, String> colIndex;
    @FXML private TableColumn<UGStudentRecords, String> colZscore;
    @FXML private TableColumn<UGStudentRecords, String> colRank;
    @FXML private TableColumn<UGStudentRecords, String> colCourse;
    @FXML private TableColumn<UGStudentRecords, String> colFaculty;
    @FXML private TableView<UGStudentRecords> stuRecordTable;
    
    
    
    

    // create new views for tabs
    
    @FXML
    private void studentView(MouseEvent event) {
        adminpanel.setCenter(vbox);
    }

    @FXML
    private void Course_admin(MouseEvent event) throws IOException{
        loadUIPanels("Course_admin");
    }

    @FXML
    private void Marks_admin(MouseEvent event) throws IOException{
        loadUIPanels("Marks_admin");
    }

    @FXML
    private void HallLabs_admin(MouseEvent event) throws IOException{
        loadUIPanels("HallLab_admin");
    }
    
    @FXML
    private void Academicstaff_admin(MouseEvent event) throws IOException {
        loadUIPanels("Academicstaff_admin");
    }

    
    //logout buttn action
    @FXML
    private void logout_admin(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/studentenrollment/FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }
    
    // load selected uiview
    private void loadUIPanels(String ui) throws IOException{
        Parent pane = null;
        pane = FXMLLoader.load(getClass().getResource("/studentenrollment/fxmls/"+ui+".fxml"));
        adminpanel.setCenter(pane);
            
    }
    // search a student using id
    @FXML
    private void search(ActionEvent event)throws SQLException, ClassNotFoundException {
        if(!stuid.getText().equals("")){
            ObservableList<UGStudentRecords> list = UGStudent.searchStudent(stuid.getText());
            populateTable(list);
            stuid.setText("");
        } else{
            ObservableList<UGStudentRecords> stuList = UGStudent.getRecords();
            populateTable(stuList);
        }
        
    }
    
    //get student object and fill the table 
    @FXML
    private void initialize() throws Exception{
        colID.setCellValueFactory(cellData -> cellData.getValue().getStudentId().asObject());
        colName.setCellValueFactory(cellData -> cellData.getValue().getStudentName());
        colDOB.setCellValueFactory(cellData -> cellData.getValue().getStudentDob());
        colGender.setCellValueFactory(cellData -> cellData.getValue().getStudentGender());
        colNIC.setCellValueFactory(cellData -> cellData.getValue().getStudentNic());
        colAddress.setCellValueFactory(cellData -> cellData.getValue().getStudentAddress());
        colIndex.setCellValueFactory(cellData -> cellData.getValue().getStudentIndex());
        colZscore.setCellValueFactory(cellData -> cellData.getValue().getStudentZscore());
        colRank.setCellValueFactory(cellData -> cellData.getValue().getStudentRank());
        colCourse.setCellValueFactory(cellData -> cellData.getValue().getStudentCourse());
        colFaculty.setCellValueFactory(cellData -> cellData.getValue().getStudentFaculty());
        
        ObservableList<UGStudentRecords> stuList = UGStudent.getRecords();
        populateTable(stuList);
    }

    private void populateTable(ObservableList<UGStudentRecords> stuList) {
        stuRecordTable.setItems(stuList);
    }
    
    //remove student from the db
    @FXML
    private void StudentRemove(ActionEvent event) throws IOException, SQLException, ClassNotFoundException{
        
        try{
            if(!stuid.getText().equals("")){
                UGStudent.deleteStudentbyId(Integer.parseInt(stuid.getText()));
                ObservableList<UGStudentRecords> stuList = UGStudent.getRecords();
                populateTable(stuList);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Done");
                alert.setHeaderText("");
                alert.setContentText("Record successfully deleted!");
                alert.showAndWait();
            }
            
        }catch(SQLException e){
            System.out.println("Error occured while deleting recored " + e);
//            throw e;
        }        
        
    }

    

    
    
}
