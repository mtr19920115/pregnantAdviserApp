package com.ming.pregnancyadviserapp.Instance;

import java.io.Serializable;

public class PregnancyHistory implements Serializable {
    int id;
    int pNumber;
    String DeliverDate;
    String wayDeliver;
    String pree;
    String ba;
    String bf37;
    String babyWeight;

    public PregnancyHistory() {
    }

    public PregnancyHistory(int id, int pNumber, String deliverDate, String wayDeliver, String pree, String ba, String bf37,String babyWeight) {
        this.id = id;
        this.pNumber = pNumber;
        DeliverDate = deliverDate;
        this.wayDeliver = wayDeliver;
        this.pree = pree;
        this.ba = ba;
        this.bf37 = bf37;
        this.babyWeight=babyWeight;
    }

    public String getBabyWeight() {
        return babyWeight;
    }

    public void setBabyWeight(String babyWeight) {
        this.babyWeight = babyWeight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getpNumber() {
        return pNumber;
    }

    public void setpNumber(int pNumber) {
        this.pNumber = pNumber;
    }

    public String getDeliverDate() {
        return DeliverDate;
    }

    public void setDeliverDate(String deliverDate) {
        DeliverDate = deliverDate;
    }

    public String getWayDeliver() {
        return wayDeliver;
    }

    public void setWayDeliver(String wayDeliver) {
        this.wayDeliver = wayDeliver;
    }

    public String getPree() {
        return pree;
    }

    public void setPree(String pree) {
        this.pree = pree;
    }

    public String getBa() {
        return ba;
    }

    public void setBa(String ba) {
        this.ba = ba;
    }

    public String getBf37() {
        return bf37;
    }

    public void setBf37(String bf37) {
        this.bf37 = bf37;
    }
}
