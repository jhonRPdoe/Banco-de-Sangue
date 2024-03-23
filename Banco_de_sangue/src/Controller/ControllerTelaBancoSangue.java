
package Controller;

import Model.ModelBancoSangue;
import Model.ModelFuncionarioPermissao;
import Persistencia.PersistenciaBancoSangue;
import View.TelaBancoSangue;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 * Classe responsável pelas regras e negócio da tela do banco de sangue
 * 
 * @author - Ruan Pereira
 * @since  - 30/12/2023
 */
public class ControllerTelaBancoSangue {
    
    private final TelaBancoSangue telaBancoSangue;
    private final PersistenciaBancoSangue persistenciaBancoSangue;
    private final ModelFuncionarioPermissao funcionarioPermissao;
    
    public ControllerTelaBancoSangue(TelaBancoSangue telaBancoSangue, ModelFuncionarioPermissao funcionarioPermissao) {
        this.telaBancoSangue = telaBancoSangue;
        this.persistenciaBancoSangue = new PersistenciaBancoSangue();
        this.funcionarioPermissao = funcionarioPermissao;
        this.validaPermissaoUsuario();
        this.atualizarTabela();
    }
    
    public final void validaPermissaoUsuario() {    
        if (this.getFuncionarioPermissao().getPermissao().getTipo() == 1) {
            this.getTelaBancoSangue().getButtonCadastrarDoacao().setVisible(false);
            this.getTelaBancoSangue().getButtonRealizarDoacao().setVisible(false);
        }
    }
    
    /*
     * Definição do vetor com os títulos de cada coluna da tabela
     */
    private final String[] colunasTabela = {"ID", "Tipo", "Quantidade"};
    
    /*
     * Atualiza a tabela toda vez que inserida, removida ou alterada alguma informação
     */
    public final void atualizarTabela(){
        ArrayList<ModelBancoSangue> listaBancoSangue = this.getPersistenciaBancoSangue().selectAll();
        ModelBancoSangue bancoSangue = this.getBancoSangue();
        DefaultTableModel modelTabela = this.getModelTabela();
        modelTabela.setNumRows(0);

        for (int i = 0; i < listaBancoSangue.size(); i++) {
            bancoSangue = listaBancoSangue.get(i);

            String[] rowData = {
                                String.valueOf(bancoSangue.getCodigo()),
                                bancoSangue.getTipo(),
                                this.formataPontuacaoDouble(String.valueOf(bancoSangue.getQuantidade()))
                               };
            
            modelTabela.addRow(rowData);
        }
        
        this.getTelaBancoSangue().getTableBancoSangue().setModel(modelTabela);     
    }
    
    /**
     * Corrige a pontuação de um número Double e o retorna como String
     * @param numero
     * @return String numeroCorrigido
     */
    private String formataPontuacaoDouble(String numero) {
        String numeroCorrigido;
        if (numero.contains(".")) {
            numeroCorrigido = numero.replace(".", ",");
        } else {
            numeroCorrigido = numero;
        }
        
        return numeroCorrigido;
    }
    
    public ModelBancoSangue getBancoSangue() {
        return new ModelBancoSangue();
    }

    public TelaBancoSangue getTelaBancoSangue() {
        return telaBancoSangue;
    }

    public PersistenciaBancoSangue getPersistenciaBancoSangue() {
        return persistenciaBancoSangue;
    }

    public ModelFuncionarioPermissao getFuncionarioPermissao() {
        return funcionarioPermissao;
    }
    
    /**
     * Retorna o model padrão da tableBancoSangue
     * @return DefaultTableModel modelTabela
     */
    public DefaultTableModel getModelTabela() {
        DefaultTableModel modelTabela = new DefaultTableModel(this.colunasTabela, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Torna todas as células não editáveis
                return false;
            }
        };
        return modelTabela;
    }
    
}
