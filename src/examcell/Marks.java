/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examcell;

import java.awt.Component;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author xrest
 */
public class Marks extends javax.swing.JFrame {

    Stable s = new Stable();
    /**
     * Creates new form Marks
     */
    int x = 0;
    String g = "";
    String ins = "";
    String select = "";
    String alt2 = "";
    JLabel l[];
    String drop = "";
    String update = "";
    int ll = 0;
   TextField j[];
    public static List<String> a = new ArrayList();
    public static List<String> b = new ArrayList();
    Connection conn = ExamCell.getCon();
    int val = 0;
    int axd = 0;
    int act = 0;

    public Marks() {

        initComponents();
        for (int i = 1; i <= 10; i++) {
            choice1.add(Integer.toString(i));
        }

    }

    void q() {
sub(a);
        String num = choice1.getSelectedItem();

        try {

            String xxx = "SELECT count(*) FROM information_schema.columns WHERE table_name = 'marks" + num + "'";
            ResultSet x = ExamCell.getResult(xxx);
            if (x.next()) {
                axd = x.getInt(1) - 2;
                act = x.getInt(1);
            }
            ResultSet s = ExamCell.getResult("Select count(*) from student where class_id=" + choice1.getSelectedItem() + "");
            if (s.next()) {
                val = s.getInt(1);
            }
        } catch (SQLException x) {
            x.getErrorCode();
        }
        if (a.isEmpty()) {
            JOptionPane.showMessageDialog(null, "NO subjects");
        }
        String alter = "Alter table marks" + num + " Add " + a.get(0) + " " + "Varchar(30) After student_id,";
        ins = "Insert into marks" + num + " values(?,?,";

        drop = "Alter table marks" + num + " Drop ";
        select = "Select" + " ";

        if (val == a.size()) {

            if (act < a.size() + 2) {
                if (axd == 0 || axd < 2) {
                } else {
                    alt2 = "Alter table marks" + num + " Add " + a.get(axd) + " " + "Varchar(30) After " + a.get(axd - 1) + "," + " ";
                    for (int i = axd + 1; i < a.size(); i++) {

                        if (i + 1 < a.size()) {
                            alt2 += "Add " + a.get(i) + " Varchar(30),";
                        }
                        if (i + 1 == a.size()) {
                            alt2 = alt2.substring(0, alt2.length() - 1);
                            alt2 = alt2 + " ,Add " + a.get(i) + " Varchar(30);";
                        }
                    }
                }
            }
            for (int i = 0; i < a.size(); i++) {

                if (i + 1 < a.size()) {
                    alter = alter + "Add" + " " + a.get(i + 1) + " " + "Varchar(30),";
                    ins = ins + "?,";
                    drop = drop + a.get(i) + ",";
                    select += a.get(i) + ",";

                }
                if (i + 1 == a.size()) {
                    g = alter.substring(0, alter.length() - 1);
                    g = g + ";";

                    ins = ins + "?)";
                    drop = drop + a.get(i);
                    select = select + a.get(i) + " " + "from marks"+num+" ";
                    break;

                }
            }
        } else {

        }

    }

    public int max() {
        String q = "select count(*) from student where class_id ="+choice1.getSelectedItem()+"";
        ResultSet id;
        int maxid = 0;
        try {
            id = ExamCell.getResult(q);

            if (id.next()) {
                maxid = Integer.parseInt(id.getString(1));
            } else {
                JOptionPane.showConfirmDialog(rootPane, "No subject");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Marks.class.getName()).log(Level.SEVERE, null, ex);
        }

        return maxid;
    }

