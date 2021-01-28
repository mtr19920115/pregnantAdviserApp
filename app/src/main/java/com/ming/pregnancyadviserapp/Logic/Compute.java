package com.ming.pregnancyadviserapp.Logic;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.text.ParseException;

public class Compute {

    public String getBMI(double height,double weight)
    {
        double result=703*(weight/(Math.pow(height,2)));
        System.out.println(result);

        return String.format("%.2f",result).toString();
    }

    public String[] getFdss(String date) throws ParseException {
        String mainDate=date;
        String startDate="";
        String endDate="";
        String[] schedule=new String[2];
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Date newDate=df.parse(mainDate);
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(newDate);

        calendar.add(Calendar.WEEK_OF_YEAR,11);
        newDate=calendar.getTime();
        startDate=df.format(newDate);

        calendar.add(Calendar.WEEK_OF_YEAR,3);
        newDate=calendar.getTime();
        endDate=df.format(newDate);

        schedule[0]=startDate;
        schedule[1]=endDate;


        return schedule;
    }

    public String[] getSdss(String date) throws ParseException {
        String mainDate=date;
        String startDate="";
        String endDate="";
        String[] schedule=new String[2];
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Date newDate=df.parse(mainDate);
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(newDate);

        calendar.add(Calendar.WEEK_OF_YEAR,16);
        newDate=calendar.getTime();
        startDate=df.format(newDate);

        calendar.add(Calendar.WEEK_OF_YEAR,3);
        newDate=calendar.getTime();
        endDate=df.format(newDate);

        schedule[0]=startDate;
        schedule[1]=endDate;


        return schedule;
    }

    public String[] getTdss(String date) throws ParseException {
        String mainDate=date;
        String startDate="";
        String endDate="";
        String[] schedule=new String[2];
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Date newDate=df.parse(mainDate);
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(newDate);

        calendar.add(Calendar.WEEK_OF_YEAR,24);
        newDate=calendar.getTime();
        startDate=df.format(newDate);

        calendar.add(Calendar.WEEK_OF_YEAR,3);
        newDate=calendar.getTime();
        endDate=df.format(newDate);

        schedule[0]=startDate;
        schedule[1]=endDate;


        return schedule;
    }

    public String[] getGBS(String date) throws ParseException
    {
        String mainDate=date;
        String startDate="";
        String endDate="";
        String[] schedule=new String[2];
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Date newDate=df.parse(mainDate);
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(newDate);

        calendar.add(Calendar.WEEK_OF_YEAR,35);
        newDate=calendar.getTime();
        startDate=df.format(newDate);

        calendar.add(Calendar.WEEK_OF_YEAR,3);
        newDate=calendar.getTime();
        endDate=df.format(newDate);

        schedule[0]=startDate;
        schedule[1]=endDate;


        return schedule;
    }

    public String[] getNtt(String date) throws ParseException
    {
        String mainDate=date;
        String startDate="";
        String endDate="";
        String[] schedule=new String[2];
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Date newDate=df.parse(mainDate);
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(newDate);

        calendar.add(Calendar.WEEK_OF_YEAR,11);
        newDate=calendar.getTime();
        startDate=df.format(newDate);

        calendar.add(Calendar.WEEK_OF_YEAR,2);
        newDate=calendar.getTime();
        endDate=df.format(newDate);

        schedule[0]=startDate;
        schedule[1]=endDate;


        return schedule;
    }

    public String[] getAu(String date) throws ParseException
    {
        String mainDate=date;
        String startDate="";
        String endDate="";
        String[] schedule=new String[2];
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Date newDate=df.parse(mainDate);
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(newDate);

        calendar.add(Calendar.WEEK_OF_YEAR,18);
        newDate=calendar.getTime();
        startDate=df.format(newDate);

        calendar.add(Calendar.WEEK_OF_YEAR,3);
        newDate=calendar.getTime();
        endDate=df.format(newDate);

        schedule[0]=startDate;
        schedule[1]=endDate;


        return schedule;
    }

    public String[] getNts(String date) throws ParseException
    {
        String mainDate=date;
        String startDate="";
        String endDate="";
        String[] schedule=new String[2];
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Date newDate=df.parse(mainDate);
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(newDate);

        calendar.add(Calendar.WEEK_OF_YEAR,40);
        newDate=calendar.getTime();
        startDate=df.format(newDate);

        calendar.add(Calendar.WEEK_OF_YEAR,3);
        newDate=calendar.getTime();
        endDate=df.format(newDate);

        schedule[0]=startDate;
        schedule[1]=endDate;


        return schedule;
    }

    public String[] getOk(String date) throws ParseException
    {
        String mainDate=date;
        String startDate="";
        String endDate="";
        String[] schedule=new String[2];
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Date newDate=df.parse(mainDate);
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(newDate);

        calendar.add(Calendar.WEEK_OF_YEAR,40);
        newDate=calendar.getTime();
        startDate=df.format(newDate);

        calendar.add(Calendar.WEEK_OF_YEAR,4);
        newDate=calendar.getTime();
        endDate=df.format(newDate);

        schedule[0]=startDate;
        schedule[1]=endDate;


        return schedule;
    }

