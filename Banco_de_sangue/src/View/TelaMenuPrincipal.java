
package View;

import Controller.ControllerTelaMenuPrincipal;
import Model.ModelFuncionario;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Tela inicial do sistema que apresenta o menu principal de ações
 * 
 * @author - Ruan Pereira
 * @since  - 18/12/2023
 */
public class TelaMenuPrincipal extends javax.swing.JFrame {

    private ControllerTelaMenuPrincipal controller;
    
    /**
     * Creates new form MenuPrincipal
     * @param funcionario
     */
    public TelaMenuPrincipal(ModelFuncionario funcionario) {
        initComponents();
        this.controller = new ControllerTelaMenuPrincipal(this, funcionario);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMenuPrincipal = new javax.swing.JPanel();
        buttonCadastrarDoacao = new javax.swing.JButton();
        buttonPermissoes = new javax.swing.JButton();
        buttonBancoSangue = new javax.swing.JButton();
        buttonRealizarDoacao = new javax.swing.JButton();
        buttonRelatarProblema = new javax.swing.JButton();
        labelBoasVindas = new javax.swing.JLabel();
        labelNomeUsuario = new javax.swing.JLabel();
        labelLogadoComo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelMenuPrincipal.setBackground(new java.awt.Color(153, 204, 255));

        buttonCadastrarDoacao.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        buttonCadastrarDoacao.setForeground(new java.awt.Color(0, 0, 0));
        buttonCadastrarDoacao.setText("Cadastrar Doação");
        buttonCadastrarDoacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCadastrarDoacaoActionPerformed(evt);
            }
        });

        buttonPermissoes.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        buttonPermissoes.setForeground(new java.awt.Color(0, 0, 0));
        buttonPermissoes.setText("Permissões");
        buttonPermissoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPermissoesActionPerformed(evt);
            }
        });

        buttonBancoSangue.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        buttonBancoSangue.setForeground(new java.awt.Color(0, 0, 0));
        buttonBancoSangue.setText("Banco de Sangue");
        buttonBancoSangue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBancoSangueActionPerformed(evt);
            }
        });

        buttonRealizarDoacao.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        buttonRealizarDoacao.setForeground(new java.awt.Color(0, 0, 0));
        buttonRealizarDoacao.setText("Doar Para Paciente");
        buttonRealizarDoacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRealizarDoacaoActionPerformed(evt);
            }
        });

        buttonRelatarProblema.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        buttonRelatarProblema.setForeground(new java.awt.Color(0, 0, 0));
        buttonRelatarProblema.setText("Relatar Problema");
        buttonRelatarProblema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRelatarProblemaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMenuPrincipalLayout = new javax.swing.GroupLayout(panelMenuPrincipal);
        panelMenuPrincipal.setLayout(panelMenuPrincipalLayout);
        panelMenuPrincipalLayout.setHorizontalGroup(
            panelMenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuPrincipalLayout.createSequentialGroup()
                .addGap(322, 322, 322)
                .addGroup(panelMenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonBancoSangue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonCadastrarDoacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonPermissoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonRealizarDoacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonRelatarProblema, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(333, Short.MAX_VALUE))
        );
        panelMenuPrincipalLayout.setVerticalGroup(
            panelMenuPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuPrincipalLayout.createSequentialGroup()
                .addContainerGap(86, Short.MAX_VALUE)
                .addComponent(buttonBancoSangue, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonCadastrarDoacao, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonRealizarDoacao, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonPermissoes, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonRelatarProblema, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(182, 182, 182))
        );

        labelBoasVindas.setBackground(new java.awt.Color(255, 255, 255));
        labelBoasVindas.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        labelBoasVindas.setForeground(new java.awt.Color(0, 0, 0));
        labelBoasVindas.setText("Boas vindas ao Banco de Sangue");

        labelNomeUsuario.setBackground(new java.awt.Color(0, 0, 0));
        labelNomeUsuario.setFont(new java.awt.Font("Courier New", 1, 10)); // NOI18N
        labelNomeUsuario.setForeground(new java.awt.Color(0, 0, 0));
        labelNomeUsuario.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        labelNomeUsuario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        labelLogadoComo.setBackground(new java.awt.Color(255, 0, 0));
        labelLogadoComo.setFont(new java.awt.Font("Courier New", 1, 10)); // NOI18N
        labelLogadoComo.setForeground(new java.awt.Color(255, 0, 0));
        labelLogadoComo.setText("Logado Como:");
        labelLogadoComo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        labelLogadoComo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelMenuPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelBoasVindas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelNomeUsuario)
                            .addComponent(labelLogadoComo))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelBoasVindas))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(labelLogadoComo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelNomeUsuario)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelMenuPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCadastrarDoacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCadastrarDoacaoActionPerformed
        TelaCadastroDoacao telaCadastroDoacao = new TelaCadastroDoacao(new TelaBancoSangue(this.getController().getFuncionarioPermissao()));
        telaCadastroDoacao.setVisible(true);
    }//GEN-LAST:event_buttonCadastrarDoacaoActionPerformed

    private void buttonPermissoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPermissoesActionPerformed
        TelaPermissoes telaPermissoes = new TelaPermissoes();
        telaPermissoes.setVisible(true);
    }//GEN-LAST:event_buttonPermissoesActionPerformed

    private void buttonBancoSangueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBancoSangueActionPerformed
        TelaBancoSangue telaBancoSangue = new TelaBancoSangue(this.getController().getFuncionarioPermissao());
        telaBancoSangue.setVisible(true);
    }//GEN-LAST:event_buttonBancoSangueActionPerformed

    private void buttonRealizarDoacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRealizarDoacaoActionPerformed
        TelaRealizaTransfusao telaRealizaTransfusao = new TelaRealizaTransfusao(new TelaBancoSangue(this.getController().getFuncionarioPermissao()));
        telaRealizaTransfusao.setVisible(true);
    }//GEN-LAST:event_buttonRealizarDoacaoActionPerformed

    private void buttonRelatarProblemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRelatarProblemaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonRelatarProblemaActionPerformed

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
            java.util.logging.Logger.getLogger(TelaMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaMenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBancoSangue;
    private javax.swing.JButton buttonCadastrarDoacao;
    private javax.swing.JButton buttonPermissoes;
    private javax.swing.JButton buttonRealizarDoacao;
    private javax.swing.JButton buttonRelatarProblema;
    private javax.swing.JLabel labelBoasVindas;
    private javax.swing.JLabel labelLogadoComo;
    private javax.swing.JLabel labelNomeUsuario;
    private javax.swing.JPanel panelMenuPrincipal;
    // End of variables declaration//GEN-END:variables

    public ControllerTelaMenuPrincipal getController() {
        return controller;
    }

    public void setController(ControllerTelaMenuPrincipal controller) {
        this.controller = controller;
    }

    public JButton getButtonBancoSangue1() {
        return buttonBancoSangue;
    }

    public void setButtonBancoSangue1(JButton buttonBancoSangue1) {
        this.buttonBancoSangue = buttonBancoSangue1;
    }

    public JButton getButtonCadastrarDoacao() {
        return buttonCadastrarDoacao;
    }

    public void setButtonCadastrarDoacao(JButton buttonCadastrarDoacao) {
        this.buttonCadastrarDoacao = buttonCadastrarDoacao;
    }

    public JButton getButtonPermissoes() {
        return buttonPermissoes;
    }

    public void setButtonPermissoes(JButton buttonPermissoes) {
        this.buttonPermissoes = buttonPermissoes;
    }

    public JButton getButtonRealizarDoacao() {
        return buttonRealizarDoacao;
    }

    public void setButtonRealizarDoacao(JButton buttonRealizarDoacao) {
        this.buttonRealizarDoacao = buttonRealizarDoacao;
    }

    public JLabel getLabelBoasVindas() {
        return labelBoasVindas;
    }

    public void setLabelBoasVindas(JLabel labelBoasVindas) {
        this.labelBoasVindas = labelBoasVindas;
    }

    public JLabel getLabelLogadoComo() {
        return labelLogadoComo;
    }

    public void setLabelLogadoComo(JLabel labelLogadoComo) {
        this.labelLogadoComo = labelLogadoComo;
    }

    public JLabel getLabelNomeUsuario() {
        return labelNomeUsuario;
    }

    public void setLabelNomeUsuario(JLabel labelNomeUsuario) {
        this.labelNomeUsuario = labelNomeUsuario;
    }

    public JPanel getPanelMenuPrincipal() {
        return panelMenuPrincipal;
    }

    public void setPanelMenuPrincipal(JPanel panelMenuPrincipal) {
        this.panelMenuPrincipal = panelMenuPrincipal;
    }

    
}