/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentenrollment;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import studentenrollment.utilities.DBUtil;

/**
 *
 * @author bhadr
 */
public class ugCourse {
    // add new course details
    public static void insertCourse(String courseName, String facName, String courseType) throws SQLException, ClassNotFoundException{
        
        if(courseType.equals("Undergraduate")){
            String sql = "insert into ugcourse(course_name, faculty_name)"
                + " values('"+courseName+"', '"+facName+"');";
            try{
                DBUtil.dbExcuteQuery(sql);
                
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("INFORMATION");
                alert.setHeaderText("");
                alert.setContentText("Course added Successfully!");
                alert.showAndWait();
                
            } catch(MySQLIntegrityConstraintViolationException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Duplication Error");
                alert.setHeaderText("");
                alert.setContentText("Course Already inserted!");
                alert.showAndWait();
                
            } catch(SQLException e){
                System.out.println("Problem Occured when inserting course "+ e);
//                throw e;
            }
     
        } else if(courseType.equals("Postgraduate")){
            System.out.println(courseType + " Post");
        } else{
            System.out.println(courseType + " Error!!!!");
        }
        
        
    }
}
