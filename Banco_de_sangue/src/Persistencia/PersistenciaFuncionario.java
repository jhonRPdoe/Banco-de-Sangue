
package Persistencia;

import Model.ModelFuncionario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Classe para persistencia de dados do funcionário em banco de dados
 * 
 * @author - Ruan Pereira
 * @since  - 21/01/2024
 */
public class PersistenciaFuncionario {
    
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
    public ArrayList<ModelFuncionario> selectAll() {
        this.conectar();
        ArrayList<ModelFuncionario> listaFuncionario = new ArrayList();
        try {
            this.statement = this.conexao.prepareStatement("SELECT * from funcionario");
            this.result = this.statement.executeQuery();

            while(this.result.next()){
                ModelFuncionario funcionario = this.getModelFuncionario();
                funcionario.setId(Integer.parseInt(this.result.getString("id")));
                funcionario.setNome(this.result.getString("nome"));
                funcionario.setCpf(this.result.getString("cpf"));
                listaFuncionario.add(funcionario);
            }
            System.out.println("Comando realizado com sucesso(SELECT)");
            this.desconectar();
        } catch (SQLException ex) {
            System.out.println( "Falha no comando executado(SELECT) : " + ex.getMessage());
            this.desconectar();
            return null;
        }
        return listaFuncionario;
    }
    
    /**
     * Registra os dados informados pelo usuário no banco de dados
     * @param funcionario
     * @return boolean retorno
     */
    public boolean insert(ModelFuncionario funcionario) {
        this.conectar();
        boolean retorno;
        try {
            this.statement = this.conexao.prepareStatement("INSERT INTO funcionario (nome, cpf) VALUES (?, ?)");
            this.statement.setString(1, funcionario.getNome());
            this.statement.setString(2, funcionario.getCpf());
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
     * @param funcionario
     * @return boolean retorno
     */
    public boolean update(ModelFuncionario funcionario) {
        this.conectar();
        boolean retorno;
        try {         
            this.statement = this.conexao.prepareStatement("UPDATE funcionario SET nome = ?, cpf = ? WHERE id = ?");
            String teste = funcionario.getNome();
            int teste2 = funcionario.getId();
            this.statement.setString(1, funcionario.getNome());
            this.statement.setString(2, funcionario.getCpf());
            this.statement.setInt(3, funcionario.getId());
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
            this.statement = this.conexao.prepareStatement("DELETE FROM funcionario WHERE id = ?");
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
     * @return ModelFuncionario funcionario
     */
    public ModelFuncionario select(String id) {
        this.conectar();
        ModelFuncionario funcionario = this.getModelFuncionario();
        try { 
            this.statement = this.conexao.prepareStatement("SELECT * FROM funcionario WHERE id = ?");
            this.statement.setInt(1, Integer.parseInt(id));
            this.result = this.statement.executeQuery();

            while(this.result.next()){
                funcionario.setId(Integer.parseInt(this.result.getString("id")));
                funcionario.setNome(this.result.getString("nome"));
                funcionario.setCpf(this.result.getString("cpf"));
            }
            System.out.println("Comando realizado com sucesso(SELECT)");
            this.desconectar();
        } catch (SQLException ex) {
            System.out.println( "Falha no comando executado(SELECT) : " + ex.getMessage());
            this.desconectar();
            return null;
        }
        return funcionario;
    }
    
    /**
     * Busca o registro correspondente ao nome e cpf informado
     * @param nome
     * @param cpf
     * @return ModelFuncionario funcionario
     */
    public ModelFuncionario selectByNomeAndCpf (String nome, String cpf) {
        this.conectar();
        ModelFuncionario funcionario = this.getModelFuncionario();
        try { 
            this.statement = this.conexao.prepareStatement("SELECT * FROM funcionario WHERE nome = ? AND cpf = ?");
            this.statement.setString(1, nome);
            this.statement.setString(2, cpf);
            this.result = this.statement.executeQuery();

            while(this.result.next()){
                funcionario.setId(this.result.getInt("id"));
                funcionario.setNome(this.result.getString("nome"));
                funcionario.setCpf(this.result.getString("cpf"));
            }
            System.out.println("Comando realizado com sucesso(SELECT)");
            this.desconectar();
        } catch (SQLException ex) {
            System.out.println( "Falha no comando executado(SELECT) : " + ex.getMessage());
            this.desconectar();
            return null;
        }
        return funcionario;
    }
    
    /**
     * Busca o registro correspondente ao cpf informado
     * @param cpf
     * @return ModelFuncionario funcionario
     */
    public ModelFuncionario selectByCpf (String cpf) {
        this.conectar();
        ModelFuncionario funcionario = this.getModelFuncionario();
        try { 
            this.statement = this.conexao.prepareStatement("SELECT * FROM funcionario WHERE cpf = ?");
            this.statement.setString(1, cpf);
            this.result = this.statement.executeQuery();

            while(this.result.next()){
                funcionario.setId(this.result.getInt("id"));
                funcionario.setNome(this.result.getString("nome"));
                funcionario.setCpf(this.result.getString("cpf"));
            }
            System.out.println("Comando realizado com sucesso(SELECT)");
            this.desconectar();
        } catch (SQLException ex) {
            System.out.println( "Falha no comando executado(SELECT) : " + ex.getMessage());
            this.desconectar();
            return null;
        }
        return funcionario;
    }
    
    /**
     * Retorna um novo model de Funcionário
     * @return ModelFuncionario
     */
    public ModelFuncionario getModelFuncionario() {
        return new ModelFuncionario();
    }
}
