package com.ming.pregnancyadviserapp.Database;

import com.ming.pregnancyadviserapp.Instance.Diabetes;

public class DiabetesDAO extends DbOpenHelper {

    public boolean addDiabetes(Diabetes instance)
    {
        boolean ok=false;
        try {
            getConnection();
            String sql="INSERT INTO Diabetes(id,type,years,time,typePosition) VALUES (?,?,?,?,?)";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,instance.getId());
            pstmt.setString(2,instance.getType());
            pstmt.setString(3,instance.getYears());
            pstmt.setString(4,instance.getTime());
            pstmt.setInt(5,instance.getTypePosition());
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

    public void addDiabetesWithOutParam(Diabetes instance)
    {
        try{
            getConnection();
            String sql="INSERT INTO Diabetes(id) VALUES (?)";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,instance.getId());
            pstmt.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
    }

    public boolean deleteDiabetes(int id)
    {
        boolean ok=false;
        int UID=id;

        try {
            getConnection();
            String sql="DELETE FROM Diabetes WHERE id=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,UID);
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

    public Diabetes getDiabetes(int id)
    {
        int UID=id;
        Diabetes instance=new Diabetes();
        try{
            getConnection();
            String sql="SELECT * FROM Diabetes WHERE id=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,UID);
            rs=pstmt.executeQuery();
            while(rs.next())
            {
                instance.setId(rs.getInt("id"));
                instance.setType(rs.getString("type"));
                instance.setYears(rs.getString("years"));
                instance.setfGlucose(rs.getString("fGlucose"));
                instance.setOnehrglucose(rs.getString("onehrglucose"));
                instance.setTwohrglucose(rs.getString("twohrglucose"));
                instance.setIncreaseInsulin(rs.getString("increaseInsulin"));
                instance.setTime(rs.getString("time"));
                instance.setFgTime(rs.getString("fgTime"));
                instance.setOneHrTime(rs.getString("oneHrTime"));
                instance.setTwoHrTime(rs.getString("twoHrTime"));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }

        return instance;
    }

    public void updateDiaInfo(int id, String type,String year)
    {
        try{
            getConnection();
            String sql="UPDATE Diabetes SET type=?,years=? WHERE id=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,type);
            pstmt.setString(2,year);
            pstmt.setInt(3,id);
            pstmt.executeUpdate();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }finally {
            closeAll();
        }
    }


    public boolean updatefGlucose(int id, String fg,String time)
    {
        int UID=id;
        String fGlucose=fg;
        String nTime=time;
        boolean ok=false;
        try{
            getConnection();
            String sql="UPDATE Diabetes SET fGlucose=?,fgTime=? WHERE id=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(3,UID);
            pstmt.setString(2,nTime);
            pstmt.setString(1,fGlucose);
            int check=0;
            check=pstmt.executeUpdate();
            if(check!=0)
            {
                ok=true;
            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return ok;
    }

    public boolean updateOneHrGlucose(int id, String oneg,String time)
    {
        int UID=id;
        String oneGlucose=oneg;
        String nTime=time;
        boolean ok=false;
        try{
            getConnection();
            String sql="UPDATE Diabetes SET onehrglucose=?,oneHrTime=? WHERE id=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(3,UID);
            pstmt.setString(2,nTime);
            pstmt.setString(1,oneGlucose);
            int check=0;
            check=pstmt.executeUpdate();
            if(check!=0)
            {
                ok=true;
            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return ok;
    }

    public boolean updateTwoGlucose(int id, String tg,String time)
    {
        int UID=id;
        String tGlucose=tg;
        String nTime=time;
        boolean ok=false;
        try{
            getConnection();
            String sql="UPDATE Diabetes SET twohrglucose=?,twoHrTime=? WHERE id=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(3,UID);
            pstmt.setString(2,nTime);
            pstmt.setString(1,tGlucose);
            int check=0;
            check=pstmt.executeUpdate();
            if(check!=0)
            {
                ok=true;
            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return ok;
    }

    public boolean checkExists(int id)
    {
        boolean ok =false;
        int UID=id;

        try {
            getConnection();
            String sql="SELECT * FROM Diabetes WHERE id=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,UID);
            rs=pstmt.executeQuery();
            while(rs.next())
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

}
