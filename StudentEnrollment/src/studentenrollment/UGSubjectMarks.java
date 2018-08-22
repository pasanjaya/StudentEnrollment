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

// subject name mark class
public class UGSubjectMarks {
    
    
    private StringProperty subnameProperty;
    private IntegerProperty submarkProperty;
    
    
    public UGSubjectMarks(){
        this.subnameProperty = new SimpleStringProperty();
        this.submarkProperty = new SimpleIntegerProperty();
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
    
    // for sub marks
    public int getSubMark(){
        return submarkProperty.get();
    }
    public void SetSubMark(int mark){
        this.submarkProperty.set(mark);
    }
    public IntegerProperty getSubjectMark(){
        return submarkProperty;
    }
}
