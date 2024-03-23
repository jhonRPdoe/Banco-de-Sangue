
package Model;

/**
 * Model do funcionario com acesso ao sistema
 * 
 * @author - Ruan Pereira
 * @since  - 18/12/2023
 */
public class ModelFuncionario {
    
    private Integer id;
    private String nome;
    private String cpf;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
}
