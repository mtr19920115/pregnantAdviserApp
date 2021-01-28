package com.ming.pregnancyadviserapp.Instance;

import java.io.Serializable;

public class AddVisiting implements Serializable {
    int id;
    int visitNumber;
    String visitType;
    String visitDate;
    String visitTime;

    public AddVisiting() {
    }

    public AddVisiting(int id, int visitNumber, String visitType, String visitDate, String visitTime) {
        this.id = id;
        this.visitNumber = visitNumber;
        this.visitType = visitType;
        this.visitDate = visitDate;
        this.visitTime = visitTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVisitNumber() {
        return visitNumber;
    }

    public void setVisitNumber(int visitNumber) {
        this.visitNumber = visitNumber;
    }

    public String getVisitType() {
        return visitType;
    }

    public void setVisitType(String visitType) {
        this.visitType = visitType;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }
}
