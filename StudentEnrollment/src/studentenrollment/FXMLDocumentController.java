/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentenrollment;

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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import studentenrollment.controllers.FXMLStudentSubjectDetailsController;

/**
 *
 * @author bhadr
 */
public class FXMLDocumentController implements Initializable {

    @FXML private Text title;
    @FXML private ToggleGroup degreeType;
    @FXML private RadioButton radioUndergraduate;
    @FXML private RadioButton radioPostgraduate;
    @FXML private TextField username;
    @FXML private PasswordField password;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        degreeType = new ToggleGroup();
        this.radioPostgraduate.setToggleGroup(degreeType);
        this.radioUndergraduate.setToggleGroup(degreeType);
        
    }

    @FXML
    private void loginBtn(ActionEvent event) throws IOException{
        //check for authentication
        boolean check = false;
        try{
            check = UGStudent.usernamePassChecker(username.getText(), password.getText());
        } catch(ClassNotFoundException | SQLException e){
            System.out.println("Problem occurd!");
        }
        if(check){ //load student form
           
            String stuId = username.getText().substring(8);
            FXMLStudentSubjectDetailsController.getStuId(Integer.parseInt(stuId));
            
            
            Parent stu_panel = FXMLLoader.load(getClass().getResource("fxmls/FXMLStudentSubjectDetails.fxml"));
            Scene stu_panel_scene = new Scene(stu_panel);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(stu_panel_scene);
            appStage.show();
            
        } else //admin login
        if(username.getText().equals("admin") && password.getText().equals("pass")){
            Parent admin_panel = FXMLLoader.load(getClass().getResource("fxmls/FXMLAdminPanel.fxml"));
            Scene admin_panel_scene = new Scene(admin_panel);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(admin_panel_scene);
            appStage.show();
            
        } else { //load alerbox
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("");
            alert.setContentText("Username or password incorect!");
            alert.showAndWait();
        }
    }

    @FXML
    private void registrationBtn(ActionEvent event) throws IOException {
        //undergraduate section
        if(this.degreeType.getSelectedToggle() != null && this.degreeType.getSelectedToggle().equals(this.radioUndergraduate)){
            Parent ug_registration = FXMLLoader.load(getClass().getResource("fxmls/FXMLUGRegistration.fxml"));
            Scene ug_registration_scene = new Scene(ug_registration);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(ug_registration_scene);
            appStage.show();
        }
        //postgraduate section
        else if(this.degreeType.getSelectedToggle() != null && this.degreeType.getSelectedToggle().equals(this.radioPostgraduate)){
            Parent pg_registration = FXMLLoader.load(getClass().getResource("fxmls/FXMLPGRegistration.fxml"));
            Scene pg_registration_scene = new Scene(pg_registration);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(pg_registration_scene);
            appStage.show();
        } else {
                       
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INFORMATION");
            alert.setHeaderText("");
            alert.setContentText("Please select your course type");
            alert.showAndWait();
        }
        
        
    }
    
}
