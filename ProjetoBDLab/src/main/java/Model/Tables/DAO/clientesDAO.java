package Model.Tables.DAO;

import Model.Classes.clientes;
import java.sql.SQLException;

public class clientesDAO extends ConnectionDAO {

    //------------------------INSERIR NOVO REGISTRO DE CLIENTE NO DATABASE----------------------------
    public boolean insertCliente(clientes cliente) {
        connect();
        String sql = "INSERT INTO clientes (nome, email, cpf, empregado_cpf) VALUES (?, ?, ?, ?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, cliente.getNome());
            pst.setString(2, cliente.getEmail());
            pst.setString(3, cliente.getCpf());
            pst.setString(4, cliente.getFk_empregado_cpf());
            pst.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro de conexao " + ex.getMessage());
            return false;
        } finally {
            closeConnection();
        }
    }

    //------------------------ALTERANDO INFORMAÇÕES DO CLIENTE NO DATABASE----------------------------
    public boolean updateCliente(String cpf, clientes novoCliente) {
        connect();
        String sql = "UPDATE clientes SET nome = ?, email = ?, empregado_cpf = ? WHERE cpf = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, novoCliente.getNome());
            pst.setString(2, novoCliente.getEmail());
            pst.setString(3, novoCliente.getFk_empregado_cpf());
            pst.setString(4, cpf);
            pst.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro de conexao " + ex.getMessage());
            return false;
        } finally {
            closeConnection();
        }
    }

    //------------------------DELETANDO CLIENTE ESPECÌFICO NO DATABASE----------------------------
    public boolean deleteCliente(String cpf) {
        connect();
        String sql = "DELETE FROM clientes WHERE cpf = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, cpf);
            pst.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro de conexao " + ex.getMessage());
            return false;
        } finally {
            closeConnection();
        }
    }

    //------------------------PROCURANDO CLIENTE ESPECÌFICO NO DATABASE PELO CPF----------------------------
    public void selectCliente(String cpf) {
        connect();
        String sql = "SELECT * FROM clientes WHERE cpf = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, cpf);
            resultSet = pst.executeQuery();
            if (resultSet.next()) {
                System.out.println("Nome: " + resultSet.getString("nome"));
                System.out.println("Email: " + resultSet.getString("email"));
                System.out.println("CPF: " + resultSet.getString("cpf"));
                System.out.println("Empregado CPF: " + resultSet.getString("empregado_cpf"));
                System.out.println("---------------------------------");
            }
        } catch (SQLException ex) {
            System.out.println("Erro de conexao " + ex.getMessage());
        } finally {
            closeConnection();
        }
    }

    private void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) connection.close();
            if (pst != null && !pst.isClosed()) pst.close();
            if (resultSet != null && !resultSet.isClosed()) resultSet.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar conexao " + ex.getMessage());
        }
    }
}
