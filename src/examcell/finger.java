/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examcell;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
/**
 *
 * @author xrest
 */
public class finger {
    
    public static void open()  {
  
    
  try {
   
   URI uri= new URI("http://localhost/biometricattendancev/index.php");
   
   java.awt.Desktop.getDesktop().browse(uri); 
   System.out.println("Web page opened in browser");
 
  } catch (Exception e) {
   
   e.printStackTrace();
  }
 }
    
}
