package Model.Tables.DAO;

import Model.Classes.empregados;

import java.sql.SQLException;

import static javax.management.remote.JMXConnectorFactory.connect;

public class empregadoDAO extends ConnectionDAO {

    boolean sucesso = false;

    //------------------------INSERIR NOVO REGISTRO DE CLIENTE NO DATABASE----------------------------
    public boolean insertEmpregado(empregados empregados) {

        connect();

        String sql = "INSERT INTO empregado (cpf, nome, email) values (?,?,?)";

        try{
            pst = connection.prepareStatement(sql);
            pst.setString(1,empregados.getCpf());
            pst.setString(2,empregados.getNome());
            pst.setString(3,empregados.getEmail());
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro de conexao = " + ex.getMessage());
            sucesso = false;
        }finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException e) {
                System.out.println("Erro de conexao = " + e.getMessage());
            }
        }
        return sucesso;
    }

    //------------------------SELECIONAR CLIENTE POR CPF NO DATABASE----------------------------
    public boolean selectEmpregadoCPF(String cpf) {

        connect();
        boolean verificado = false;

        String sql = "SELECT * FROM empregado";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql); //ref. a tabela resultante da busca
            while (resultSet.next()) {
                empregados empregadosTemp = new empregados(resultSet.getString("cpf"), resultSet.getString("nome"), resultSet.getString("email"));
                if(empregadosTemp.getCpf().equals(cpf)) {
                    verificado = true;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro de conexao = " + ex.getMessage());
            verificado = false;
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException ex) {
                System.out.println("Erro de conexao = " + ex.getMessage());
            }
        }
        return verificado;
    }

    //------------------------SELECIONAR NOME DE CLIENTE ESPECÌFICO NO DATABASE----------------------------
    public String selectEmpregadoNome(String cpf) {

        connect();

        String nome = null;
        String sql = "SELECT * FROM empregado";

        try {

            pst = connection.prepareStatement(sql);
            resultSet = pst.executeQuery();

            while(resultSet.next()) {
                empregados empregadosTemp = new empregados(resultSet.getString("cpf"), resultSet.getString("nome"), resultSet.getString("email"));

                if(empregadosTemp.getCpf().equals(cpf)){
                    nome = empregadosTemp.getNome();
                }
            }

        } catch (SQLException ex) {
            System.out.println("Erro de conexao = " + ex.getMessage());
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException ex) {
                System.out.println("Erro de conexao = " + ex.getMessage());
            }
        }
        return nome;
    }
    public boolean deleteEmpregado(String cpf) {

        connect();

        String sql = "DELETE FROM empregado WHERE cpf =?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1,cpf);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException ex) {
                System.out.println("Erro = " + ex.getMessage());
            }
        }
        return sucesso;
    }

}