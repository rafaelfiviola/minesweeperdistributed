package mineSweeper.jogo;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mineSweeper.jogo.MineFrame;
import mineSweeper.jogo.MineFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author laser
 */
public class TelaLoginFrame extends javax.swing.JFrame {

    private String nick;

    /**
     * Creates new form TelaLoginFrame
     */
    public TelaLoginFrame() {
        initComponents();
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEntrarJogo = new javax.swing.JButton();
        btnCriarJogo = new javax.swing.JButton();
        lblNick = new javax.swing.JLabel();
        txtNick = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnEntrarJogo.setText("Entrar em um jogo...");
        btnEntrarJogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEntrarJogoMouseClicked(evt);
            }
        });
        btnEntrarJogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarJogoActionPerformed(evt);
            }
        });

        btnCriarJogo.setText("Criar um novo jogo...");
        btnCriarJogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCriarJogoMouseClicked(evt);
            }
        });
        btnCriarJogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCriarJogoActionPerformed(evt);
            }
        });

        lblNick.setText("Nickname");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNick)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEntrarJogo)
                    .addComponent(btnCriarJogo)
                    .addComponent(txtNick, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNick)
                    .addComponent(txtNick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnCriarJogo)
                .addGap(11, 11, 11)
                .addComponent(btnEntrarJogo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCriarJogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCriarJogoMouseClicked
        // TODO add your handling code here:
        nick = txtNick.getText();
        try {
            //No caso de host ñ é necessário passar ip remoto
            new MineFrame(true, null); //inicia como servidor
        } catch (NotBoundException ex) {
            Logger.getLogger(TelaLoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(TelaLoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(TelaLoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
    }//GEN-LAST:event_btnCriarJogoMouseClicked

    private void btnEntrarJogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEntrarJogoMouseClicked
        // TODO add your handling code here:
        nick = txtNick.getText();
        new ListarServidores();
        this.setVisible(false);
    }//GEN-LAST:event_btnEntrarJogoMouseClicked

    private void btnCriarJogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCriarJogoActionPerformed
        try {
            // TODO add your handling code here:
            new MineFrame(true, null);
        } catch (NotBoundException ex) {
            Logger.getLogger(TelaLoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(TelaLoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(TelaLoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCriarJogoActionPerformed

    private void btnEntrarJogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarJogoActionPerformed
        // TODO add your handling code here:
        new ListarServidores();
    }//GEN-LAST:event_btnEntrarJogoActionPerformed

    public static void main(String[] args) {
        TelaLoginFrame tela = new TelaLoginFrame();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCriarJogo;
    private javax.swing.JButton btnEntrarJogo;
    private javax.swing.JLabel lblNick;
    private javax.swing.JTextField txtNick;
    // End of variables declaration//GEN-END:variables
}
