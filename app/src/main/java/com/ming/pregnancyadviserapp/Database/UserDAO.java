package com.ming.pregnancyadviserapp.Database;


import com.ming.pregnancyadviserapp.Instance.User;


import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 *user db operation
 */

public class UserDAO extends DbOpenHelper {

    //get all users
    public List<User> getAllUserList(){
        List<User> list=new ArrayList<>();

        try{
            getConnection();
            String sql="SELECT * FROM users ";
            pstmt= (PreparedStatement) conn.prepareStatement(sql);
            rs=pstmt.executeQuery();
            if(rs.next())
            {
               User instance=new User();
                instance.setId(rs.getInt("id"));
                instance.setUserName(rs.getString("userName"));
                instance.setPassWord(rs.getString("password"));
                instance.setZipCode(rs.getInt("zipcode"));
                instance.setFirstName(rs.getString("firstname"));
                instance.setLastName(rs.getString("lastname"));

                list.add(instance);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }

        return list;
    }

    //get user by userName and passWord, return User instance
    public User getUser(String userName,String passWord)
    {
        User instance=null;
        try{
            getConnection();
            String sql="SELECT * FROM users WHERE userName=? AND password=?";
            pstmt= (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1,userName);
            pstmt.setString(2,passWord);
            rs=pstmt.executeQuery();
            if(rs.next())
            {
                instance=new User();
                instance.setId(rs.getInt("id"));
                instance.setUserName(rs.getString("userName"));
                instance.setPassWord(rs.getString("password"));
                instance.setZipCode(rs.getInt("zipcode"));
                instance.setFirstName(rs.getString("firstname"));
                instance.setLastName(rs.getString("lastname"));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return instance;
    }


    public User getUserById(int id)
    {
        User instance=null;
        try{
            getConnection();
            String sql="SELECT * FROM users WHERE id=?";
            pstmt= (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            rs=pstmt.executeQuery();
            if(rs.next())
            {
                instance=new User();
                instance.setId(rs.getInt("id"));
                instance.setUserName(rs.getString("userName"));
                instance.setPassWord(rs.getString("password"));
                instance.setZipCode(rs.getInt("zipcode"));
                instance.setFirstName(rs.getString("firstname"));
                instance.setLastName(rs.getString("lastname"));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }
        return instance;
    }

    //add user to db
    public int addUser(User instance)
    {
        int iRow=0;

        try{
            getConnection();
            String sql="INSERT INTO users(userName,password,zipcode,firstname,lastname) VALUES (?,?,?,?,?)";
            pstmt= (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1,instance.getUserName());
            pstmt.setString(2,instance.getPassWord());
            pstmt.setInt(3,instance.getZipCode());
            pstmt.setString(4,instance.getFirstName());
            pstmt.setString(5,instance.getLastName());
            iRow=pstmt.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }

        return iRow;
    }

    public boolean checkExists(String userName)
    {
        boolean exists=false;

        try{
            getConnection();
            String sql="SELECT * FROM users WHERE userName=?";
            pstmt= (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1,userName);
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


    public int updateUser(User instance)
    {

        int iRow=0;
        try{
            getConnection();
            String sql="UPDATE users SET userName=?, zipcode=? WHERE id=?";
            pstmt= (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1,instance.getUserName());
            pstmt.setInt(2,instance.getZipCode());
            pstmt.setInt(3,instance.getId());
            iRow=pstmt.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeAll();
        }



        return iRow;
    }

    /**
     * delete user by id
     * @param id
     * @return
     */
    public int deleteUser(int id)
    {
        int iRow=0;
        try{
            getConnection();
            String sql="DELETE FROM users WHERE id=?";
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
