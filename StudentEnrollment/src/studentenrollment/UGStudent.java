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
public class UGStudent {

    public static int stuId;
    // insert student details to database
    public static void insertStudent(String name, String fullname, String dob, String gender, String nic,
            String address, String year, String syllabus, String indexno, String zscore, String rank, String subject1,
            String grade1, String subject2, String grade2, String subject3, String grade3, int course_id) throws SQLException, ClassNotFoundException {
        String sql = "insert into ugstudent(name, fullname, dob, gender, nic, address, year, syllabus, "
                + "indexno, zscore,rank, subject1, grade1, subject2, grade2, subject3, grade3, course_id) "
                + "values('" + name + "', '" + fullname + "', '" + dob + "', '" + gender + "', '" + nic + "', '" + address + "',"
                + " '" + year + "','" + syllabus + "', '" + indexno + "', '" + zscore + "', '" + rank + "', '" + subject1 + "', '" + grade1 + "', "
                + "'" + subject2 + "', '" + grade2 + "', '" + subject3 + "', '" + grade3 + "', '"+course_id+"');";
        try {
            DBUtil.dbExcuteQuery(sql);
        } catch (SQLException e) {
            System.out.println("problem occured when inserting data " + e);
//            throw e;
        }
         getLastId();//retrive id of add student;
    }
    
    // get newest student that added to make login details
    public static void getLastId() throws SQLException, ClassNotFoundException {
        String sql = "select max(stu_id) from ugstudent;";
        
        try {
            ResultSet rsSet = DBUtil.dbExcute(sql);
            if(rsSet.last()){
                stuId = rsSet.getInt(1);
            }
        } catch(SQLException e){
            System.out.println("Problem occurd when retriving ID " + e);
//            throw e;
        }
    }
    
    
    
    //create login for new student
    public static void insertStudentLogin(String username, String email, String password) throws SQLException, ClassNotFoundException {
        String sql = "insert into uglogin(username, email, pass, stu_id)"
                + "values('" + username + "', '" + email + "', '" + password + "', '" +stuId+ "');";

        try {
            DBUtil.dbExcuteQuery(sql);
        } catch (SQLException e) {
            System.out.println("Problem occured when inserting login details " + e);
//            throw e;
        }
    }
    //retrive student recordes
    public static ObservableList<UGStudentRecords> getRecords() throws SQLException, ClassNotFoundException{
        String sql = "select stu_id, name, dob, gender, nic, address, indexno, zscore, rank, course_name, faculty_name "
                + "from ugstudent, ugcourse "
                + "where ugstudent.course_id = ugcourse.course_id;";
        try{
            ResultSet rsSet = DBUtil.dbExcute(sql);
            ObservableList<UGStudentRecords> stuList = getStudentObject(rsSet);
            return stuList;
        }catch(SQLException e){
            System.out.println("Problem occured when fetching to TableView "+ e);
            throw e;
        }
    }
    // filling the observable list with student objects
    private static ObservableList<UGStudentRecords> getStudentObject(ResultSet rsSet) throws SQLException, ClassNotFoundException {
        try{
            ObservableList<UGStudentRecords> stuList = FXCollections.observableArrayList();
            while(rsSet.next()){
                UGStudentRecords stu = new UGStudentRecords();
                stu.SetStuId(rsSet.getInt("stu_id"));
                stu.SetStuName(rsSet.getString("name"));
                stu.SetStuDob(rsSet.getString("dob"));
                stu.SetStuGender(rsSet.getString("gender"));
                stu.SetStuNic(rsSet.getString("nic"));
                stu.SetStuAddress(rsSet.getString("address"));
                stu.SetStuIndex(rsSet.getString("indexno"));
                stu.SetStuZscore(rsSet.getString("zscore"));
                stu.SetStuRank(rsSet.getString("rank"));
                stu.SetStuCourse(rsSet.getString("course_name"));
                stu.SetStuFaculty(rsSet.getString("faculty_name"));
                stuList.add(stu);
            }
            return stuList;
        }catch(SQLException e){
            System.out.println("Problem occured when fetching from db "+ e);
            throw e;
        }
    }

    public static ObservableList<UGStudentRecords> searchStudent(String stuId) throws SQLException, ClassNotFoundException{
        String sql = "select * from ugstudent, ugcourse where ugstudent.course_id = ugcourse.course_id and stu_id="+stuId;
        
        try{
            
            ResultSet rsSet = DBUtil.dbExcute(sql);
            ObservableList<UGStudentRecords> list = getStudentObject(rsSet);
            return list;
        }catch(SQLException e){
            System.out.println("Error occured when searching the recored "+ e);
            throw e;
        }
    }
    
    public static void deleteStudentbyId(int id) throws SQLException, ClassNotFoundException{
        String sql = "delete from ugstudent where stu_id='"+id+"';";
        try{
            DBUtil.dbExcuteQuery(sql);
        } catch(SQLException e){
            System.out.println("Problem occured when deleting student recored " +e);
//            throw e;
        }
    }
    
    public static boolean usernamePassChecker(String username, String password) throws SQLException, ClassNotFoundException{
        String sql = "select stu_id from uglogin where username='"+username+"' and pass='"+password+"';";
        try{
            ResultSet rsSet = DBUtil.dbExcute(sql);
            if(rsSet != null && rsSet.next()){
                return true;
            }
            
        }catch(SQLException e){
            System.out.println("Error occurd when checking username password " + e);
            //throw e;
            
        }
        return false;
    }
}
