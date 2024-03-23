
package Controller;

import Model.ModelFuncionario;
import Model.ModelFuncionarioPermissao;
import Model.ModelPermissao;
import Persistencia.PersistenciaFuncionario;
import Persistencia.PersistenciaFuncionarioPermissao;
import View.TelaCadastrarFuncionario;
import javax.swing.JOptionPane;

/**
 * Classe responsável pelas regras e negócio da tela de cadastro de funcionários
 * 
 * @author - Ruan Pereira
 * @since  - 30/12/2023
 */
public class ControllerTelaCadastroFuncionario {
    
    private final TelaCadastrarFuncionario telaCadastrarFuncionario;
    private final PersistenciaFuncionario persistenciaFuncionario;
    private final PersistenciaFuncionarioPermissao persistenciaFuncionarioPermissao;
    
    public ControllerTelaCadastroFuncionario(TelaCadastrarFuncionario telaCadastrarFuncionario) {
        this.telaCadastrarFuncionario = telaCadastrarFuncionario;
        this.persistenciaFuncionario = new PersistenciaFuncionario();
        this.persistenciaFuncionarioPermissao = new PersistenciaFuncionarioPermissao();
        if (this.getTelaCadastrarFuncionario().isUpdate) this.preencheCampos();
    }
    
    /**
     * Altera o cadastro do funcionário e seu nivel de permissão no banco de dados
     * @return boolean retorno
     */
    public boolean alterar() {
        boolean retorno = false;
        try {
            if (this.alterarFuncionario()) {
                retorno = this.alterarFuncionarioPermissao();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this.getTelaCadastrarFuncionario(), "Não foi possível realizar a alteração, verifique as informações inseridas.");
        }
        
        return retorno;
    }
    
    /**
     * Altera o nivel de permissão do usuário
     * @return boolean retorno
     */
    public boolean alterarFuncionarioPermissao() {
        boolean retorno = false;
        if (this.isFuncionario()) {
            ModelFuncionarioPermissao funcionarioPermissao = this.getPersistenciaFuncionarioPermissao().selectByFuncionarioId(this.buscaFuncionarioAdicionado().getId());
            funcionarioPermissao.getPermissao().setId(this.getTelaCadastrarFuncionario().getComboBoxPermissaoFuncionario().getSelectedIndex() + 1);
            retorno = this.getPersistenciaFuncionarioPermissao().update(funcionarioPermissao);
        }
        return retorno;
    }
    
    /**
     * Altera as informações do funcionário no banco
     * @return boolean retorno
     */
    private boolean alterarFuncionario() {
        boolean retorno = false;
        if (this.isFuncionario()) {
            ModelFuncionario funcionario = this.getFuncionario();
            funcionario.setId(this.buscaFuncionarioAdicionado().getId());
            retorno = this.getPersistenciaFuncionario().update(funcionario);
        }
        
        return retorno;
    }
    
