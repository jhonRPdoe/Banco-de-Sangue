
package Controller;

import Model.ModelBancoSangue;
import Model.ModelDoador;
import Persistencia.PersistenciaBancoSangue;
import Persistencia.PersistenciaDoador;
import View.TelaCadastroDoacao;
import javax.swing.JOptionPane;

/**
 * Classe responsável pelas regras e negócio da tela de cadastro de doação
 * 
 * @author - Ruan Pereira
 * @since  - 30/12/2023
 */
public class ControllerTelaCadastroDoacao {
    
    private final TelaCadastroDoacao telaCadastroDoacao;
    private final PersistenciaDoador persistenciaDoador;
    private final PersistenciaBancoSangue persistenciaBancoSangue;
    
    public ControllerTelaCadastroDoacao(TelaCadastroDoacao telaCadastroDoacao) {
        this.telaCadastroDoacao = telaCadastroDoacao;
        this.persistenciaDoador = new PersistenciaDoador();
        this.persistenciaBancoSangue = new PersistenciaBancoSangue();
    }
    
    /**
     * Realiza o cadastro da doação sangue adicionando a quantidade de sangue doada do banco de sangue
     * @return boolean retorno
     */
    public boolean cadastrar() {
        boolean retorno = false;
        try {
            if (this.getTelaCadastroDoacao().getCheckBoxIsDoador().isSelected()) {
                if (this.isDoador()) {
                    retorno = this.cadastrarDoacao();
                } else {
                    JOptionPane.showMessageDialog(null, "ATENÇÃO! Não há nenhum doador cadastrado com este cpf, verifique o valor inserido.");
                }
            } else {
                if (this.cadastrarDoador()) {
                    retorno = this.cadastrarDoacao();
                } else {
                    JOptionPane.showMessageDialog(null, "ATENÇÃO! Doador já cadastrado!");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível realizar a doação, verifique as informações inseridas.");
        }
        
        return retorno;
    }
    
    /**
     * Realiza a doação da quantidade e do tipo de sangue informado
     * @return boolean
     */
    public boolean cadastrarDoacao() {
        ModelBancoSangue bancoSangue = new ModelBancoSangue();
        bancoSangue.setCodigo(this.getTelaCadastroDoacao().getComboBoxTipoSangue().getSelectedIndex() + 1);
        bancoSangue.setQuantidade(this.formataPontuacaoDouble(this.getTelaCadastroDoacao().getInputQuantidade().getText().trim()));
        return this.getPersistenciaBancoSangue().update(bancoSangue, "+");
    }
    
    /**
     * Realiza o cadastro do doador no banco de dados
     * @return boolean retorno
     */
    private boolean cadastrarDoador() {
        boolean retorno = false;
        if (!this.isDoador()) {
            ModelDoador doador = this.getDoador();
            doador.setNome(this.getTelaCadastroDoacao().getInputNomeDoador().getText().trim());
            doador.setNascimento(this.getTelaCadastroDoacao().getInputNascimento().getText().trim());
            doador.setCpf(this.getTelaCadastroDoacao().getInputCpfDoador().getText().replaceAll("[^0-9]", "").trim());
            doador.setTelefone(this.getTelaCadastroDoacao().getInputTelefone().getText().trim());
            doador.setEmail(this.getTelaCadastroDoacao().getInputEmail().getText().trim());
            doador.setEndereco(this.getTelaCadastroDoacao().getInputEndereco().getText().trim());
            retorno = this.getPersistenciaDoador().insert(doador);
        }
        
        return retorno;
    }
    
    /**
     * Corrige a pontuação de um número caso o ele seja Double adicionando um ponto no lugar da virgula
     * @param numero
     * @return Double numeroCorrigido
     */
    private Double formataPontuacaoDouble(String numero) {
        String numeroCorrigido;
        if (numero.contains(",")) {
            numeroCorrigido = numero.replace(",", ".");
        } else {
            numeroCorrigido = numero;
        }
        
        return Double.valueOf(numeroCorrigido);
    }
    
    /**
     * Verifica se os valores inseridos nos campos são válidos
     * 
     * @return boolean isCamposValidos
     */
    public boolean validaCampos() {
        boolean isCamposValidos = true;
        if (this.emptyFields()) {
            isCamposValidos = false;
        } else if (!validaCpf.isCpfValido(this.getTelaCadastroDoacao().getInputCpfDoador().getText())) {
           isCamposValidos = false;
           JOptionPane.showMessageDialog(null, "ATENÇÃO! CPF Inválido!");
        }
        
        return isCamposValidos;
    }
    
    public boolean isDoador() {
        ModelDoador doador = this.getPersistenciaDoador().select(this.getTelaCadastroDoacao().getInputCpfDoador().getText().replaceAll("[^0-9]", "").trim());
        return (doador.getId() != null);
    }
    
    /**
     * Retorna "true" quando qualquer um dos campos e "false" quando todos estiverem preenchidos.
     * @return boolean empty
     */
    public boolean emptyFields(){
        boolean empty = true; 
        if (this.getTelaCadastroDoacao().getCheckBoxIsDoador().isSelected()) {
            if(this.getTelaCadastroDoacao().getInputCpfDoador().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "ATENÇÃO! O campo CPF deve ser preenchido.");
            } else {
                empty = false;
            }
        } else {
            if (this.getTelaCadastroDoacao().getInputNomeDoador().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "ATENÇÃO! O campo Nome deve ser preenchido.");
            } else if(this.getTelaCadastroDoacao().getInputCpfDoador().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "ATENÇÃO! O campo CPF deve ser preenchido.");
            } else if (this.getTelaCadastroDoacao().getInputNascimento().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "ATENÇÃO! O campo Nascimento deve ser preenchido.");
            } else if (this.getTelaCadastroDoacao().getInputTelefone().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "ATENÇÃO! O campo Telefone deve ser preenchido.");
            } else if (this.getTelaCadastroDoacao().getInputEmail().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "ATENÇÃO! O campo Email deve ser preenchido.");
            } else if (this.getTelaCadastroDoacao().getInputEndereco().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "ATENÇÃO! O campo Endereço deve ser preenchido.");
            } else if (this.getTelaCadastroDoacao().getInputQuantidade().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "ATENÇÃO! O campo Quantidade deve ser preenchido.");
            } else {
                empty = false;
            }
        }

        return empty;
    }
    
    public ModelDoador getDoador() {
        return new ModelDoador();
    }

    public TelaCadastroDoacao getTelaCadastroDoacao() {
        return telaCadastroDoacao;
    }

    public PersistenciaDoador getPersistenciaDoador() {
        return persistenciaDoador;
    }

    public PersistenciaBancoSangue getPersistenciaBancoSangue() {
        return persistenciaBancoSangue;
    }
    
}
