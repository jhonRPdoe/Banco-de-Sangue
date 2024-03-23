
package Persistencia;

import Model.ModelPaciente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Classe para persistencia de dados do paciente em banco de dados
 * 
 * @author - Ruan Pereira
 * @since  - 07/01/2024
 */
public class PersistenciaPaciente {
    
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
    public ArrayList<ModelPaciente> selectAll() {
        this.conectar();
        ArrayList<ModelPaciente> listaDoadores = new ArrayList();
        try {
            this.statement = this.conexao.prepareStatement("SELECT * from pacientes");
            this.result = this.statement.executeQuery();

            while(this.result.next()){
                ModelPaciente paciente = this.getModelPaciente();
                paciente.setId(this.result.getInt("id"));
                paciente.setNome(this.result.getString("nome"));
                paciente.setNascimento(this.result.getString("nascimento"));
                paciente.setCpf(this.result.getString("cpf"));
                paciente.setTelefone(this.result.getString("telefone"));
                paciente.setEmail(this.result.getString("email"));
                paciente.setEndereco(this.result.getString("endereço"));
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
     * @param paciente
     * @return boolean retorno
     */
    public boolean insert(ModelPaciente paciente) {
        this.conectar();
        boolean retorno;
        try {
            this.statement = this.conexao.prepareStatement("INSERT INTO pacientes (nome, nascimento, cpf, telefone, email, endereço) "
                                                              + "VALUES (?, ?, ?, ?, ?, ?)");
            this.statement.setString(1, paciente.getNome());
            this.statement.setString(2, this.formataData(paciente.getNascimento(), true));
            this.statement.setString(3, paciente.getCpf());
            this.statement.setString(4, paciente.getTelefone());
            this.statement.setString(5, paciente.getEmail());
            this.statement.setString(6, paciente.getEndereco());
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
     * @param paciente
     * @return boolean retorno
     */
    public boolean update(ModelPaciente paciente) {
        this.conectar();
        boolean retorno;
        try {         
            this.statement = this.conexao.prepareStatement("UPDATE pacientes SET nome = ?, nascimento = ?, cpf = ?, telefone = ?, email = ?, endereço = ?"  
                                                          + "WHERE id = ?");
            this.statement.setString(1, paciente.getNome());
            this.statement.setString(2, paciente.getNascimento());
            this.statement.setString(3, paciente.getCpf());
            this.statement.setString(4, paciente.getTelefone());
            this.statement.setString(5, paciente.getEmail());
            this.statement.setString(6, paciente.getEndereco());
            this.statement.setInt(7, paciente.getId());
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
            this.statement = this.conexao.prepareStatement("DELETE FROM pacientes WHERE id = ?");
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
     * @return ModelPaciente paciente
     */
    public ModelPaciente select(String cpf) {
        this.conectar();
        ModelPaciente paciente = this.getModelPaciente();
        try { 
            this.statement = this.conexao.prepareStatement("SELECT * FROM pacientes WHERE cpf = ?");
            this.statement.setString(1, cpf);
            this.result = this.statement.executeQuery();

            while(this.result.next()){
                paciente.setId(this.result.getInt("id"));
                paciente.setNome(this.result.getString("nome"));
                paciente.setNascimento(this.result.getString("nascimento"));
                paciente.setCpf(this.result.getString("cpf"));
                paciente.setTelefone(this.result.getString("telefone"));
                paciente.setEmail(this.result.getString("email"));
                paciente.setEndereco(this.result.getString("endereço"));
            }
            System.out.println("Comando realizado com sucesso(SELECT)");
            this.desconectar();
        } catch (SQLException ex) {
            System.out.println( "Falha no comando executado(SELECT) : " + ex.getMessage());
            this.desconectar();
            return null;
        }
        return paciente;
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
     * Retorna um novo model do Paciente
     * @return ModelDoador
     */
    public ModelPaciente getModelPaciente() {
        return new ModelPaciente();
    }
}
