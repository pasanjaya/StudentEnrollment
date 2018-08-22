/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentenrollment;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author bhadr
 */

//instructor subject lab assingmnt class
public class UGSubjectLabs {
    private StringProperty subidProperty;
    private StringProperty subnameProperty;
    private IntegerProperty instructorIDProperty;
    private StringProperty LabIDProperty;
    
    public UGSubjectLabs(){
        this.subidProperty = new SimpleStringProperty();
        this.subnameProperty = new SimpleStringProperty();
        this.instructorIDProperty = new SimpleIntegerProperty();
        this.LabIDProperty = new SimpleStringProperty();
    }
    
    // for sub id
    public String getSubid(){
        return subidProperty.get();
    }
    public void SetSubid(String id){
        this.subidProperty.set(id);
    }
    public StringProperty getSubjectid(){
        return subidProperty;
    }

    // for sub name
    public String getSubName(){
        return subnameProperty.get();
    }
    public void SetSubName(String name){
        this.subnameProperty.set(name);
    }
    public StringProperty getSubjectName(){
        return subnameProperty;
    }
    
    // for instructor id
    public int getIntrucId(){
        return instructorIDProperty.get();
    }
    public void SetIntrucId(int id){
        this.instructorIDProperty.set(id);
    }
    public IntegerProperty getIntructorId(){
        return instructorIDProperty;
    }
    
    // for lab id
    public String getLabId(){
        return LabIDProperty.get();
    }
    public void SetLabId(String id){
        this.LabIDProperty.set(id);
    }
    public StringProperty getLabNameId(){
        return LabIDProperty;
    }
}
