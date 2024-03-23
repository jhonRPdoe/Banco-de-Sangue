
package Controller;

import Model.ModelBancoSangue;
import Model.ModelPaciente;
import Persistencia.PersistenciaBancoSangue;
import Persistencia.PersistenciaPaciente;
import View.TelaRealizaTransfusao;
import javax.swing.JOptionPane;

/**
 * Classe responsável pelas regras e negócio da tela de transfusão
 * 
 * @author - Ruan Pereira
 * @since  - 30/12/2023
 */
public class ControllerTelaRealizaTransfusao {
    
    private final TelaRealizaTransfusao telaRealizaTransfusao;
    private final PersistenciaPaciente persistenciaPaciente;
    private final PersistenciaBancoSangue persistenciaBancoSangue;
    
    public ControllerTelaRealizaTransfusao(TelaRealizaTransfusao telaRealizaTransfusao) {
        this.telaRealizaTransfusao = telaRealizaTransfusao;
        this.persistenciaPaciente = new PersistenciaPaciente();
        this.persistenciaBancoSangue = new PersistenciaBancoSangue();
    }
    
    /**
     * Realiza a tranfusão de sangue subtraindo a quantidade de sangue doada do banco de sangue
     * @return boolean retorno
     */
    public boolean cadastrar() {
        boolean retorno = false;
        try {
            if (this.getTelaRealizaTransfusao().getCheckBoxIsPaciente().isSelected()) {
                if (this.isPaciente()) {
                    retorno = this.realizarDoacao();
                } else {
                    JOptionPane.showMessageDialog(null, "ATENÇÃO! Não há nenhum paciente cadastrado com este cpf, verifique o valor inserido.");
                }
            } else {
                if (this.cadastrarPaciente()) {
                    retorno = this.realizarDoacao();
                } else {
                    JOptionPane.showMessageDialog(null, "ATENÇÃO! Paciente já cadastrado!");
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
    public boolean realizarDoacao() {
        ModelBancoSangue bancoSangue = new ModelBancoSangue();
        bancoSangue.setCodigo(this.getTelaRealizaTransfusao().getComboBoxTipoSangue().getSelectedIndex() + 1);
        bancoSangue.setQuantidade(this.formataPontuacaoDouble(this.getTelaRealizaTransfusao().getInputQuantidade().getText().trim()));
        return this.getPersistenciaBancoSangue().update(bancoSangue, "-");
    }
    
    /**
     * Realiza o cadastro do paciente no banco de dados
     * @return boolean retorno
     */
    private boolean cadastrarPaciente() {
        boolean retorno = false;
        if (!this.isPaciente()) {
            ModelPaciente paciente = this.getPaciente();
            paciente.setNome(this.getTelaRealizaTransfusao().getInputNomePaciente().getText().trim());
            paciente.setNascimento(this.getTelaRealizaTransfusao().getInputNascimento().getText().trim());
            paciente.setCpf(this.getTelaRealizaTransfusao().getInputCpfPaciente().getText().replaceAll("[^0-9]", "").trim());
            paciente.setTelefone(this.getTelaRealizaTransfusao().getInputTelefone().getText().trim());
            paciente.setEmail(this.getTelaRealizaTransfusao().getInputEmail().getText().trim());
            paciente.setEndereco(this.getTelaRealizaTransfusao().getInputEndereco().getText().trim());
            retorno = this.getPersistenciaPaciente().insert(paciente);
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
        } else if (!validaCpf.isCpfValido(this.getTelaRealizaTransfusao().getInputCpfPaciente().getText())) {
           isCamposValidos = false;
           JOptionPane.showMessageDialog(null, "ATENÇÃO! CPF Inválido!");
        }
        
        return isCamposValidos;
    }
    
    /**
     * Verifica se o paciente informado já está registrado no banco
     * @return 
     */
    public boolean isPaciente() {
        ModelPaciente paciente = this.getPersistenciaPaciente().select(this.getTelaRealizaTransfusao().getInputCpfPaciente().getText().replaceAll("[^0-9]", "").trim());
        return (paciente.getId() != null);
    }
    
    /**
     * Retorna "true" quando qualquer um dos campos estiver vazio e "false" quando todos estiverem preenchidos.
     * @return boolean empty
     */
    public boolean emptyFields(){
        boolean empty = true; 
        if (this.getTelaRealizaTransfusao().getCheckBoxIsPaciente().isSelected()) {
            if(this.getTelaRealizaTransfusao().getInputCpfPaciente().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "ATENÇÃO! O campo CPF deve ser preenchido.");
            } else {
                empty = false;
            }
        } else {
            if (this.getTelaRealizaTransfusao().getInputNomePaciente().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "ATENÇÃO! O campo Nome deve ser preenchido.");
            } else if(this.getTelaRealizaTransfusao().getInputCpfPaciente().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "ATENÇÃO! O campo CPF deve ser preenchido.");
            } else if (this.getTelaRealizaTransfusao().getInputNascimento().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "ATENÇÃO! O campo Nascimento deve ser preenchido.");
            } else if (this.getTelaRealizaTransfusao().getInputTelefone().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "ATENÇÃO! O campo Telefone deve ser preenchido.");
            } else if (this.getTelaRealizaTransfusao().getInputEmail().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "ATENÇÃO! O campo Email deve ser preenchido.");
            } else if (this.getTelaRealizaTransfusao().getInputEndereco().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "ATENÇÃO! O campo Endereço deve ser preenchido.");
            } else if (this.getTelaRealizaTransfusao().getInputQuantidade().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "ATENÇÃO! O campo Quantidade deve ser preenchido.");
            } else {
                empty = false;
            }
        }

        return empty;
    }
    
    public ModelPaciente getPaciente() {
        return new ModelPaciente();
    }

    public TelaRealizaTransfusao getTelaRealizaTransfusao() {
        return telaRealizaTransfusao;
    }

    public PersistenciaPaciente getPersistenciaPaciente() {
        return persistenciaPaciente;
    }

    public PersistenciaBancoSangue getPersistenciaBancoSangue() {
        return persistenciaBancoSangue;
    }
}
