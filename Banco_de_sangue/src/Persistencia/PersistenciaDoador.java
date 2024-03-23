
package Persistencia;

import Model.ModelDoador;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Classe para persistencia de dados do doador em banco de dados
 * 
 * @author - Ruan Pereira
 * @since  - 07/01/2024
 */
public class PersistenciaDoador {
    
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
    public ArrayList<ModelDoador> selectAll() {
        this.conectar();
        ArrayList<ModelDoador> listaDoadores = new ArrayList();
        try {
            this.statement = this.conexao.prepareStatement("SELECT * from doadores");
            this.result = this.statement.executeQuery();

            while(this.result.next()){
                ModelDoador doador = this.getModelDoador();
                doador.setId(this.result.getInt("id"));
                doador.setNome(this.result.getString("nome"));
                doador.setNascimento(this.result.getString("nascimento"));
                doador.setCpf(this.result.getString("cpf"));
                doador.setTelefone(this.result.getString("telefone"));
                doador.setEmail(this.result.getString("email"));
                doador.setEndereco(this.result.getString("endereço"));
            }
            System.out.println("Comando realizado com sucesso(SELECT)");
            this.desconectar();
        } catch (SQLException ex) {
            System.out.println( "Falha no comando executado(SELECT) : " + ex.getMessage());
            this.desconectar();
            return null;
        }
        return listaDoadores;
    }
    
    /**
     * Registra os dados informados pelo usuário no banco de dados
     * @param doador
     * @return boolean retorno
     */
    public boolean insert(ModelDoador doador) {
        this.conectar();
        boolean retorno;
        try {
            this.statement = this.conexao.prepareStatement("INSERT INTO doadores (nome, nascimento, cpf, telefone, email, endereço) "
                                                              + "VALUES (?, ?, ?, ?, ?, ?)");
            this.statement.setString(1, doador.getNome());
            this.statement.setString(2, this.formataData(doador.getNascimento(), true));
            this.statement.setString(3, doador.getCpf());
            this.statement.setString(4, doador.getTelefone());
            this.statement.setString(5, doador.getEmail());
            this.statement.setString(6, doador.getEndereco());
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
     * @param doador
     * @return boolean retorno
     */
    public boolean update(ModelDoador doador) {
        this.conectar();
        boolean retorno;
        try {         
            this.statement = this.conexao.prepareStatement("UPDATE doadores SET nome = ?, nascimento = ?, cpf = ?, telefone = ?, email = ?, endereço = ?"  
                                                          + "WHERE id = ?");
            this.statement.setString(1, doador.getNome());
            this.statement.setString(2, doador.getNascimento());
            this.statement.setString(3, doador.getCpf());
            this.statement.setString(4, doador.getTelefone());
            this.statement.setString(5, doador.getEmail());
            this.statement.setString(6, doador.getEndereco());
            this.statement.setInt(7, doador.getId());
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
            this.statement = this.conexao.prepareStatement("DELETE FROM doadores WHERE id = ?");
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
     * @param cpf
     * @return ModelDoador doador
     */
    public ModelDoador select(String cpf) {
        this.conectar();
        ModelDoador doador = this.getModelDoador();
        try { 
            this.statement = this.conexao.prepareStatement("SELECT * FROM doadores WHERE cpf = ?");
            this.statement.setString(1, cpf);
            this.result = this.statement.executeQuery();

            while(this.result.next()){
                doador.setId(this.result.getInt("id"));
                doador.setNome(this.result.getString("nome"));
                doador.setNascimento(this.result.getString("nascimento"));
                doador.setCpf(this.result.getString("cpf"));
                doador.setTelefone(this.result.getString("telefone"));
                doador.setEmail(this.result.getString("email"));
                doador.setEndereco(this.result.getString("endereço"));
            }
            System.out.println("Comando realizado com sucesso(SELECT)");
            this.desconectar();
        } catch (SQLException ex) {
            System.out.println( "Falha no comando executado(SELECT) : " + ex.getMessage());
            this.desconectar();
            return null;
        }
        return doador;
    }
    
    /**
     * Formata a data para o formato yyyy-mm-dd ou para dd/mm/yyyy
     * @param String data
     * @param boolean inverteData
     * @return String data
     */
    private String formataData(String data, boolean inverteData) {
        if (inverteData) {
            String dataDia = data.substring(0, 2);
            String dataMes = data.substring(3, 5);
            String dataAno = data.substring(6, data.length());
            data = (dataAno + "-" + dataMes + "-" + dataDia );
        } else {
            String dataAno = data.substring(0, 4);
            String dataMes = data.substring(5, 7);
            String dataDia = data.substring(8, data.length());
            data = (dataDia + "/" + dataMes + "/" +  dataAno);
        }
        
        return data;
    }
    
    /**
     * Retorna um novo model do Doador
     * @return ModelDoador
     */
    public ModelDoador getModelDoador() {
        return new ModelDoador();
    }
}
