package com.ming.pregnancyadviserapp.Database;

import com.ming.pregnancyadviserapp.Instance.Lab;

import java.sql.PreparedStatement;

public class LabDAO extends DbOpenHelper {

    public boolean addLab(Lab instance)
    {
        boolean ok=false;

        try{
            getConnection();
            String sql="INSERT INTO Lab(id,bloodType,hemoglobin,platelet,AST,ALT,creatinine,urineProtein," +
                    "firstTrimester,downSyndromeScreen,secondTrimesterDownSyndromeScreen,NIPT,amniocentesis,sickleCellScreen,cysticFibrosisScreen," +
                    "HIV,syphilis,hepatitisBC,gestationalDiabetesScreen,GBS,btTime,hemTime,plaTime,ASTTime,ALTTime,creTime," +
                    "UPTime,FTTime,DSSTime,STDSSTime,NIPTTime,amnTime,SCSTime,CFSTime,HIVTime," +
                    "sypTime,HBCTime,GDSTime,GBSTime) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,instance.getId());
            pstmt.setString(2,instance.getBloodType());
            pstmt.setString(3,instance.getHemoglobin());
            pstmt.setString(4,instance.getPlatelet());
            pstmt.setString(5,instance.getAST());
            pstmt.setString(6,instance.getALT());
            pstmt.setString(7,instance.getCreatinine());
            pstmt.setString(8,instance.getUrineProtein());
            pstmt.setString(9,instance.getFirstTrimester());
            pstmt.setString(10,instance.getDownSyndromeScreen());
            pstmt.setString(11,instance.getSecondTrimesterDownSyndromeScreen());
            pstmt.setString(12,instance.getNIPT());
            pstmt.setString(13,instance.getAmniocentesis());
            pstmt.setString(14,instance.getSickleCellScreen());
            pstmt.setString(15,instance.getCysticFibrosisScreen());
            pstmt.setString(16,instance.getHIV());
            pstmt.setString(17,instance.getSyphilis());
            pstmt.setString(18,instance.getHepatitisBC());
            pstmt.setString(19,instance.getGestationalDiabetesScreen());
            pstmt.setString(20,instance.getGBS());
            pstmt.setString(21,instance.getBtTime());
            pstmt.setString(22,instance.getHemTime());
            pstmt.setString(23,instance.getPlaTime());
            pstmt.setString(24,instance.getASTTime());
            pstmt.setString(25,instance.getALTTime());
            pstmt.setString(26,instance.getCreTime());
            pstmt.setString(27,instance.getUPTime());
            pstmt.setString(28,instance.getFTTime());
            pstmt.setString(29,instance.getDSSTime());
            pstmt.setString(30,instance.getSTDSSTime());
            pstmt.setString(31,instance.getNIPTTime());
            pstmt.setString(32,instance.getAmnTime());
            pstmt.setString(33,instance.getSCSTime());
            pstmt.setString(34,instance.getCFSTime());
            pstmt.setString(35,instance.getHIVTime());
            pstmt.setString(36,instance.getSypTime());
            pstmt.setString(37,instance.getHBCTime());
            pstmt.setString(38,instance.getGDSTime());
            pstmt.setString(39,instance.getGBSTime());
            int check=0;
            check=pstmt.executeUpdate();
            if(check!=0)
            {
                ok=true;
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }

        return ok;
    }

    public boolean checkExists(int id)
    {
        boolean exists=false;

        try{
            getConnection();
            String sql="SELECT * FROM Lab WHERE id=?";
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
            String sql="DELETE FROM Lab WHERE id=?";
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

    public boolean updateLab(int id, String title, String value, String time)
    {
        boolean ok=false;

        switch (title)
        {
            case "blood type":
                try{
                    getConnection();
                    String sql="UPDATE Lab SET bloodType=?, btTime=? WHERE id=?";
                    pstmt=(PreparedStatement) conn.prepareStatement(sql);
                    pstmt.setString(1,value);
                    pstmt.setString(2,time);
                    pstmt.setInt(3,id);
                    int check=pstmt.executeUpdate();
                    if(check!=0)
                    {
                        ok=true;
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }finally {
                    closeAll();
                }
                break;

            case "Hemoglobin":
                try {
                    getConnection();
                    String sql="UPDATE Lab SET hemoglobin=?, hemTime=? WHERE id=?";
                    pstmt=(PreparedStatement) conn.prepareStatement(sql);
                    pstmt.setString(1,value);
                    pstmt.setString(2,time);
                    pstmt.setInt(3,id);
                    int check=pstmt.executeUpdate();
                    if(check!=0)
                    {
                        ok=true;
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }finally {
                    closeAll();
                }
                break;

            case "platelet":
                try {
                    getConnection();
                    String sql="UPDATE Lab SET platelet=?,plaTime=? WHERE id=?";
                    pstmt=(PreparedStatement) conn.prepareStatement(sql);
                    pstmt.setString(1,value);
                    pstmt.setString(2,time);
                    pstmt.setInt(3,id);
                    int check=pstmt.executeUpdate();
                    if(check!=0)
                    {
                        ok=true;
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }finally {
                    closeAll();
                }
                break;

            case "AST":
                try {
                    getConnection();
                    String sql="UPDATE Lab SET AST=?,ASTTime=? WHERE id=?";
                    pstmt=(PreparedStatement) conn.prepareStatement(sql);
                    pstmt.setString(1,value);
                    pstmt.setString(2,time);
                    pstmt.setInt(3,id);
                    int check=pstmt.executeUpdate();
                    if(check!=0)
                    {
                        ok=true;
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }finally {
                    closeAll();
                }
                break;

            case "ALT":
                try {
                    getConnection();
                    String sql="UPDATE Lab SET ALT=?,ALTTime=? WHERE id=?";
                    pstmt=(PreparedStatement) conn.prepareStatement(sql);
                    pstmt.setString(1,value);
                    pstmt.setString(2,time);
                    pstmt.setInt(3,id);
                    int check=pstmt.executeUpdate();
                    if(check!=0)
                    {
                        ok=true;
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }finally {
                    closeAll();
                }
                break;

            case "creatinine":
                try {
                    getConnection();
                    String sql="UPDATE Lab SET creatinine=?,creTime=? WHERE id=?";
                    pstmt=(PreparedStatement) conn.prepareStatement(sql);
                    pstmt.setString(1,value);
                    pstmt.setString(2,time);
                    pstmt.setInt(3,id);
                    int check=pstmt.executeUpdate();
                    if(check!=0)
                    {
                        ok=true;
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }finally {
                    closeAll();
                }
                break;

            case "Urine Protein":
                try {
                    getConnection();
                    String sql="UPDATE Lab SET urineProtein=?,UPTime=? WHERE id=?";
                    pstmt=(PreparedStatement) conn.prepareStatement(sql);
                    pstmt.setString(1,value);
                    pstmt.setString(2,time);
                    pstmt.setInt(3,id);
                    int check=pstmt.executeUpdate();
                    if(check!=0)
                    {
                        ok=true;
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }finally {
                    closeAll();
                }
                break;

            case "first Trimester":
                try {
                    getConnection();
                    String sql="UPDATE Lab SET firstTrimester=?,FTTime=? WHERE id=?";
                    pstmt=(PreparedStatement) conn.prepareStatement(sql);
                    pstmt.setString(1,value);
                    pstmt.setString(2,time);
                    pstmt.setInt(3,id);
                    int check=pstmt.executeUpdate();
                    if(check!=0)
                    {
                        ok=true;
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }finally {
                    closeAll();
                }
                break;

            case "down Syndrome Screen":
                try {
                    getConnection();
                    String sql="UPDATE Lab SET downSyndromeScreen=?,DSSTime=? WHERE id=?";
                    pstmt=(PreparedStatement) conn.prepareStatement(sql);
                    pstmt.setString(1,value);
                    pstmt.setString(2,time);
                    pstmt.setInt(3,id);
                    int check=pstmt.executeUpdate();
                    if(check!=0)
                    {
                        ok=true;
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }finally {
                    closeAll();
                }
                break;

            case "second Trimester Down Syndrome Screen":
                try {
                    getConnection();
                    String sql="UPDATE Lab SET secondTrimesterDownSyndromeScreen=?,STDSSTime=? WHERE id=?";
                    pstmt=(PreparedStatement) conn.prepareStatement(sql);
                    pstmt.setString(1,value);
                    pstmt.setString(2,time);
                    pstmt.setInt(3,id);
                    int check=pstmt.executeUpdate();
                    if(check!=0)
                    {
                        ok=true;
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }finally {
                    closeAll();
                }
                break;

            case "NIPT":
                try {
                    getConnection();
                    String sql="UPDATE Lab SET NIPT=?,NIPTTime=? WHERE id=?";
                    pstmt=(PreparedStatement) conn.prepareStatement(sql);
                    pstmt.setString(1,value);
                    pstmt.setString(2,time);
                    pstmt.setInt(3,id);
                    int check=pstmt.executeUpdate();
                    if(check!=0)
                    {
                        ok=true;
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }finally {
                    closeAll();
                }
                break;

            case "amniocentesis":
                try {
                    getConnection();
                    String sql="UPDATE Lab SET amniocentesis=?,amnTime=? WHERE id=?";
                    pstmt=(PreparedStatement) conn.prepareStatement(sql);
                    pstmt.setString(1,value);
                    pstmt.setString(2,time);
                    pstmt.setInt(3,id);
                    int check=pstmt.executeUpdate();
                    if(check!=0)
                    {
                        ok=true;
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }finally {
                    closeAll();
                }
                break;

            case "sickle Cell Screen":
                try {
                    getConnection();
                    String sql="UPDATE Lab SET sickleCellScreen=?,SCSTime=? WHERE id=?";
                    pstmt=(PreparedStatement) conn.prepareStatement(sql);
                    pstmt.setString(1,value);
                    pstmt.setString(2,time);
                    pstmt.setInt(3,id);
                    int check=pstmt.executeUpdate();
                    if(check!=0)
                    {
                        ok=true;
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }finally {
                    closeAll();
                }
                break;

            case "cystic Fibrosis Screen":
                try {
                    getConnection();
                    String sql="UPDATE Lab SET cysticFibrosisScreen=?,CFSTime=? WHERE id=?";
                    pstmt=(PreparedStatement) conn.prepareStatement(sql);
                    pstmt.setString(1,value);
                    pstmt.setString(2,time);
                    pstmt.setInt(3,id);
                    int check=pstmt.executeUpdate();
                    if(check!=0)
                    {
                        ok=true;
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }finally {
                    closeAll();
                }
                break;

            case "HIV":
                try {
                    getConnection();
                    String sql="UPDATE Lab SET HIV=?,HIVTime=? WHERE id=?";
                    pstmt=(PreparedStatement) conn.prepareStatement(sql);
                    pstmt.setString(1,value);
                    pstmt.setString(2,time);
                    pstmt.setInt(3,id);
                    int check=pstmt.executeUpdate();
                    if(check!=0)
                    {
                        ok=true;
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }finally {
                    closeAll();
                }
                break;

            case "syphilis":
                try {
                    getConnection();
                    String sql="UPDATE Lab SET syphilis=?,sypTime=? WHERE id=?";
                    pstmt=(PreparedStatement) conn.prepareStatement(sql);
                    pstmt.setString(1,value);
                    pstmt.setString(2,time);
                    pstmt.setInt(3,id);
                    int check=pstmt.executeUpdate();
                    if(check!=0)
                    {
                        ok=true;
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }finally {
                    closeAll();
                }
                break;

            case "hepatitisBC":
                try {
                    getConnection();
                    String sql="UPDATE Lab SET hepatitisBC=?,HBCTime=? WHERE id=?";
                    pstmt=(PreparedStatement) conn.prepareStatement(sql);
                    pstmt.setString(1,value);
                    pstmt.setString(2,time);
                    pstmt.setInt(3,id);
                    int check=pstmt.executeUpdate();
                    if(check!=0)
                    {
                        ok=true;
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }finally {
                    closeAll();
                }
                break;

            case "gestational Diabetes Screen":
                try {
                    getConnection();
                    String sql="UPDATE Lab SET gestationalDiabetesScreen=?,GDSTime=? WHERE id=?";
                    pstmt=(PreparedStatement) conn.prepareStatement(sql);
                    pstmt.setString(1,value);
                    pstmt.setString(2,time);
                    pstmt.setInt(3,id);
                    int check=pstmt.executeUpdate();
                    if(check!=0)
                    {
                        ok=true;
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }finally {
                    closeAll();
                }
                break;

            case "GBS":
                try {
                    getConnection();
                    String sql="UPDATE Lab SET GBS=?,GBSTime=? WHERE id=?";
                    pstmt=(PreparedStatement) conn.prepareStatement(sql);
                    pstmt.setString(1,value);
                    pstmt.setString(2,time);
                    pstmt.setInt(3,id);
                    int check=pstmt.executeUpdate();
                    if(check!=0)
                    {
                        ok=true;
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }finally {
                    closeAll();
                }
                break;

            default:
                break;
        }

        return ok;
    }

    public Lab getLab(int id)
    {
        Lab instance=null;
        try {
            getConnection();
            String sql="SELECT * FROM Lab WHERE id=?";
            pstmt=(PreparedStatement) conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            rs=pstmt.executeQuery();
            if(rs.next())
            {
                instance=new Lab();
                instance.setId(rs.getInt("id"));
                instance.setBloodType(rs.getString("bloodType"));
                instance.setBtTime(rs.getString("btTime"));
                instance.setHemoglobin(rs.getString("hemoglobin"));
                instance.setHemTime(rs.getString("hemTime"));
                instance.setPlatelet(rs.getString("platelet"));
                instance.setPlaTime(rs.getString("plaTime"));
                instance.setAST(rs.getString("AST"));
                instance.setASTTime(rs.getString("ASTTime"));
                instance.setALT(rs.getString("ALT"));
                instance.setALTTime(rs.getString("ALTTime"));
                instance.setCreatinine(rs.getString("creatinine"));
                instance.setCreTime(rs.getString("creTime"));
                instance.setUrineProtein(rs.getString("urineProtein"));
                instance.setUPTime(rs.getString("UPTime"));
                instance.setFirstTrimester(rs.getString("firstTrimester"));
                instance.setFTTime(rs.getString("FTTime"));
                instance.setDownSyndromeScreen(rs.getString("downSyndromeScreen"));
                instance.setDSSTime(rs.getString("DSSTime"));
                instance.setSecondTrimesterDownSyndromeScreen(rs.getString("secondTrimesterDownSyndromeScreen"));
                instance.setSTDSSTime(rs.getString("STDSSTime"));
                instance.setNIPT(rs.getString("NIPT"));
                instance.setNIPTTime(rs.getString("NIPTTime"));
                instance.setAmniocentesis(rs.getString("amniocentesis"));
                instance.setAmnTime(rs.getString("amnTime"));
                instance.setSickleCellScreen(rs.getString("sickleCellScreen"));
                instance.setSCSTime(rs.getString("SCSTime"));
                instance.setCysticFibrosisScreen(rs.getString("cysticFibrosisScreen"));
                instance.setCFSTime(rs.getString("CFSTime"));
                instance.setHIV(rs.getString("HIV"));
                instance.setHIVTime(rs.getString("HIVTime"));
                instance.setSyphilis(rs.getString("syphilis"));
                instance.setSypTime(rs.getString("sypTime"));
                instance.setHepatitisBC(rs.getString("hepatitisBC"));
                instance.setHBCTime(rs.getString("HBCTime"));
                instance.setGestationalDiabetesScreen(rs.getString("gestationalDiabetesScreen"));
                instance.setGDSTime(rs.getString("GDSTime"));
                instance.setGBS(rs.getString("GBS"));
                instance.setGBSTime(rs.getString("GBSTime"));
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return instance;
    }
}