    public String[] getEdd(String date) throws ParseException
    {
        String mainDate=date;
        String startDate="";
        String endDate="";
        String[] schedule=new String[2];
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Date newDate=df.parse(mainDate);
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(newDate);

        calendar.add(Calendar.WEEK_OF_YEAR,40);
        newDate=calendar.getTime();
        startDate=df.format(newDate);

        calendar.add(Calendar.WEEK_OF_YEAR,4);
        newDate=calendar.getTime();
        endDate=df.format(newDate);

        schedule[0]=startDate;
        schedule[1]=endDate;


        return schedule;
    }

    public String[] getHyper(String date) throws ParseException
    {
        String mainDate=date;
        String startDate="";
        String endDate="";
        String[] schedule=new String[2];
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Date newDate=df.parse(mainDate);
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(newDate);

        calendar.add(Calendar.WEEK_OF_YEAR,4);
        newDate=calendar.getTime();
        startDate=df.format(newDate);

        calendar.add(Calendar.WEEK_OF_YEAR,16);
        newDate=calendar.getTime();
        endDate=df.format(newDate);

        schedule[0]=startDate;
        schedule[1]=endDate;


        return schedule;
    }

    public String[] getHyperNts(String date) throws  ParseException
    {
        String mainDate=date;
        String startDate="";
        String endDate="";
        String[] schedule=new String[2];
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Date newDate=df.parse(mainDate);
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(newDate);

        calendar.add(Calendar.WEEK_OF_YEAR,32);
        newDate=calendar.getTime();
        startDate=df.format(newDate);

        calendar.add(Calendar.WEEK_OF_YEAR,7);
        newDate=calendar.getTime();
        endDate=df.format(newDate);

        schedule[0]=startDate;
        schedule[1]=endDate;


        return schedule;
    }

    public String[] getDiacheckDate(String date) throws ParseException
    {
        String mainDate=date;
        String startDate="";
        String endDate="";
        String[] schedule=new String[2];
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Date newDate=df.parse(mainDate);
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(newDate);

        calendar.add(Calendar.WEEK_OF_YEAR,4);
        newDate=calendar.getTime();
        startDate=df.format(newDate);

        calendar.add(Calendar.WEEK_OF_YEAR,16);
        newDate=calendar.getTime();
        endDate=df.format(newDate);

        schedule[0]=startDate;
        schedule[1]=endDate;


        return schedule;
    }

    public String[] getDiaECDate(String date) throws ParseException
    {
        String mainDate=date;
        String startDate="";
        String endDate="";
        String[] schedule=new String[2];
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Date newDate=df.parse(mainDate);
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(newDate);

        calendar.add(Calendar.WEEK_OF_YEAR,22);
        newDate=calendar.getTime();
        startDate=df.format(newDate);

        calendar.add(Calendar.WEEK_OF_YEAR,0);
        newDate=calendar.getTime();
        endDate=df.format(newDate);

        schedule[0]=startDate;
        schedule[1]=endDate;


        return schedule;
    }

    public String[] getDiaNVDate(String date) throws  ParseException
    {
        String mainDate=date;
        String startDate="";
        String endDate="";
        String date2="";
        String date3="";
        String date4="";
        String date5="";
        String date6="";

        String[] schedule=new String[7];
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Date newDate=df.parse(mainDate);
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(newDate);

        calendar.add(Calendar.WEEK_OF_YEAR,6);
        newDate=calendar.getTime();
        startDate=df.format(newDate);

        calendar.add(Calendar.WEEK_OF_YEAR,4);
        newDate=calendar.getTime();
        date2=df.format(newDate);

        calendar.add(Calendar.WEEK_OF_YEAR,4);
        newDate=calendar.getTime();
        date3=df.format(newDate);

        calendar.add(Calendar.WEEK_OF_YEAR,4);
        newDate=calendar.getTime();
        date4=df.format(newDate);

        calendar.add(Calendar.WEEK_OF_YEAR,4);
        newDate=calendar.getTime();
        date5=df.format(newDate);

        calendar.add(Calendar.WEEK_OF_YEAR,4);
        newDate=calendar.getTime();
        date6=df.format(newDate);

        calendar.add(Calendar.WEEK_OF_YEAR,4);
        newDate=calendar.getTime();
        endDate=df.format(newDate);

        schedule[0]=startDate;
        schedule[1]=date2;
        schedule[2]=date3;
        schedule[3]=date4;
        schedule[4]=date5;
        schedule[5]=date6;
        schedule[6]=endDate;


        return schedule;
    }

    public boolean checkBooldPressure(int high, int low)
    {
        if(low>=110||high>=160)
        {
            return true;
        }
        return false;
    }

    public boolean checkGlucose(String type, int glucose)
    {

        if(type.equals("nfg")&&glucose>=95*1.25)
        {

                return true;
        }

        if(type.equals("1pg")&&glucose>=140*1.25)
        {
            return true;
        }

        if(type.equals("2pg")&&glucose>=120*1.25)
        {
            return true;
        }



        return false;
    }

}
