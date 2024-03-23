
package Model;

/**
 * Model da permissão do funcionário
 * 
 * @author - Ruan Pereira
 * @since  - 18/12/2023
 */
public class ModelFuncionarioPermissao {
    
    private Integer id;
    private ModelFuncionario funcionario;
    private ModelPermissao permissao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ModelFuncionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(ModelFuncionario funcionario) {
        this.funcionario = funcionario;
    }

    public ModelPermissao getPermissao() {
        return permissao;
    }

    public void setPermissao(ModelPermissao permissao) {
        this.permissao = permissao;
    }
    
    
}
