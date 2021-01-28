package com.ming.pregnancyadviserapp.Instance;

import java.io.Serializable;

public class BasicData  implements Serializable {

    int id;
    int dateTypePosition;
    String dateType;
    String mainDate;
    String medicalHistory;
    String pregnancyHistory;
    String height;
    String weight;
    String BMI;
    String hypertension;
    String diabetes;
    String firstName;
    String lastName;

    public BasicData() {

    }

    public BasicData(int id,int dateTypePosition, String dateType, String mainDate, String medicalHistory, String pregnancyHistory, String height, String weight, String BMI, String hypertension, String diabetes, String firstName, String lastName) {
        this.id = id;
        this.dateTypePosition=dateTypePosition;
        this.dateType = dateType;
        this.mainDate = mainDate;
        this.medicalHistory = medicalHistory;
        this.pregnancyHistory = pregnancyHistory;
        this.height = height;
        this.weight = weight;
        this.BMI = BMI;
        this.hypertension = hypertension;
        this.diabetes = diabetes;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public int getDateTypePosition() {
        return dateTypePosition;
    }

    public void setDateTypePosition(int dateTypePosition) {
        this.dateTypePosition = dateTypePosition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public String getMainDate() {
        return mainDate;
    }

    public void setMainDate(String mainDate) {
        this.mainDate = mainDate;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getPregnancyHistory() {
        return pregnancyHistory;
    }

    public void setPregnancyHistory(String pregnancyHistory) {
        this.pregnancyHistory = pregnancyHistory;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBMI() {
        return BMI;
    }

    public void setBMI(String BMI) {
        this.BMI = BMI;
    }

    public String getHypertension() {
        return hypertension;
    }

    public void setHypertension(String hypertension) {
        this.hypertension = hypertension;
    }

    public String getDiabetes() {
        return diabetes;
    }

    public void setDiabetes(String diabetes) {
        this.diabetes = diabetes;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
