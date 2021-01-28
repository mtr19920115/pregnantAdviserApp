package com.ming.pregnancyadviserapp.Database;

import com.ming.pregnancyadviserapp.Instance.BloodPressure;

import java.util.ArrayList;
import java.util.List;

public class BloodPressureDAO extends DbOpenHelper {

    public boolean addBloodPressure(BloodPressure bloodPressure)
    {
        boolean ok=false;

        try{
            getConnection();
            String sql="INSERT INTO BloodPressure(id,highBloodPressure,lowBloodPressure,timeStamper) VALUES(?,?,?,?)";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,bloodPressure.getId());
            pstmt.setInt(2,bloodPressure.getHighBloodPressure());
            pstmt.setInt(3,bloodPressure.getLowBloodPressure());
            pstmt.setString(4,bloodPressure.getTimeStamper());
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

    public List<BloodPressure> getBloodPressure(int id)
    {
        List<BloodPressure> bloodPressures=new ArrayList<>();
        try {
            getConnection();
            String sql = "Select * From BloodPressure Where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                BloodPressure bloodPressure=new BloodPressure();
                bloodPressure.setId(rs.getInt("id"));
                bloodPressure.setHighBloodPressure(rs.getInt("highBloodPressure"));
                bloodPressure.setLowBloodPressure(rs.getInt("lowBloodPressure"));
                bloodPressure.setTimeStamper(rs.getString("timeStamper"));
                bloodPressures.add(bloodPressure);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }

        return bloodPressures;
    }
}
