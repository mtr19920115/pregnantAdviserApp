package com.ming.pregnancyadviserapp.Database;

import com.ming.pregnancyadviserapp.Instance.AddVisiting;

import java.util.ArrayList;
import java.util.List;

public class AddVisitDAO extends DbOpenHelper {
    public boolean addAddVisit(AddVisiting instance)
    {
        boolean ok=false;
        try{
            getConnection();
            String sql="INSERT INTO addVisit(id,visitNumber,visitType,visitDate,visitTime) VALUES(?,?,?,?,?)";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,instance.getId());
            pstmt.setInt(2,instance.getVisitNumber());
            pstmt.setString(3,instance.getVisitType());
            pstmt.setString(4,instance.getVisitDate());
            pstmt.setString(5,instance.getVisitTime());
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

    public List<AddVisiting> getAddVisit(int id)
    {
        List<AddVisiting> list=new ArrayList<>();
        int UID=id;
        try{
            getConnection();
            String sql="SELECT * FROM addVisit WHERE id=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,UID);
            rs=pstmt.executeQuery();
            while(rs.next())
            {
                AddVisiting instance=new AddVisiting();
                instance.setId(rs.getInt("id"));
                instance.setVisitNumber(rs.getInt("visitNumber"));
                instance.setVisitType(rs.getString("visitType"));
                instance.setVisitDate(rs.getString("visitDate"));
                instance.setVisitTime(rs.getString("visitTime"));
                list.add(instance);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }

        return list;
    }

    public boolean deleteVisit(int id,int visitNumber)
    {
        boolean ok=false;
        try{
            getConnection();
            String sql="DELETE FROM addVisit WHERE id=? AND visitNumber=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            pstmt.setInt(2,visitNumber);
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

        return ok;
    }
}
