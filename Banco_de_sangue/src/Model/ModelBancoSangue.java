
package Model;

/**
 * Model do banco de sangue
 * 
 * @author - Ruan Pereira
 * @since  - 18/12/2023
 */
public class ModelBancoSangue {
    
    private Integer codigo;
    private String tipo;
    private Double quantidade;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

}
