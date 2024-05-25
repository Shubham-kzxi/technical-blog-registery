
package com.tech.blog.Dao;

import com.tech.blog.entities.User;
import java.sql.*;
public class UserDao {
    private Connection con;
    
    public UserDao(Connection con)
    {
        this.con=con;
    }
    
    // method to insert user to data base
    
    public boolean saveUser(User user)
    {
        boolean f=false;
       try{
           // user-->databse
           
           String query="insert into user(name,email,password,gender,about) values (?,?,?,?,?)";
           PreparedStatement pstmt=this.con.prepareStatement(query);
           pstmt.setString(1, user.getName());
           pstmt.setString(2, user.getEmail());
           pstmt.setString(3, user.getPassword());
           pstmt.setString(4, user.getGender());
           pstmt.setString(5, user.getAbout());
           
           pstmt.executeUpdate();
           f=true;
       }   
       catch(Exception e)
       {
           e.printStackTrace();
       }
       return f;
    }
    
    ///get user by useremail and userpassword
    
    public User getUserByEmailAndPassword(String email, String password) {
        
        User user=null;
        
        try{
            String query="select * from user where email=? and password=?";
            PreparedStatement ptmt=con.prepareStatement(query);
            ptmt.setString(1, email);
            ptmt.setString(2, password);
            
            ResultSet rs=ptmt.executeQuery();
            
            if(rs.next())
            {
                user=new User();
                
                //data from db
                String name=rs.getString("name");
                //set to user object
                user.setName(name);
                
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setGender(rs.getString("gender"));
                user.setAbout(rs.getString("about"));
                user.setDateTime(rs.getTimestamp("rdate"));
                user.setProfile(rs.getString("profile"));
            }
            else
            {
                
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return user;
    }
}
