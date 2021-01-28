package com.ming.pregnancyadviserapp.Instance;

import java.io.Serializable;

public class Diabetes implements Serializable {
    int id;
    int typePosition;
    String type;
    String years;
    String fGlucose;
    String onehrglucose;
    String twohrglucose;
    String increaseInsulin;
    String time;
    String fgTime;
    String oneHrTime;
    String twoHrTime;

    public Diabetes() {
    }

    public Diabetes(int id,int typePosition, String type, String years, String fGlucose, String onehrglucose, String twohrglucose, String increaseInsulin, String time,String fgTime,String oneHrTime,String twoHrTime) {
        this.id = id;
        this.typePosition=typePosition;
        this.type = type;
        this.years = years;
        this.fGlucose = fGlucose;
        this.onehrglucose = onehrglucose;
        this.twohrglucose = twohrglucose;
        this.increaseInsulin = increaseInsulin;
        this.time = time;
        this.fgTime=fgTime;
        this.oneHrTime=oneHrTime;
        this.twoHrTime=twoHrTime;
    }

    public String getFgTime() {
        return fgTime;
    }

    public void setFgTime(String fgTime) {
        this.fgTime = fgTime;
    }

    public String getOneHrTime() {
        return oneHrTime;
    }

    public void setOneHrTime(String oneHrTime) {
        this.oneHrTime = oneHrTime;
    }

    public String getTwoHrTime() {
        return twoHrTime;
    }

    public void setTwoHrTime(String twoHrTime) {
        this.twoHrTime = twoHrTime;
    }

    public int getTypePosition() {
        return typePosition;
    }

    public void setTypePosition(int typePosition) {
        this.typePosition = typePosition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getfGlucose() {
        return fGlucose;
    }

    public void setfGlucose(String fGlucose) {
        this.fGlucose = fGlucose;
    }

    public String getOnehrglucose() {
        return onehrglucose;
    }

    public void setOnehrglucose(String onehrglucose) {
        this.onehrglucose = onehrglucose;
    }

    public String getTwohrglucose() {
        return twohrglucose;
    }

    public void setTwohrglucose(String twohrglucose) {
        this.twohrglucose = twohrglucose;
    }

    public String getIncreaseInsulin() {
        return increaseInsulin;
    }

    public void setIncreaseInsulin(String increaseInsulin) {
        this.increaseInsulin = increaseInsulin;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
