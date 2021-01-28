package com.ming.pregnancyadviserapp.Database;

import com.ming.pregnancyadviserapp.Instance.PregnancyHistory;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class PregnancyHistoryDAO extends DbOpenHelper {
    public List<PregnancyHistory> getAllPH(int id)
    {
        List<PregnancyHistory> list=new ArrayList<>();

        try{
            getConnection();
            String sql="SELECT * FROM pHistory WHERE id=?";
            pstmt=(PreparedStatement) conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            rs=pstmt.executeQuery();
            while(rs.next())
            {
                PregnancyHistory instance=new PregnancyHistory();
                instance.setId(rs.getInt("id"));
                instance.setpNumber(rs.getInt("pNumber"));
                instance.setDeliverDate(rs.getString("DeliveryDate"));
                instance.setWayDeliver(rs.getString("wayDelivery"));
                instance.setPree(rs.getString("pree"));
                instance.setBa(rs.getString("ba"));
                instance.setBf37(rs.getString("bf37"));
                instance.setBabyWeight(rs.getString("babyWeight"));
                list.add(instance);
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }

        return list;
    }


    public boolean addPH(PregnancyHistory instance)
    {
        boolean ok=false;

        try{
            getConnection();
            String sql="INSERT INTO pHistory(id,pNumber,DeliveryDate,wayDelivery,pree,ba,bf37,babyWeight) VALUES(?,?,?,?,?,?,?,?)";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,instance.getId());
            pstmt.setInt(2,instance.getpNumber());
            pstmt.setString(3,instance.getDeliverDate());
            pstmt.setString(4,instance.getWayDeliver());
            pstmt.setString(5,instance.getPree());
            pstmt.setString(6,instance.getBa());
            pstmt.setString(7,instance.getBf37());
            pstmt.setString(8,instance.getBabyWeight());
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




    public boolean deletePH(int id)
    {
        boolean ok=false;
        try{
            getConnection();
            String sql="DELETE FROM pHistory WHERE id=? ";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,id);
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

    public boolean deletePHbyIdPNumber(int id,int pNumber)
    {
        boolean ok=false;
        try{
            getConnection();
            String sql="DELETE FROM pHistory WHERE id=? AND pNumber=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            pstmt.setInt(2,pNumber);
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
        boolean ok =false;
        int UID=id;

        try {
            getConnection();
            String sql="SELECT * FROM pHistory WHERE id=?";
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
