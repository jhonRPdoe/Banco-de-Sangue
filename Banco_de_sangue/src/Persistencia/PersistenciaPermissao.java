
package Persistencia;

import Model.ModelPermissao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Classe para persistencia de dados das permissões em banco de dados
 * 
 * @author - Ruan Pereira
 * @since  - 21/01/2024
 */
public class PersistenciaPermissao {
    
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
    public ArrayList<ModelPermissao> selectAll() {
        this.conectar();
        ArrayList<ModelPermissao> listaPermissao = new ArrayList();
        try {
            this.statement = this.conexao.prepareStatement("SELECT * from filmes");
            this.result = this.statement.executeQuery();

            while(this.result.next()){
                ModelPermissao permissao = this.getModelPermissao();
                permissao.setId(this.result.getInt("id"));
                permissao.setTipo(this.result.getInt("tipo"));
                listaPermissao.add(permissao);
            }
            System.out.println("Comando realizado com sucesso(SELECT)");
            this.desconectar();
        } catch (SQLException ex) {
            System.out.println( "Falha no comando executado(SELECT) : " + ex.getMessage());
            this.desconectar();
            return null;
        }
        return listaPermissao;
    }
    
    /**
     * Registra os dados informados pelo usuário no banco de dados
     * @param permissao
     * @return boolean retorno
     */
    public boolean insert(ModelPermissao permissao) {
        this.conectar();
        boolean retorno;
        try {
            this.statement = this.conexao.prepareStatement("INSERT INTO permissao (tipo) VALUES (?)");
            this.statement.setInt(1, permissao.getTipo());
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
     * @param permissao
     * @return boolean retorno
     */
    public boolean update(ModelPermissao permissao) {
        this.conectar();
        boolean retorno;
        try {         
            this.statement = this.conexao.prepareStatement("UPDATE permissao SET tipo = ? WHERE id = ?");
            this.statement.setInt(1, permissao.getTipo());
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
            this.statement = this.conexao.prepareStatement("DELETE FROM permissao WHERE id = ?");
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
     * @return ModelPermissao permissao
     */
    public ModelPermissao select(String id) {
        this.conectar();
        ModelPermissao permissao = this.getModelPermissao();
        try { 
            this.statement = this.conexao.prepareStatement("SELECT * FROM permissao WHERE id = ?");
            this.statement.setInt(1, Integer.parseInt(id));
            this.result = this.statement.executeQuery();

            while(this.result.next()){
                permissao.setId(Integer.parseInt(this.result.getString("id")));
                permissao.setTipo(Integer.parseInt(this.result.getString("tipo"))); 
            }
            System.out.println("Comando realizado com sucesso(SELECT)");
            this.desconectar();
        } catch (SQLException ex) {
            System.out.println( "Falha no comando executado(SELECT) : " + ex.getMessage());
            this.desconectar();
            return null;
        }
        return permissao;
    }
    
    /**
     * Retorna um novo model de Permissao
     * @return ModelFuncionario
     */
    public ModelPermissao getModelPermissao() {
        return new ModelPermissao();
    }
    
}
