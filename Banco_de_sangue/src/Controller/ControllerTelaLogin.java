
package Controller;

import Model.ModelFuncionario;
import javax.swing.JOptionPane;
import View.TelaLogin;
import Persistencia.PersistenciaFuncionario;

/**
 * Classe responsável pelas regras e negócio da tela de login
 * 
 * @author - Ruan Pereira
 * @since  - 30/12/2023
 */
public class ControllerTelaLogin {
    
    private final TelaLogin telaLogin;
    private final PersistenciaFuncionario persistencia;
    private ModelFuncionario funcionario;
    
    public ControllerTelaLogin(TelaLogin telaLogin) {
        this.telaLogin = telaLogin;
        this.persistencia = new PersistenciaFuncionario();
    }
    
    /**
     * Verifica se as informações do usuário são válidas
     * @return boolean retorno
     */
    public boolean validaLogin() {
        boolean retorno = false;
        
        if (!this.emptyFields() && this.validaCampos()) {
            if (this.isFuncionarioInvalido()) {
                JOptionPane.showMessageDialog(null, "ATENÇÃO! funcionário Inválido, verifique o valores inseridos.");
            } else {
                retorno = true;
            }
        }
        
        return retorno;
    }
    
    /**
     * Retorna "true" quando qualquer um dos campos e "false" quando todos estiverem preenchidos.
     * @return boolean empty
     */
    public boolean emptyFields(){
        boolean empty = true; 

        if(this.telaLogin.getInputNomeFuncionario().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "ATENÇÃO! O campo Nome deve ser preenchido.");
        } else if(this.telaLogin.getInputCpfFuncionario().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "ATENÇÃO! O campo CPF deve ser preenchido.");
        } else {
            empty = false;
        }

        return empty;
    }
    
    /**
     * Verifica se os valores inseridos nos campos são válidos
     * 
     * @return boolean isCamposValidos
     */
    public boolean validaCampos() {
        boolean isCamposValidos = true;
        
        if (!validaCpf.isCpfValido(this.telaLogin.getInputCpfFuncionario().getText())) {
            isCamposValidos = false;
            JOptionPane.showMessageDialog(null, "ATENÇÃO! CPF Inválido!");
        }
        
        return isCamposValidos;
    }
    
    /**
     * Verifica se o funcionário é inválido se o mesmo não estiver cadastrado no banco
     * @return boolean
     */
    public boolean isFuncionarioInvalido() {
        this.setModelFuncionario();
        return (this.getModelFuncionario().getId() == null);
    }
    
    public ModelFuncionario getModelFuncionario() {
        return this.funcionario;
    }
    
    public void setModelFuncionario() {
        this.funcionario = this.getPersistenciaFuncionario().selectByNomeAndCpf(this.telaLogin.getInputNomeFuncionario().getText().trim(), 
                                                                                 this.telaLogin.getInputCpfFuncionario().getText().replaceAll("[^0-9]", "").trim()
                                                                                );
    }
    
    private PersistenciaFuncionario getPersistenciaFuncionario() {
        return this.persistencia;
    }
}
