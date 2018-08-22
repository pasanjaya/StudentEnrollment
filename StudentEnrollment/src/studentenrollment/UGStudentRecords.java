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

//student recored class
public class UGStudentRecords {
    
    private IntegerProperty idProperty;
    private StringProperty nameProperty;
    private StringProperty dobProperty;
    private StringProperty genderProperty;
    private StringProperty nicProperty;
    private StringProperty addressProperty;
    private StringProperty indexProperty;
    private StringProperty zscoreProperty;
    private StringProperty rankProperty;
    private StringProperty courseProperty;
    private StringProperty facultyProperty;
    
    public UGStudentRecords(){
        this.idProperty = new SimpleIntegerProperty();
        this.nameProperty = new SimpleStringProperty();
        this.dobProperty = new SimpleStringProperty();
        this.genderProperty = new SimpleStringProperty();
        this.nicProperty = new SimpleStringProperty();
        this.addressProperty= new SimpleStringProperty();
        this.indexProperty = new SimpleStringProperty();
        this.zscoreProperty = new SimpleStringProperty();
        this.rankProperty = new SimpleStringProperty();
        this.courseProperty = new SimpleStringProperty();
        this.facultyProperty = new SimpleStringProperty();
                
    }
    
    // for id
    public int getStuId(){
        return idProperty.get();
    }
    public void SetStuId(int id){
        this.idProperty.set(id);
    }
    public IntegerProperty getStudentId(){
        return idProperty;
    }
    
    // for name
    public String getStuName(){
        return nameProperty.get();
    }
    public void SetStuName(String name){
        this.nameProperty.set(name);
    }
    public StringProperty getStudentName(){
        return nameProperty;
    }
    
    //for dob
    public String getStuDob(){
        return dobProperty.get();
    }
    public void SetStuDob(String dob){
        this.dobProperty.set(dob);
    }
    public StringProperty getStudentDob(){
        return dobProperty;
    }
    
    //for gender
    public String getStuGender(){
        return genderProperty.get();
    }
    public void SetStuGender(String gender){
        this.genderProperty.set(gender);
    }
    public StringProperty getStudentGender(){
        return genderProperty;
    }
    
    //for nic
    public String getStuNic(){
        return nicProperty.get();
    }
    public void SetStuNic(String nic){
        this.nicProperty.set(nic);
    }
    public StringProperty getStudentNic(){
        return nicProperty;
    }
    
    //for address
    public String getStuAddress(){
        return addressProperty.get();
    }
    public void SetStuAddress(String address){
        this.addressProperty.set(address);
    }
    public StringProperty getStudentAddress(){
        return addressProperty;
    }
    
    //for index
    public String getStuIndex(){
        return indexProperty.get();
    }
    public void SetStuIndex(String index){
        this.indexProperty.set(index);
    }
    public StringProperty getStudentIndex(){
        return indexProperty;
    }
    
    //for zscore
    public String getStuZscore(){
        return zscoreProperty.get();
    }
    public void SetStuZscore(String zscore){
        this.zscoreProperty.set(zscore);
    }
    public StringProperty getStudentZscore(){
        return zscoreProperty;
    }
    
    //for rank
    public String getStuRank(){
        return rankProperty.get();
    }
    public void SetStuRank(String rank){
        this.rankProperty.set(rank);
    }
    public StringProperty getStudentRank(){
        return rankProperty;
    }
    
    //for course
    public String getStuCourse(){
        return courseProperty.get();
    }
    public void SetStuCourse(String course){
        this.courseProperty.set(course);
    }
    public StringProperty getStudentCourse(){
        return courseProperty;
    }
    
    //for faculty
    public String getStuFaculty(){
        return facultyProperty.get();
    }
    public void SetStuFaculty(String faculty){
        this.facultyProperty.set(faculty);
    }
    public StringProperty getStudentFaculty(){
        return facultyProperty;
    }
}
