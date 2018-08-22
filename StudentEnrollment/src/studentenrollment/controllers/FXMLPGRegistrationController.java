/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentenrollment.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bhadr
 */

// postgraduate registration
public class FXMLPGRegistrationController implements Initializable {

    @FXML
    private AnchorPane pgregistration;
    @FXML
    private TextField nameIn;
    @FXML
    private TextField fullname;
    @FXML
    private DatePicker dob;
    @FXML
    private RadioButton male;
    @FXML
    private ToggleGroup gender;
    @FXML
    private RadioButton female;
    @FXML
    private TextField nic;
    @FXML
    private TextField address;
    @FXML
    private TextField telephone;
    @FXML
    private TextField qualification;
    @FXML
    private TextField yearcmplt;
    @FXML
    private TextField institute;
    @FXML
    private ComboBox<String> facname;
    @FXML
    private ComboBox<String> coursename;

    /**
     * Initializes the controller class.
     */
    
    // validation check
    private boolean isFilledAll(){
        if(gender.getSelectedToggle() != null && !nameIn.getText().equals("") && !fullname.getText().equals("") && 
                !nic.getText().equals("") && !address.getText().equals("") && 
                !telephone.getText().equals("") && dob.getValue() != null && 
                !qualification.getText().equals("") && !yearcmplt.getText().equals("") &&
                !institute.getText().equals("")){
            return true;
        }
        return false;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void getFacultyCourses(ActionEvent event) {
    }

    // cancel btn get to the home login
    @FXML
    private void cancelBtn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/studentenrollment/FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }

    @FXML
    private void nextBtn(ActionEvent event) {
        if(isFilledAll()){
            System.out.println("OKAY");
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Unfinished");
            alert.setHeaderText("");
            alert.setContentText("Please complete all fields");
            alert.showAndWait();
        }
    }
    
}
