/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentenrollment.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import studentenrollment.UGSubject;
import studentenrollment.UGSubjectMarks;

/**
 * FXML Controller class
 *
 * @author bhadr
 */

//studten mark contoller
public class Marks_adminController implements Initializable {

    @FXML
    private TableColumn<UGSubjectMarks, String> colsubject;
    @FXML
    private TableColumn<UGSubjectMarks, Integer> colmark;
    @FXML
    private ComboBox<String> semestercombo;
    @FXML
    private TextField studentID;
    @FXML
    private TableView<UGSubjectMarks> subjectmarktbl;
    
    int sem;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        subjectmarktbl.setEditable(true);
        colmark.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    }    
    // get subjects and marks to the table
    @FXML
    private void SearchSubjects(ActionEvent event) {
        if(studentID.getText() != null){
            
            colsubject.setCellValueFactory(cellData -> cellData.getValue().getSubjectName());
            colmark.setCellValueFactory(cellData -> cellData.getValue().getSubjectMark().asObject());
            try{
                ObservableList<UGSubjectMarks> subList = UGSubject.getMarks(Integer.parseInt(studentID.getText()), sem);
                populateTable(subList);
            } catch(Exception e){
                System.out.println("Somthin " + e);
            }
        }
    }

    private void populateTable(ObservableList<UGSubjectMarks> subList) {
        subjectmarktbl.setItems(subList);
    }
    
    //semester selection
    @FXML
    private void getSemester(ActionEvent event) {
        if(semestercombo.getValue() != null && semestercombo.getValue().equals("Semester 1")){
            sem = 1;
        } else if(semestercombo.getValue() != null && semestercombo.getValue().equals("Semester 2")){
            sem = 2;
        }
    }
    
    // update marks that has in the cell
    @FXML
    public void changeMarksCellEvent(CellEditEvent<UGSubjectMarks, Integer> edittedCell) throws SQLException, ClassNotFoundException{
        UGSubjectMarks markSelected = subjectmarktbl.getSelectionModel().getSelectedItem();
        markSelected.SetSubMark(Integer.parseInt(edittedCell.getNewValue().toString()));
        int newMarks = Integer.parseInt(edittedCell.getNewValue().toString());
        UGSubject.setSubjectMarks(Integer.parseInt(studentID.getText()), sem, markSelected.getSubName(), newMarks);
        
        
    }
    
}
