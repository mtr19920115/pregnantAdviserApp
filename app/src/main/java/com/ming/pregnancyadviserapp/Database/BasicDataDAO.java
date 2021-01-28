package com.ming.pregnancyadviserapp.Database;

import com.ming.pregnancyadviserapp.Instance.BasicData;


import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class BasicDataDAO extends DbOpenHelper {

    public List<BasicData> getAllBasicDataList(){
        List<BasicData> list=new ArrayList<>();

        try{
            getConnection();
            String sql="SELECT * FROM basicData ";
            pstmt= (PreparedStatement) conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            if(rs.next())
            {
                BasicData instance=new BasicData();
                instance.setId(rs.getInt("id"));
                instance.setDateType(rs.getString("dateType"));
                instance.setMainDate(rs.getString("mainDate"));
                instance.setMedicalHistory(rs.getString("mh"));
                instance.setPregnancyHistory(rs.getString("ph"));
                instance.setHeight(rs.getString("height"));
                instance.setWeight(rs.getString("weight"));
                instance.setBMI(rs.getString("BMI"));
                instance.setHypertension(rs.getString("hypertension"));
                instance.setDiabetes(rs.getString("diabetes"));
                instance.setFirstName(rs.getString("firstName"));
                instance.setLastName(rs.getString("lastName"));

                list.add(instance);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }

        return list;
    }


    public BasicData getBasicData(int id)
    {
       BasicData instance=null;
        try{
            getConnection();
            String sql="SELECT * FROM basicData WHERE id=?";
            pstmt= (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            rs=pstmt.executeQuery();
            if(rs.next())
            {
                instance=new BasicData();
                instance.setId(rs.getInt("id"));
                instance.setDateType(rs.getString("dateType"));
                instance.setMainDate(rs.getString("mainDate"));
                instance.setMedicalHistory(rs.getString("mh"));
                instance.setPregnancyHistory(rs.getString("ph"));
                instance.setHeight(rs.getString("height"));
                instance.setWeight(rs.getString("weight"));
                instance.setBMI(rs.getString("BMI"));
                instance.setHypertension(rs.getString("hypertension"));
                instance.setDiabetes(rs.getString("diabetes"));
                instance.setFirstName(rs.getString("firstName"));
                instance.setLastName(rs.getString("lastName"));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return instance;
    }

    public int addBasicData(BasicData instance)
    {
        int iRow=0;

        try{
            getConnection();
            String sql="INSERT INTO basicData(id,dateTypePosition, dateType,mainDate,mh,ph,height,weight,BMI,hypertension,diabetes,firstName,lastName) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pstmt= (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setInt(1,instance.getId());
            pstmt.setInt(2,instance.getDateTypePosition());
            pstmt.setString(3,instance.getDateType());
            pstmt.setString(4,instance.getMainDate());
            pstmt.setString(5,instance.getMedicalHistory());
            pstmt.setString(6,instance.getPregnancyHistory());
            pstmt.setString(7,instance.getHeight());
            pstmt.setString(8,instance.getWeight());
            pstmt.setString(9,instance.getBMI());
            pstmt.setString(10,instance.getHypertension());
            pstmt.setString(11,instance.getDiabetes());
            pstmt.setString(12,instance.getFirstName());
            pstmt.setString(13,instance.getLastName());
            iRow=pstmt.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }

        return iRow;
    }

    public int updateBasicData(BasicData instance)
    {

        int iRow=0;
        try{
            getConnection();
            String sql="UPDATE basicData SET dateType=?,mainDate=?, mh=?, ph=?,height=?,weight=?,BMI=?,hypertension=?,diabetes=?,firstName=?,lastName=? WHERE id=?";
            pstmt= (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1,instance.getDateType());
            pstmt.setString(2,instance.getMainDate());
            pstmt.setString(3,instance.getMedicalHistory());
            pstmt.setString(4,instance.getPregnancyHistory());
            pstmt.setString(5,instance.getHeight());
            pstmt.setString(6,instance.getWeight());
            pstmt.setString(7,instance.getBMI());
            pstmt.setString(8,instance.getHypertension());
            pstmt.setString(9,instance.getDiabetes());
            pstmt.setString(10,instance.getFirstName());
            pstmt.setString(11,instance.getLastName());
            pstmt.setInt(12,instance.getId());
            iRow=pstmt.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }



        return iRow;
    }

    public boolean checkExists(int id)
    {
        boolean exists=false;

        try{
            getConnection();
            String sql="SELECT * FROM basicData WHERE id=?";
            pstmt= (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            rs=pstmt.executeQuery();
            if(rs.next())
            {
                exists=true;
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }

        return exists;
    }

    public int deleteUser(int id)
    {
        int iRow=0;
        try{
            getConnection();
            String sql="DELETE FROM basicData WHERE id=?";
            pstmt= (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            iRow=pstmt.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }

        return iRow;
    }


}
