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

// subject detalis class
public class UGSubjectRecords {
    private StringProperty subCodeProperty;
    private StringProperty subNameProperty;
    private IntegerProperty subCostProperty;

    public UGSubjectRecords() {
        this.subCodeProperty = new SimpleStringProperty();
        this.subNameProperty = new SimpleStringProperty();
        this.subCostProperty = new SimpleIntegerProperty();
    }
    
    // for sub code
    public String getSubCode(){
        return subCodeProperty.get();
    }
    public void SetSubCode(String code){
        this.subCodeProperty.set(code);
    }
    public StringProperty getSubjectCode(){
        return subCodeProperty;
    }
    
    // for sub name
    public String getSubName(){
        return subNameProperty.get();
    }
    public void SetSubName(String name){
        this.subNameProperty.set(name);
    }
    public StringProperty getSubjectName(){
        return subNameProperty;
    }
    
    // for sub cost
    public int getSubCost(){
        return subCostProperty.get();
    }
    public void SetSubCost(int cost){
        this.subCostProperty.set(cost);
    }
    public IntegerProperty getSubjectCost(){
        return subCostProperty;
    }
    
}