    public void sub(List<String> e) {
    
        
        
       
        j = null;
        e.clear();
        int maxid = max();

        int i = 0;
        while (i <= maxid) {
            String x = "select * from student where class_id =" + choice1.getSelectedItem() + "";

            try {
                ResultSet s = ExamCell.getResult(x);
                while (s.next()) {
                    if (e.contains(s.getString(3))) {
                    } else {
                        e.add(s.getString(3));
                    }
                    
                }
                if(e.isEmpty())
                {
                
                    JOptionPane.showMessageDialog(null,"NO subject found Please add  first");
                break;
                
                }
                
                
                
            } catch (SQLException ex) {
                Logger.getLogger(Marks.class.getName()).log(Level.SEVERE, null, ex);
            }
            i++;
        }

        l = new JLabel[e.size()];
        j = new TextField[e.size()];
        for (int x = 0; x < e.size(); x++) {
            j[x] = new TextField();
            l[x] = new JLabel(e.get(x));
            ll = l.length;  
            jPanel1.add(j[x]);
            jPanel1.add(l[x]);
            l[x].setBounds(25, 30 + 50 * x, 100, 100);
            j[x].setBounds(80, 50 + 50 * x, 150, 40);
        }
    }

    public void loadData() {
        DefaultTableModel model;
        String query = "Select * from register where class_id=" + choice1.getSelectedItem() + "";

        try {
            ResultSet rs = ExamCell.getResult(query);

            model = (DefaultTableModel) jTable1.getModel();
            model.getDataVector().removeAllElements();

            while (rs.next()) {
                model.addRow(new Object[]{rs.getString(4), rs.getString(5), rs.getString(7), rs.getString(6), rs.getString(3)});
            }
        } catch (SQLException ex) {

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        save = new java.awt.Button();
        choice1 = new java.awt.Choice();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        save.setLabel("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        jButton2.setText("Sort");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FirstName", "LastName", "DOB", "Gender","ID"
            }
        ));
        jTable1.setFocusable(false);
        jTable1.setGridColor(new java.awt.Color(255, 255, 255));
        jTable1.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTable1.setRowHeight(25);
        jTable1.setSelectionBackground(new java.awt.Color(232, 57, 95));
        jTable1.setShowVerticalLines(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 229, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 362, Short.MAX_VALUE)
        );

        jLabel1.setText("Name");

        jLabel2.setText("ID");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(114, 114, 114)
                                        .addComponent(jButton2))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(80, 80, 80)
                                        .addComponent(choice1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(365, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(259, 259, 259))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(choice1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        save.getAccessibleContext().setAccessibleName("save");

        pack();
    }// </editor-fold>//GEN-END:initComponents
  public void insert() {

        try {
            String gr = "";
            PreparedStatement as = conn.prepareStatement(ins);
            as.setString(1, jTextField1.getText());
            as.setString(2, jTextField2.getText());
            int x = 0, ind = j.length + 3;
            for (int i = 0; i < j.length;) {
                for (int col = 3; col <= j.length + 2; col++, i++) {
                    as.setString(col, j[i].getText());

                }

                as.addBatch();
            }

            as.executeBatch();
            as.executeUpdate();
         
        } catch (SQLException e) {
        }
    }

    public void alter(String x) {
        PreparedStatement aas = null;
        try {
           
                aas = conn.prepareStatement(x);

            
            int a =aas.executeUpdate();
          
        } catch (SQLException ex) {

        }
    }
      
    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed

        try {
            System.out.println(ins);
            System.out.println(ins);
          
            
                        System.out.println(act);
                                    System.out.println(alt2);
            ResultSet s = ExamCell.getResult(select);
            if (!s.next()){
       alter(g);
       insert();
            } else {
              
insert();  
                
            }
            JOptionPane.showMessageDialog(rootPane, "Value Entered");

        } catch (SQLException ex) {

        }
    }//GEN-LAST:event_saveActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int column = jTable1.getColumnCount();
        int row = jTable1.getSelectedRow();
        boolean clicked = true;
        jTextField1.setText(jTable1.getModel().getValueAt(row, 0).toString());
        jTextField2.setText(jTable1.getModel().getValueAt(row, 4).toString());

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
q();
                if(act==2)
               {
                   alter(g);
                  
               }
               else{
               alter(alt2);
            
               }
      
jPanel1.removeAll();   
sub(a);
loadData();



    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        this.hide();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Marks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Marks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Marks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Marks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Marks().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Choice choice1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private java.awt.Button save;
    // End of variables declaration//GEN-END:variables
}
