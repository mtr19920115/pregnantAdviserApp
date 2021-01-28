package com.ming.pregnancyadviserapp.Instance;

import java.io.Serializable;

public class Hypertension implements Serializable {
    int id;
    int highBloodPressure;
    int lowBloodPressure;
    int longBPMedication;
    int longPregnancyMedication;
    int longCurrentMedication;
    String time;

    public Hypertension() {
    }

    public Hypertension(int id, int highBloodPressure, int lowBloodPressure, int longBPMedication, int longPregnancyMedication, int longCurrentMedication, String time) {
        this.id = id;
        this.highBloodPressure = highBloodPressure;
        this.lowBloodPressure = lowBloodPressure;
        this.longBPMedication = longBPMedication;
        this.longPregnancyMedication = longPregnancyMedication;
        this.longCurrentMedication = longCurrentMedication;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHighBloodPressure() {
        return highBloodPressure;
    }

    public void setHighBloodPressure(int highBloodPressure) {
        this.highBloodPressure = highBloodPressure;
    }

    public int getLowBloodPressure() {
        return lowBloodPressure;
    }

    public void setLowBloodPressure(int lowBloodPressure) {
        this.lowBloodPressure = lowBloodPressure;
    }

    public int getLongBPMedication() {
        return longBPMedication;
    }

    public void setLongBPMedication(int longBPMedication) {
        this.longBPMedication = longBPMedication;
    }

    public int getLongPregnancyMedication() {
        return longPregnancyMedication;
    }

    public void setLongPregnancyMedication(int longPregnancyMedication) {
        this.longPregnancyMedication = longPregnancyMedication;
    }

    public int getLongCurrentMedication() {
        return longCurrentMedication;
    }

    public void setLongCurrentMedication(int longCurrentMedication) {
        this.longCurrentMedication = longCurrentMedication;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
