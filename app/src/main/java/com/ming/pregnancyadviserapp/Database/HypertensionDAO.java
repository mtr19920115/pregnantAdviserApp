package com.ming.pregnancyadviserapp.Database;

import com.ming.pregnancyadviserapp.Instance.Hypertension;

public class HypertensionDAO extends DbOpenHelper {

    public Hypertension getHypertension(int id)
    {
        Hypertension instance=new Hypertension();

        try{
            getConnection();
            String sql="SELECT * FROM hypertension WHERE id=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            rs=pstmt.executeQuery();
            while (rs.next())
            {
                instance.setId(rs.getInt("id"));
                instance.setHighBloodPressure(rs.getInt("highbp"));
                instance.setLowBloodPressure(rs.getInt("lowbp"));
                instance.setLongBPMedication(rs.getInt("longBPMedication"));
                instance.setLongPregnancyMedication(rs.getInt("longPregnancyMedication"));
                instance.setLongCurrentMedication(rs.getInt("longCurrentMedication"));
                instance.setTime(rs.getString("time"));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }

        return instance;
    }

    public boolean addHypertension(Hypertension instance)
    {
        boolean ok=false;
        try{
            getConnection();
            String sql="INSERT INTO hypertension(id,highbp,lowbp,longBPMedication,longPregnancyMedication,longCurrentMedication,time) VALUES(?,?,?,?,?,?,?)";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,instance.getId());
            pstmt.setInt(2,instance.getHighBloodPressure());
            pstmt.setInt(3,instance.getLowBloodPressure());
            pstmt.setInt(4,instance.getLongBPMedication());
            pstmt.setInt(5,instance.getLongPregnancyMedication());
            pstmt.setInt(6,instance.getLongCurrentMedication());
            pstmt.setString(7,instance.getTime());
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

    public boolean updateBP (int id,int highBp,int lowBp,String time)
    {
        boolean ok=false;
        int uId=id;
        int nhp=highBp;
        int nlp=lowBp;
        String ntime=time;


        try {
            getConnection();
            String sql="UPDATE hypertension SET highbp=?,lowbp=?,time=? WHERE id=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,nhp);
            pstmt.setInt(2,nlp);
            pstmt.setString(3,ntime);
            pstmt.setInt(4,uId);
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

    public boolean deleteHypertension(int id)
    {
        boolean ok=false;
        int uid=id;
        try {
            getConnection();
            String sql="DELETE FROM hypertension WHERE id=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,uid);
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
        boolean ok=false;
        int UID=id;

        try {
            getConnection();
            String sql="SELECT * FROM hypertension WHERE id=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,UID);
            rs=pstmt.executeQuery();
            while (rs.next())
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
