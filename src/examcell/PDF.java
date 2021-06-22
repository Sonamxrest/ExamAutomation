/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examcell;
import java.io.FileOutputStream;
import java.io.*;
import java.util.*;
import java.sql.*; 
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
/**
 *
 * @author xrest
 */
public class PDF {
    
    public  void pdf()
    {
    
    Connection conn = ExamCell.getCon();
    try{
   Statement stmt = conn.createStatement();
                /* Define the SQL query */
                      ResultSet query_set = stmt.executeQuery("SELECT * from seat");
                      
                /* Step-2: Initialize PDF documents - logical objects */
                Document my_pdf_report = new Document();
                PdfWriter.getInstance(my_pdf_report, new FileOutputStream("Seat_Plan.pdf"));
                my_pdf_report.open();            
                //we have four columns in our table
                PdfPTable my_report_table = new PdfPTable(5);
               my_report_table.addCell("ID");
                my_report_table.addCell("Student_id");
                 my_report_table.addCell("FirstName");
                  my_report_table.addCell("Last Name");
                   my_report_table.addCell("Seat_No");
                   my_report_table.setHeaderRows(1);
                //create a cell object
                PdfPCell table_cell;
              
               
                while (query_set.next()) {  
                    
                   
                                         
                     
                                String id = query_set.getString(1);
                                table_cell=new PdfPCell(new Phrase(id));
                                my_report_table.addCell(table_cell);
                                 
                                String st_id=query_set.getString(2);
                                table_cell=new PdfPCell(new Phrase(st_id));
                                 my_report_table.addCell(table_cell);
                                 
                                  String name = query_set.getString(3);
                                table_cell = new PdfPCell(new Phrase(name));
                                 my_report_table.addCell(table_cell);
                                 
                                String lname = query_set.getString(4);
                                table_cell = new PdfPCell(new Phrase(lname));
                                 my_report_table.addCell(table_cell); 
                                    
                                 String manager_id=query_set.getString(5);
                                table_cell=new PdfPCell(new Phrase(manager_id));
                                my_report_table.addCell(table_cell);
                    
                }
            
                my_pdf_report.add(my_report_table);                       
                my_pdf_report.close();
                
              
                query_set.close();
                stmt.close(); 
                conn.close();  
                JOptionPane.showMessageDialog(null, "SuccessFully generated in C:\\Users\\xrest\\Documents\\NetBeansProjects\\ExamCell");
                  
}
    catch(SQLException ex)
    {
    ex.getErrorCode();
    }   catch (Exception x) {
            x.printStackTrace();
        }
    
    
    
    
    }
}
