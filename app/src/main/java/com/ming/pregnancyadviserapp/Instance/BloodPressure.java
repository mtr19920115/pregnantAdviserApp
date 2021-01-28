package com.ming.pregnancyadviserapp.Instance;

public class BloodPressure {

    int id;
    int highBloodPressure;
    int lowBloodPressure;
    String timeStamper;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BloodPressure(int id,int hp,int lp,String ts)
    {
        this.id=id;
        highBloodPressure=hp;
        lowBloodPressure=lp;
        timeStamper=ts;
    }

    public BloodPressure()
    {

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

    public String getTimeStamper() {
        return timeStamper;
    }

    public void setTimeStamper(String timeStamper) {
        this.timeStamper = timeStamper;
    }
}
