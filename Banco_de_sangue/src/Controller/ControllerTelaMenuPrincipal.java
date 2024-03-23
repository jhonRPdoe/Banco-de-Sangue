
package Controller;

import Model.ModelFuncionario;
import Model.ModelFuncionarioPermissao;
import Persistencia.PersistenciaFuncionarioPermissao;
import View.TelaMenuPrincipal;

/**
 * Classe responsável pelas regras e negócio da tela de menu principal
 * 
 * @author - Ruan Pereira
 * @since  - 30/12/2023
 */
public class ControllerTelaMenuPrincipal {
    
    private final TelaMenuPrincipal telaMenuPrincipal;
    private final PersistenciaFuncionarioPermissao persistenciaFuncionarioPermissao;
    private final ModelFuncionario funcionario;
    
    public ControllerTelaMenuPrincipal(TelaMenuPrincipal telaMenuPrincipal, ModelFuncionario funcionario) {
        this.telaMenuPrincipal = telaMenuPrincipal;
        this.persistenciaFuncionarioPermissao = new PersistenciaFuncionarioPermissao();
        this.funcionario = funcionario;
        this.validaPermissaoUsuario();
    }
    
    /**
     * Verifica o tipo da permissão vinda do banco e printa na tela a String referente ao tipo
     */
    public final void validaPermissaoUsuario() {
        String permissao = "(Administrador)";
        
        switch (this.getFuncionarioPermissao().getPermissao().getTipo()) {
            case 1 -> {
                this.getTelaMenuPrincipal().getButtonCadastrarDoacao().setVisible(false);
                this.getTelaMenuPrincipal().getButtonRealizarDoacao().setVisible(false);
                this.getTelaMenuPrincipal().getButtonPermissoes().setVisible(false);
                permissao = "(Comum)";
            }
            case 2 -> {
                this.getTelaMenuPrincipal().getButtonPermissoes().setVisible(false);
                permissao = "(Especial)";
            }
        }
        
        this.informaUsuarioLogado(permissao);
    }
    
    /**
     * Printa da tela o nome e o nivel de acesso do usuário atualmente logado no sistema
     * @param permissao 
     */
    private void informaUsuarioLogado(String permissao) {
        this.getTelaMenuPrincipal().getLabelNomeUsuario().setText(this.getFuncionario().getNome() + permissao);
    }

    public TelaMenuPrincipal getTelaMenuPrincipal() {
        return telaMenuPrincipal;
    }

    public PersistenciaFuncionarioPermissao getPersistenciaFuncionarioPermissao() {
        return persistenciaFuncionarioPermissao;
    }

    public ModelFuncionario getFuncionario() {
        return funcionario;
    }
    
    /**
     * Retorna o model de FuncionarioPermissao persistido
     * @return 
     */
    public ModelFuncionarioPermissao getFuncionarioPermissao() {
        ModelFuncionarioPermissao funcionarioPermissao = this.getPersistenciaFuncionarioPermissao().selectByFuncionarioId(this.getFuncionario().getId());
        return funcionarioPermissao;
    }

}
