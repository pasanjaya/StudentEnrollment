/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentenrollment.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import studentenrollment.UGStudent;

/**
 * FXML Controller class
 *
 * @author bhadr
 */

// create student login controlller
public class FXMLUGCreateAccountController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField cpassword;

    String pass = "";
    private void passCheck(){       // password check
        if(password.getText().equals(cpassword.getText())){
            pass = password.getText();
        }
    }
    
    String user = "2018/ug/" + String.valueOf(UGStudent.stuId); //set username string
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        username.setText(user); // set the username that can't change by user
    }    

   
    //completion of login creating
    @FXML
    private void doneBtn(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        
        passCheck(); //password chk
        
        if(!pass.equals("")){
            UGStudent.insertStudentLogin(username.getText(), email.getText(), pass);
            
            //return to the login page;
            Parent root = FXMLLoader.load(getClass().getResource("/studentenrollment/FXMLDocument.fxml"));
            Scene scene = new Scene(root);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
        } 
        else{
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("");
            alert.setContentText("Passwords not correct");
            alert.showAndWait();
        }
    }
}
