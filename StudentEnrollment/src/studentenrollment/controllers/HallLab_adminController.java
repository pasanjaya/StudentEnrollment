/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentenrollment.controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import studentenrollment.UGSubjectLabs;
import studentenrollment.utilities.DBUtil;

/**
 * FXML Controller class
 *
 * @author bhadr
 */
public class HallLab_adminController implements Initializable {

    

    @FXML
    private ComboBox<String> coursetype;
    @FXML
    private ComboBox<String> semester;
    @FXML
    private ComboBox<String> coursename;
    @FXML
    private TableView<UGSubjectLabs> subjectInst_tbl;
    @FXML
    private TableColumn<UGSubjectLabs, String> subjectidcol;
    @FXML
    private TableColumn<UGSubjectLabs, String> subjectnamecol;
    @FXML
    private TableColumn<UGSubjectLabs, Integer> int_idcol;
    @FXML
    private TableColumn<UGSubjectLabs, String> lab_idcol;
    @FXML
    private ComboBox<String> faculty;
    
    int sem;
    int course_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        subjectInst_tbl.setEditable(true);
        int_idcol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        lab_idcol.setCellFactory(TextFieldTableCell.forTableColumn());
    }



    //retrive course id from db
    private void getCourseId() throws SQLException, ClassNotFoundException{
        String sql = "select course_id from ugcourse where course_name = '"+coursename.getValue()+"'";
        
        try{
            ResultSet rsSet = DBUtil.dbExcute(sql);
            if(rsSet.next()){
                course_id = rsSet.getInt("course_id");
            }
            
        } catch(SQLException e){
            System.out.println("Problem occurd when getting courseId");
        }
            
    }
    
    //fill the observble list with subject object
    private static ObservableList<UGSubjectLabs> getSubjectObjects(ResultSet rset) throws SQLException, ClassNotFoundException {
         ObservableList<UGSubjectLabs> subList = FXCollections.observableArrayList();
         try{
             while(rset.next()){
                 UGSubjectLabs sub = new UGSubjectLabs();
                 sub.SetSubid(rset.getString("sub_id"));
                 sub.SetSubName(rset.getString("sub_name"));
                 sub.SetIntrucId(rset.getInt("int_id"));
                 sub.SetLabId(rset.getString("lab_id"));
                 subList.add(sub);
             }
             return subList;
         }catch(SQLException e){
             System.out.println("Problem occured when filling observle arry with subjectlb objects");
             throw e;
         }
    }
    
    //get subject names from db
    private static ObservableList<UGSubjectLabs> getSubjects(int courseid, int semester) throws SQLException, ClassNotFoundException {
        String sql = "";
        switch (semester) {
            case 0:
                sql = "select ugsub.sub_id, ugsub.sub_name, insub.int_id, inlab.lab_id from ugsubject as ugsub left join instructorsubject as insub on ugsub.sub_id=insub.sub_id left join instructorlab as inlab on inlab.int_id = insub.int_id where ugsub.course_id = '"+courseid+"'";
                break;
            case 1:
            case 2:
                sql = "select ugsub.sub_id, ugsub.sub_name, insub.int_id, inlab.lab_id from ugsubject as ugsub left join instructorsubject as insub on ugsub.sub_id=insub.sub_id left join instructorlab as inlab on inlab.int_id = insub.int_id where ugsub.course_id = '"+courseid+"' and ugsub.sub_id like '%/_"+semester+"%';";
                break;
            default:
                System.out.println("Error occured in semester");
                break;
        }
        try{
            ResultSet rset = DBUtil.dbExcute(sql);
            ObservableList<UGSubjectLabs> sublist = getSubjectObjects(rset);
            return sublist;
        } catch(SQLException e){
            System.out.println("Error occured while retriving data ");
            throw e;
        }
    }
    // get semestr using selection combo
    @FXML
    private void getSemester(ActionEvent event) {
        if(semester.getValue() != null && semester.getValue().equals("Semester 1")){
            sem = 1;
        } else if(semester.getValue() != null && semester.getValue().equals("Semester 2")){
            sem = 2;
        }
    }
    
    // fill with the courses
    @FXML
    private void getCourseLists(ActionEvent event) throws SQLException, ClassNotFoundException {
        ObservableList<String> list = FXCollections.observableArrayList();
        
        if(coursetype.getValue().equals("Undergraduate")){
            String sql = "select course_name from ugcourse where faculty_name = '"+faculty.getValue()+"'";
            try{
                ResultSet rsSet = DBUtil.dbExcute(sql);
                while(rsSet.next()){
                    String course_name = rsSet.getString("course_name");
                    list.add(course_name);
                }
                coursename.setItems(list);
                
            }catch(SQLException e){
                System.out.println("Problem occured when filling subject list");
//                throw e;
            }
        } else if(coursetype.getValue().equals("Postgraduate")){
            System.out.println(coursetype.getValue() + " Post AddSub");
        } else{
            System.out.println(coursetype.getValue() + " Error!!!! AddSub");
        }
    }
    // fetch the object data to table
    @FXML
    private void getSubjectName(ActionEvent event) throws SQLException, ClassNotFoundException {
        getCourseId();
        subjectidcol.setCellValueFactory(cellData -> cellData.getValue().getSubjectid());
        subjectnamecol.setCellValueFactory(cellData -> cellData.getValue().getSubjectName());
        int_idcol.setCellValueFactory(cellData -> cellData.getValue().getIntructorId().asObject());
        lab_idcol.setCellValueFactory(cellData -> cellData.getValue().getLabNameId());
        
        
        try{
            ObservableList<UGSubjectLabs> list = getSubjects(course_id, sem);
            populateTable(list);
        }catch (SQLException e){
            System.out.println("Problem occured when setting cell data");
//            throw e;
        }
        
    }
    //populate table
    private void populateTable(ObservableList<UGSubjectLabs> list) {
        subjectInst_tbl.setItems(list);
    }
    // insructor cell edit get value
    @FXML
    private void changeInsIDCellEvent(TableColumn.CellEditEvent<UGSubjectLabs, Integer> edittedCell) throws SQLException, ClassNotFoundException {
        UGSubjectLabs selected = subjectInst_tbl.getSelectionModel().getSelectedItem();
        selected.SetIntrucId(Integer.parseInt(edittedCell.getNewValue().toString()));
        int newId = Integer.parseInt(edittedCell.getNewValue().toString());
        setInstructorId(selected.getSubid(), newId);
    }
    // set new value to db
    private static void setInstructorId(String subid, int newid)throws SQLException, ClassNotFoundException{
        String sql = "insert into instructorsubject(sub_id, int_id) values('"+subid+"', '"+newid+"');";
        try{
            DBUtil.dbExcuteQuery(sql);
        } catch(SQLException e){
            System.out.println("Error occurd when updating subject marks " + e);
//            throw e;
        }
    }
    // get new value of editted cell
    @FXML
    private void changeLabIDCellEvent(TableColumn.CellEditEvent<UGSubjectLabs, String> edittedCell) throws SQLException, ClassNotFoundException{
        UGSubjectLabs selected = subjectInst_tbl.getSelectionModel().getSelectedItem();
        selected.SetLabId(edittedCell.getNewValue());
        String newlbId = edittedCell.getNewValue();
        setLabRoomId(selected.getIntrucId(), newlbId);
    }
    // instert data to db
    private void setLabRoomId(int intid, String newlbId) throws SQLException, ClassNotFoundException {
        if(intid == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("");
            alert.setContentText("No instructor id found");
            alert.showAndWait();
        } else{
            String sql = "insert into instructorlab(int_id, lab_id) values('"+intid+"', '"+newlbId+"');";
            try{
                DBUtil.dbExcuteQuery(sql);
            }catch(SQLException e){
                System.out.println("Problem occured when inserting data to instructorlab");
     //           throw e;
            }
        }
    }

    
    
    
    
}
