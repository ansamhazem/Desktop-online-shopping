/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stocker;

import clinet.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Ansam
 */
public class ItemDetails extends javax.swing.JFrame {

    /**
     * Creates new form ItemDetails
     */
    public ArrayList details=new ArrayList();
    public ItemDetails(ArrayList<Vector> d,int expired) {
        initComponents();
        String name=d.get(0).get(0).toString();
        String price=d.get(0).get(2).toString();
        String Cate=d.get(0).get(1).toString();
        ItemCatogery.setText(Cate);
        ItemName.setText(name);
        ItemPrice.setText(price);
        if (expired ==2)
        {
            addQuatity.setVisible(false);
            edit.setVisible(false);
        }
        else if (expired==1)
        { kill.setVisible(false);
            edit.setVisible(false);
        }
        else
        {
            addQuatity.setVisible(false);
            kill.setVisible(false);
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

        jPanel3 = new javax.swing.JPanel();
        Name = new javax.swing.JLabel();
        kill = new javax.swing.JButton();
        Catogery = new javax.swing.JLabel();
        ItemCatogery = new javax.swing.JLabel();
        ItemName = new javax.swing.JLabel();
        Price = new javax.swing.JLabel();
        ItemPrice = new javax.swing.JLabel();
        Catogery1 = new javax.swing.JLabel();
        ItemCatogery1 = new javax.swing.JLabel();
        addQuatity = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 51, 102));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Name.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        Name.setForeground(new java.awt.Color(255, 255, 255));
        Name.setText("Name :");
        jPanel3.add(Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 60, 30));

        kill.setBackground(new java.awt.Color(255, 204, 153));
        kill.setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N
        kill.setText("Kill");
        kill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                killActionPerformed(evt);
            }
        });
        jPanel3.add(kill, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 120, 40));

        Catogery.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        Catogery.setForeground(new java.awt.Color(255, 255, 255));
        Catogery.setText("Description :");
        jPanel3.add(Catogery, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 100, 30));

        ItemCatogery.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        ItemCatogery.setForeground(new java.awt.Color(255, 255, 255));
        ItemCatogery.setText("jLabel11");
        jPanel3.add(ItemCatogery, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 270, -1, -1));

        ItemName.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        ItemName.setForeground(new java.awt.Color(255, 255, 255));
        ItemName.setText("jLabel11");
        jPanel3.add(ItemName, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 130, -1, -1));

        Price.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        Price.setForeground(new java.awt.Color(255, 255, 255));
        Price.setText("Price :");
        jPanel3.add(Price, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 50, 30));

        ItemPrice.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        ItemPrice.setForeground(new java.awt.Color(255, 255, 255));
        ItemPrice.setText("jLabel11");
        jPanel3.add(ItemPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 180, -1, -1));

        Catogery1.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        Catogery1.setForeground(new java.awt.Color(255, 255, 255));
        Catogery1.setText("Catogery :");
        jPanel3.add(Catogery1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 80, 30));

        ItemCatogery1.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        ItemCatogery1.setForeground(new java.awt.Color(255, 255, 255));
        ItemCatogery1.setText("jLabel11");
        jPanel3.add(ItemCatogery1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 220, -1, -1));

        addQuatity.setBackground(new java.awt.Color(255, 204, 153));
        addQuatity.setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N
        addQuatity.setText("Add Quantity");
        addQuatity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addQuatityActionPerformed(evt);
            }
        });
        jPanel3.add(addQuatity, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 120, 40));

        edit.setBackground(new java.awt.Color(255, 204, 153));
        edit.setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N
        edit.setText("Edit");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });
        jPanel3.add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 120, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1492585871_product-sales-report.png"))); // NOI18N
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 570, 450));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void killActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_killActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_killActionPerformed

    private void addQuatityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addQuatityActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new AddQuantity().setVisible(true);
    }//GEN-LAST:event_addQuatityActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new AddItem().setVisible(true);
    }//GEN-LAST:event_editActionPerformed

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Catogery;
    private javax.swing.JLabel Catogery1;
    private javax.swing.JLabel ItemCatogery;
    private javax.swing.JLabel ItemCatogery1;
    private javax.swing.JLabel ItemName;
    private javax.swing.JLabel ItemPrice;
    private javax.swing.JLabel Name;
    private javax.swing.JLabel Price;
    private javax.swing.JButton addQuatity;
    private javax.swing.JButton edit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton kill;
    // End of variables declaration//GEN-END:variables
}