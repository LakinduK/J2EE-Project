
package com.mvc.dao;

import com.mvc.bean.loginBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class loginDao {
    public String authorizelogin(loginBean loginbean)
    {
        String username = loginbean.getUsername();
        String password = loginbean.getPassword();
        
        String dbusername="";
        String dbpassword="";
        
        String url ="jdbc:mysql://localhost:3306/events";
        String name="root";
        String passwrd = "";
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, name, passwrd);
            
            PreparedStatement psmt = null;
            
            psmt=con.prepareStatement("SELECT * FROM user WHERE UEmail=? and Password=?");
            psmt=setString(1,username);
            psmt=setString(2,password);
            ResultSet rs=psmt.executeQuery();
            
            while(rs.next())
            {
                dbusername=rs.getString("username");
                dbpassword=rs.getString("password");
                
                if(username.equals(dbusername) && password.equals(dbpassword))
                {
                    return "SUCCESS LOGIN";
                }
            }
            psmt.close();
            
            con.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return "Wrong username and password";
    }

    private PreparedStatement setString(int i, String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
