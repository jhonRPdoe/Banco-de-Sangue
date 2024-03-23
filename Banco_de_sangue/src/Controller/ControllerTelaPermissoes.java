
package Controller;

import Model.ModelFuncionarioPermissao;
import Persistencia.PersistenciaFuncionarioPermissao;
import View.TelaPermissoes;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Classe responsável pelas regras e negócio da tela de FuncionárioPermissão
 * 
 * @author - Ruan Pereira
 * @since  - 21/12/2023
 */
public class ControllerTelaPermissoes {
    
    private final TelaPermissoes telaPermissoes;
    private final PersistenciaFuncionarioPermissao persistenciaFuncionarioPermissao;
    
    public ControllerTelaPermissoes(TelaPermissoes telaPermissoes) {
        this.telaPermissoes = telaPermissoes;
        this.persistenciaFuncionarioPermissao = new PersistenciaFuncionarioPermissao();
        this.atualizarTabela();
    }
    
    /*
     * Definição do vetor com os títulos de cada coluna da tabela
     */
    private final String[] colunasTabela = {"ID", "Nome", "CPF", "Permissão"};
    
    /*
     * Atualiza a tabela toda vez que inserida, removida ou alterada alguma informação
     */
    public final void atualizarTabela(){
        ArrayList<ModelFuncionarioPermissao> listaFuncionarioPermissao = this.getPersistenciaFuncionarioPermissao().selectAll();
        ModelFuncionarioPermissao funcionarioPermissao = this.getFuncionarioPermissao();
        DefaultTableModel modelTabela = this.getModelTabela();
        modelTabela.setNumRows(0);

        for (int i = 0; i < listaFuncionarioPermissao.size(); i++) {
            funcionarioPermissao = listaFuncionarioPermissao.get(i);

            String[] rowData = {
                                String.valueOf(funcionarioPermissao.getId()),
                                funcionarioPermissao.getFuncionario().getNome(),
                                this.montaCpf(funcionarioPermissao.getFuncionario().getCpf()),
                                this.PermissaoUsuario(funcionarioPermissao.getPermissao().getTipo())
                               };
            
            modelTabela.addRow(rowData);
        }
        
        this.getTelaPermissoes().getTableFuncionarios().setModel(modelTabela);    
    }
    
    /**
     * Executa a exclusão do registro selecionado
     */
    public void excluirRegistro() {
        int indiceRegistro = this.getTelaPermissoes().getTableFuncionarios().getSelectedRow();
        if (indiceRegistro != -1) {
            String[] options = { "Sim", "Não" };
                int deletar = JOptionPane.showOptionDialog(
                    this.getTelaPermissoes(),
                    "Tem certeza que deseja excluir?",
                    "Exclusão de funcionario", 
                    JOptionPane.DEFAULT_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, 
                    null, 
                    options, 
                    options[0]
                );
            if (deletar == 0) {
                if(this.getPersistenciaFuncionarioPermissao().delete(this.getTelaPermissoes().getTableFuncionarios().getValueAt(indiceRegistro, 0).toString())) {
                    this.atualizarTabela();
                    JOptionPane.showMessageDialog(this.getTelaPermissoes(), "Registro excluido com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this.getTelaPermissoes(), "Não foi possivel excluir o registro.");
                }

            }
        } else {
            JOptionPane.showMessageDialog(this.getTelaPermissoes(), "Selecione um registro para exclusão.");
        }
    }
    
    /**
     * Retorna o cpf formatado com os caracteres especiais padrões
     * 
     * @return String cpfMontado
     */
    private String montaCpf(String cpf) {
        String cpfMontado = trataDados.adicionaCaracteres(cpf, ".", 3);
        cpfMontado = trataDados.adicionaCaracteres(cpfMontado, ".", 7);
        cpfMontado = trataDados.adicionaCaracteres(cpfMontado, "-", 11);
        
        return cpfMontado;
    }
    
    /**
     * Executa a busca dos dados do funcionário selecionado
     * @return ModelFuncionarioPermissao funcionarioPermissao
     */
    protected ModelFuncionarioPermissao buscaFuncionario() {
        int indiceRegistro = this.getTelaPermissoes().getTableFuncionarios().getSelectedRow();
        ModelFuncionarioPermissao funcionarioPermissao = this.getFuncionarioPermissao();
        funcionarioPermissao = this.getPersistenciaFuncionarioPermissao().select(this.getTelaPermissoes().getTableFuncionarios().getValueAt(indiceRegistro, 0).toString());
        return funcionarioPermissao;
    }
    
    public String PermissaoUsuario(Integer codigoPermissao) {
        String permissao = "Administrador";
        switch (codigoPermissao) {
            case 1 -> {
                permissao = "Comum";
            }
            case 2 -> {
                permissao = "Especial";
            }
        }
        return permissao;
    }

    public TelaPermissoes getTelaPermissoes() {
        return telaPermissoes;
    }

    public ModelFuncionarioPermissao getFuncionarioPermissao() {
        return new ModelFuncionarioPermissao();
    }
    
    /**
     * Retorna o model padrão da TableFuncionarios
     * @return DefaultTableModel modelTabela
     */
    public DefaultTableModel getModelTabela() {
        DefaultTableModel modelTabela = new DefaultTableModel(this.colunasTabela, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Torna todas as células não editáveis
                return false;
            }
        };
        return modelTabela;
    }
    
    private PersistenciaFuncionarioPermissao getPersistenciaFuncionarioPermissao() {
        return persistenciaFuncionarioPermissao;
    }
}
