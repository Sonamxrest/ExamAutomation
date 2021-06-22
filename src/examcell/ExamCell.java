/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examcell;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xrest
 */
public class ExamCell {
    
    static Connection con=null;
    public static Connection getCon()
    {
        try {
            java.lang.Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ExamCell.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/examcell","root","");
        } catch (SQLException ex) {
            Logger.getLogger(ExamCell.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.print("Success");
        return con;
        
    }
    
    public static ResultSet getResult(String s) throws SQLException
    {
        Connection con=getCon();
        PreparedStatement stm=con.prepareStatement(s);
        
        ResultSet rs=stm.executeQuery(s);
        return rs;
        
    }
    
    public static void main(String argv[])
    {
        getCon();
    }
    
}
