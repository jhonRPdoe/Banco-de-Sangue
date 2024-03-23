
package Persistencia;

import Model.ModelBancoSangue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Classe para persistencia de dados para o banco de sangue em banco de dados
 * 
 * @author - Ruan Pereira
 * @since  - 07/01/2024
 */
public class PersistenciaBancoSangue {
    
    Connection conexao;
    PreparedStatement statement;
    ResultSet result;
    
    /**
     * Inicia a conexão com o banco de dados
     * @return boolean retorno
     */
    public boolean conectar() {
        boolean retorno;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/banco_de_sangue","root","123456789");
            System.out.println("Conexão realizada com sucesso");
            retorno = true;
        } catch(ClassNotFoundException | SQLException ex) {
            System.out.println("Falha na conexão com o banco " + ex.getMessage());
            retorno = false;
        }
        
        return retorno;
    }
    
    /**
     * Encerra a conexão com o banco de dados
     */
    public void desconectar(){
        try {
            this.conexao.close();
        } catch (SQLException ex) {
        
        }
    }
    
    /**
     * Busca todos dados armazenados no banco de dados
     * @return boolean retorno
     */
    public ArrayList<ModelBancoSangue> selectAll() {
        this.conectar();
        ArrayList<ModelBancoSangue> listaBancoSangue = new ArrayList();
        try {
            this.statement = this.conexao.prepareStatement("SELECT * from banco_de_sangue");
            this.result = this.statement.executeQuery();

            while(this.result.next()){
                ModelBancoSangue bancoSangue = this.getModelBancoSangue();
                bancoSangue.setCodigo(this.result.getInt("id"));
                bancoSangue.setTipo(this.result.getString("tipo"));
                bancoSangue.setQuantidade(this.result.getDouble("quantidade"));
                listaBancoSangue.add(bancoSangue);
            }
            System.out.println("Comando realizado com sucesso(SELECT)");
            this.desconectar();
        } catch (SQLException ex) {
            System.out.println( "Falha no comando executado(SELECT) : " + ex.getMessage());
            this.desconectar();
            return null;
        }
        return listaBancoSangue;
    }
    
    /**
     * Registra os dados informados pelo usuário no banco de dados
     * @param bancoSangue
     * @return boolean retorno
     */
    public boolean insert(ModelBancoSangue bancoSangue) {
        this.conectar();
        boolean retorno;
        try {
            this.statement = this.conexao.prepareStatement("INSERT INTO banco_de_sangue (tipo, quantidade) VALUES (?, ?)");
            this.statement.setString(1, bancoSangue.getTipo());
            this.statement.setDouble(2, bancoSangue.getQuantidade());
            this.statement.execute();
            System.out.println("Comando realizado com sucesso(INSERT)");

            retorno = true;
        }
        catch (Exception ex){
            System.out.println( "Falha no comando executado(INSERT) : " + ex.getMessage());
            retorno = false;
        }
        this.desconectar();
        return retorno;
    }
    
    /**
     * Atualiza o registro selecionado pelo usuário
     * @param bancoSangue
     * @param operador
     * @return boolean retorno
     */
    public boolean update(ModelBancoSangue bancoSangue, String operador) {
        this.conectar();
        boolean retorno;
        try {         
            this.statement = this.conexao.prepareStatement("UPDATE banco_de_sangue SET quantidade = quantidade + " + operador + " ? WHERE id = ?");
            this.statement.setDouble(1, bancoSangue.getQuantidade());
            this.statement.setInt(2, bancoSangue.getCodigo());
            this.statement.execute();
            System.out.println("Comando realizado com sucesso(UPDATE)");
            retorno = true;
        }
        catch (Exception ex){
            System.out.println( "Falha no comando executado(UPDATE) : " + ex.getMessage());
            retorno = false;
        }
        this.desconectar();
        return retorno;
    }
    
    /**
     * Deleta o registro informados pelo usuário
     * @param id
     * @return boolean retorno
     */
    public boolean delete(String id) {
        this.conectar();
        boolean retorno;
        try {
            this.statement = this.conexao.prepareStatement("DELETE FROM banco_de_sangue WHERE id = ?");
            this.statement.setInt(1, Integer.parseInt(id));
            this.statement.execute();
            System.out.println("Comando realizado com sucesso(DELETE)");
            retorno = true;
        }
        catch (SQLException ex){
            System.out.println( "Falha no comando executado(DELETE) : " + ex.getMessage());
            retorno = false;
        }
        this.desconectar();
        return retorno;
    }
    
    /**
     * Busca o registro correspondente ao id informado
     * @param id
     * @return ModelBancoSangue bancoSangue
     */
    public ModelBancoSangue select(String id) {
        this.conectar();
        ModelBancoSangue bancoSangue = this.getModelBancoSangue();
        try { 
            this.statement = this.conexao.prepareStatement("SELECT * FROM banco_de_sangue WHERE id = ?");
            this.statement.setInt(1, Integer.parseInt(id));
            this.result = this.statement.executeQuery();

            while(this.result.next()){
                bancoSangue.setCodigo(this.result.getInt("id"));
                bancoSangue.setTipo(this.result.getString("tipo"));
                bancoSangue.setQuantidade(this.result.getDouble("quantidade"));
            }
            System.out.println("Comando realizado com sucesso(SELECT)");
            this.desconectar();
        } catch (SQLException ex) {
            System.out.println( "Falha no comando executado(SELECT) : " + ex.getMessage());
            this.desconectar();
            return null;
        }
        return bancoSangue;
    }
    
    /**
     * Retorna um novo model do banco de sangue
     * @return ModelBancoSangue
     */
    public ModelBancoSangue getModelBancoSangue() {
        return new ModelBancoSangue();
    }
}
