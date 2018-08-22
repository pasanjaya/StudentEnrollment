/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentenrollment;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import studentenrollment.utilities.DBUtil;

/**
 *
 * @author bhadr
 */
public class UGSubject {
    // get subject records using stu id
    public static ObservableList<UGSubjectRecords> getRecords(int id) throws SQLException, ClassNotFoundException{
        String sql = "select ugsub.sub_id, ugsub.sub_name, ugsub.price "
                + "from ugsubject as ugsub, ugstudentsubject "
                + "where ugstudentsubject.sub_id = ugsub.sub_id and ugstudentsubject.stu_id = '"+id+"';";
        try{
            ResultSet rsSet = DBUtil.dbExcute(sql);
            ObservableList<UGSubjectRecords> subList = getSubjectObject(rsSet);
            return subList;
        } catch(SQLException e){
            System.out.println("Prblem occured when geting subject records "+ e);
            throw e;
        }
    }
    // filling with the subject object observable list
    private static ObservableList<UGSubjectRecords> getSubjectObject(ResultSet rsSet) throws SQLException, ClassNotFoundException {
        ObservableList<UGSubjectRecords> subList = FXCollections.observableArrayList();
        try{
            while(rsSet.next()){
                UGSubjectRecords sub = new UGSubjectRecords();
                sub.SetSubCode(rsSet.getString("sub_id"));
                sub.SetSubName(rsSet.getString("sub_name"));
                sub.SetSubCost(rsSet.getInt("price"));
                subList.add(sub);
            }
            return subList;
        } catch(SQLException e){
            System.out.println("Problem occured when retriving data from subject Table " + e);
            throw e;
        }
    }
    // get subject mark that available in db
    public static ObservableList<UGSubjectMarks> getMarks(int id, int sem) throws SQLException, ClassNotFoundException {
        String sql = "select ugsub.sub_name, ugstusub.sub_mark from ugsubject as ugsub, ugstudentsubject as ugstusub "
                + "where ugstusub.sub_id = ugsub.sub_id and ugstusub.stu_id ='"+id+"' and ugstusub.sub_id like '%/_"+sem+"%';";
        
        try{
            ResultSet rsSet = DBUtil.dbExcute(sql);
            ObservableList<UGSubjectMarks> subList = getSubjectMarksObject(rsSet);
            return subList;
        } catch(SQLException e){
            System.out.println("Prblem occured when geting subject marks "+ e);
            throw e;
        }
    }
    // fill the mark with the subject and add that object to observble list
    private static ObservableList<UGSubjectMarks> getSubjectMarksObject(ResultSet rsSet) throws SQLException, ClassNotFoundException {
        ObservableList<UGSubjectMarks> subList = FXCollections.observableArrayList();
        try{
            while(rsSet.next()){
                UGSubjectMarks sub = new UGSubjectMarks();
                sub.SetSubName(rsSet.getString("sub_name"));
                sub.SetSubMark(rsSet.getInt("sub_mark"));
                subList.add(sub);
            }
            return subList;
        } catch(SQLException e){
            System.out.println("Problem occured when retriving data from subjectmark Table  " + e);
            throw e;
        }
    }
    // set new marks for a subject update db
    public static void setSubjectMarks(int id, int sem, String subName, int marks)throws SQLException, ClassNotFoundException{
        String sql = "UPDATE ugstudentsubject SET sub_mark = '"+marks+"' WHERE stu_id = '"+id+"' AND sub_id like '%/_"+sem+"%' AND sub_id = (select sub_id from ugsubject where sub_name= '"+subName+"');";
        try{
            DBUtil.dbExcuteQuery(sql);
        } catch(SQLException e){
            System.out.println("Error occurd when updating subject marks " + e);
//            throw e;
        }
    }
    //insert new subject to system
    public static void insertSubjects(String sub_id, String sub_name, int credit, int price, int course_id, String courseType) throws SQLException, ClassNotFoundException{
        if(courseType.equals("Undergraduate")){
        
            String sql = "insert into ugsubject(sub_id, sub_name, credit, price, course_id)"
                    +"values('"+sub_id+"', '"+sub_name+"', '"+credit+"', '"+price+"','"+course_id+"');";

            try{
                DBUtil.dbExcuteQuery(sql);
            } catch(SQLException e){
                System.out.println("Problem occured when inserting subjects");
//                throw e;
            }
            
        } else if(courseType.equals("Postgraduate")){
            System.out.println(courseType + " Post Student");
        } else{
            System.out.println(courseType + " Error!!!! Student");
        }
        
    }
    
}
