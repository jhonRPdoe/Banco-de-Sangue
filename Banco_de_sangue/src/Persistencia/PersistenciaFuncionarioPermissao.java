
package Persistencia;

import Model.ModelFuncionario;
import Model.ModelFuncionarioPermissao;
import Model.ModelPermissao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Classe para persistencia de dados de FuncionarioPermissao em banco de dados
 * 
 * @author - Ruan Pereira
 * @since  - 21/01/2024
 */
public class PersistenciaFuncionarioPermissao {
    
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
    public ArrayList<ModelFuncionarioPermissao> selectAll() {
        this.conectar();
        ArrayList<ModelFuncionarioPermissao> listaFuncionarioPermissao = new ArrayList();
        try {
            this.statement = this.conexao.prepareStatement("SELECT * from func_permissao");
            this.result = this.statement.executeQuery();

            while(this.result.next()){
                ModelFuncionarioPermissao funcionarioPermissao = this.getModelFuncionarioPermissao();
                funcionarioPermissao.setId(this.result.getInt("id"));
                funcionarioPermissao.setFuncionario(this.getFuncionario(this.result.getString("funcionario_id")));
                funcionarioPermissao.setPermissao(this.getPermissao(this.result.getString("permissao_id")));
                listaFuncionarioPermissao.add(funcionarioPermissao);
            }
            System.out.println("Comando realizado com sucesso(SELECT)");
            this.desconectar();
        } catch (SQLException ex) {
            System.out.println( "Falha no comando executado(SELECT) : " + ex.getMessage());
            this.desconectar();
            return null;
        }
        return listaFuncionarioPermissao;
    }
    
    /**
     * Registra os dados informados pelo usuário no banco de dados
     * @param funcionarioPermissao
     * @return boolean retorno
     */
    public boolean insert(ModelFuncionarioPermissao funcionarioPermissao) {
        this.conectar();
        boolean retorno;
        try {
            this.statement = this.conexao.prepareStatement("INSERT INTO func_permissao (funcionario_id, permissao_id) VALUES (?, ?)");
            this.statement.setInt(1, funcionarioPermissao.getFuncionario().getId());
            this.statement.setInt(2, funcionarioPermissao.getPermissao().getId());
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
     * @param funcionarioPermissao
     * @return boolean retorno
     */
    public boolean update(ModelFuncionarioPermissao funcionarioPermissao) {
        this.conectar();
        boolean retorno;
        try {         
            this.statement = this.conexao.prepareStatement("UPDATE func_permissao SET funcionario_id = ?, permissao_id = ? WHERE id = ?");
            this.statement.setInt(1, funcionarioPermissao.getFuncionario().getId());
            this.statement.setInt(2, funcionarioPermissao.getPermissao().getId());
            this.statement.setInt(3, funcionarioPermissao.getId());
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
            this.statement = this.conexao.prepareStatement("DELETE FROM func_permissao WHERE id = ?");
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
     * @return ModelFuncionarioPermissao funcionarioPermissao
     */
    public ModelFuncionarioPermissao select(String id) {
        this.conectar();
        ModelFuncionarioPermissao funcionarioPermissao = this.getModelFuncionarioPermissao();
        try { 
            this.statement = this.conexao.prepareStatement("SELECT * FROM func_permissao WHERE id = ?");
            this.statement.setInt(1, Integer.parseInt(id));
            this.result = this.statement.executeQuery();

            while(this.result.next()){
                funcionarioPermissao.setId(Integer.parseInt(this.result.getString("id")));
                funcionarioPermissao.setFuncionario(this.getFuncionario(this.result.getString("funcionario_id")));
                funcionarioPermissao.setPermissao(this.getPermissao(this.result.getString("permissao_id")));
            }
            System.out.println("Comando realizado com sucesso(SELECT)");
            this.desconectar();
        } catch (SQLException ex) {
            System.out.println( "Falha no comando executado(SELECT) : " + ex.getMessage());
            this.desconectar();
            return null;
        }
        return funcionarioPermissao;
    }
    
    /**
     * Busca o registro correspondente ao id do funcionario informado
     * @param funcionarioId
     * @return ModelFuncionarioPermissao funcionarioPermissao
     */
    public ModelFuncionarioPermissao selectByFuncionarioId(Integer funcionarioId) {
        this.conectar();
        ModelFuncionarioPermissao funcionarioPermissao = this.getModelFuncionarioPermissao();
        try { 
            this.statement = this.conexao.prepareStatement("SELECT * FROM func_permissao WHERE funcionario_id = ?");
            this.statement.setInt(1, funcionarioId);
            this.result = this.statement.executeQuery();

            while(this.result.next()){
                funcionarioPermissao.setId(Integer.parseInt(this.result.getString("id")));
                funcionarioPermissao.setFuncionario(this.getFuncionario(this.result.getString("funcionario_id")));
                funcionarioPermissao.setPermissao(this.getPermissao(this.result.getString("permissao_id")));
            }
            System.out.println("Comando realizado com sucesso(SELECT)");
            this.desconectar();
        } catch (SQLException ex) {
            System.out.println( "Falha no comando executado(SELECT) : " + ex.getMessage());
            this.desconectar();
            return null;
        }
        return funcionarioPermissao;
    }

    /**
     * Retorna um novo model de Funcionario persistido
     * @return ModelFuncionario
     */
    private ModelFuncionario getFuncionario(String id) {
        PersistenciaFuncionario persistenciaFuncionario = this.getPersistenciaFuncionario();
        return persistenciaFuncionario.select(id);
    }
    
    /**
     * Retorna um novo model de Permissao persistido
     * @return ModelFuncionario
     */
    private ModelPermissao getPermissao(String id) {
        PersistenciaPermissao persistenciaPermissao = this.getPersistenciaPermissao();
        return persistenciaPermissao.select(id);
    }
    
    /**
     * Retorna um novo model de FuncionarioPermissao
     * @return ModelFuncionario
     */
    public ModelFuncionarioPermissao getModelFuncionarioPermissao() {
        return new ModelFuncionarioPermissao();
    }
    
    /**
     * Retorna um novo model de PersistenciaFuncionario
     * @return PersistenciaFuncionario
     */
    private PersistenciaFuncionario getPersistenciaFuncionario() {
        return new PersistenciaFuncionario();
    }
    
    /**
     * Retorna um novo model de PersistenciaPermissao
     * @return PersistenciaPermissao
     */
    private PersistenciaPermissao getPersistenciaPermissao() {
        return new PersistenciaPermissao();
    }
}