    /**
     * Realiza o cadastro do funcionário e seu nivel de permissão no banco de dados
     * @return boolean retorno
     */
    public boolean cadastrar() {
        boolean retorno = false;
        try {
            if (this.cadastrarFuncionario()) {
                retorno = this.cadastrarFuncionarioPermissao();
            } else {
                JOptionPane.showMessageDialog(this.getTelaCadastrarFuncionario(), "ATENÇÃO! Funcionário já cadastrado!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this.getTelaCadastrarFuncionario(), "Não foi possível realizar o cadastro, verifique as informações inseridas.");
        }
        
        return retorno;
    }
    
    /**
     * Cadastra o nivel de permissão do usuário
     * @return boolean retorno
     */
    public boolean cadastrarFuncionarioPermissao() {
        boolean retorno = false;
        if (this.isFuncionario()) {
            ModelFuncionario funcionario = this.buscaFuncionarioAdicionado();
            ModelFuncionarioPermissao funcionarioPermissao = this.getFuncionarioPermissao();
            funcionarioPermissao.setFuncionario(funcionario);
            funcionarioPermissao.setPermissao(new ModelPermissao());
            funcionarioPermissao.getPermissao().setId(this.getTelaCadastrarFuncionario().getComboBoxPermissaoFuncionario().getSelectedIndex() + 1);
            
            retorno = this.getPersistenciaFuncionarioPermissao().insert(funcionarioPermissao);
        }
        return retorno;
    }
    
    /**
     * Cadastra as informações do funcionário no banco
     * @return boolean retorno
     */
    private boolean cadastrarFuncionario() {
        boolean retorno = false;
        if (!this.isFuncionario()) {
            retorno = this.getPersistenciaFuncionario().insert(this.getFuncionario());
        }
        
        return retorno;
    }
    
    private ModelFuncionario buscaFuncionarioAdicionado() {
        return this.getPersistenciaFuncionario().selectByCpf(this.getFuncionario().getCpf());
    }
    
    /**
     * Preenche os campos da tela com as informações do funcionário selecionado
     */
    private void preencheCampos() {
        this.getTelaCadastrarFuncionario().getInputNomeFuncionario().setText(this.getFuncionarioPermissaoSelecionado().getFuncionario().getNome());
        this.getTelaCadastrarFuncionario().getInputCpfFuncionario().setText(this.montaCpf(this.getFuncionarioPermissaoSelecionado().getFuncionario().getCpf()));
        this.getTelaCadastrarFuncionario().getComboBoxPermissaoFuncionario().setSelectedIndex(this.getFuncionarioPermissaoSelecionado().getPermissao().getTipo() - 1);
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
        } else if (!validaCpf.isCpfValido(this.getTelaCadastrarFuncionario().getInputCpfFuncionario().getText())) {
           isCamposValidos = false;
           JOptionPane.showMessageDialog(this.getTelaCadastrarFuncionario(), "ATENÇÃO! CPF Inválido!");
        }
        
        return isCamposValidos;
    }
    
    /**
     * Verifica se o funcionário já está registrado no banco
     * @return boolean
     */
    public boolean isFuncionario() {
        return (this.buscaFuncionarioAdicionado().getId() != null);
    }
    
    /**
     * Retorna "true" quando qualquer um dos campos e "false" quando todos estiverem preenchidos.
     * @return boolean empty
     */
    public boolean emptyFields(){
        boolean empty = true; 
        
        if (this.getTelaCadastrarFuncionario().getInputNomeFuncionario().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this.getTelaCadastrarFuncionario(), "ATENÇÃO! O campo Nome deve ser preenchido.");
        } else if(this.getTelaCadastrarFuncionario().getInputCpfFuncionario().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this.getTelaCadastrarFuncionario(), "ATENÇÃO! O campo CPF deve ser preenchido.");
        } else {
            empty = false;
        }

        return empty;
    }
    
    public ModelFuncionario getFuncionario() {
        ModelFuncionario funcionario = new ModelFuncionario();
        funcionario.setNome(this.getTelaCadastrarFuncionario().getInputNomeFuncionario().getText().trim());
        funcionario.setCpf(this.getTelaCadastrarFuncionario().getInputCpfFuncionario().getText().replaceAll("[^0-9]", "").trim());
        
        return funcionario;
    }
    
    /**
     * Retorna o cpf formatado com os caracteres especiais padrões
     * 
     * @return String cpfMontado
     */
    private String montaCpf(String cpf) {
        String cpfMontado = trataDados.adicionaCaracteres(cpf, ".", 3);
        cpfMontado = trataDados.adicionaCaracteres(cpfMontado, ".", 7);
        cpfMontado = trataDados.adicionaCaracteres(cpfMontado, "-", 11);
        
        return cpfMontado;
    }
    
    public ModelFuncionarioPermissao getFuncionarioPermissaoSelecionado() {
        return this.getTelaCadastrarFuncionario().getTelaPermissoes().getController().buscaFuncionario();
    }
    
    public ModelFuncionarioPermissao getFuncionarioPermissao() {
        return new ModelFuncionarioPermissao();
    }

    public final TelaCadastrarFuncionario getTelaCadastrarFuncionario() {
        return telaCadastrarFuncionario;
    }

    public PersistenciaFuncionario getPersistenciaFuncionario() {
        return persistenciaFuncionario;
    }

    public PersistenciaFuncionarioPermissao getPersistenciaFuncionarioPermissao() {
        return persistenciaFuncionarioPermissao;
    }
}
