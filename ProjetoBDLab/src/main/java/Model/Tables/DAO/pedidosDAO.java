package Model.Tables.DAO;

import Model.Classes.autores;
import Model.Classes.pedidos;
import Model.Tables.DAO.ConnectionDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class pedidosDAO extends ConnectionDAO {

    boolean sucesso = false;

    //------------------------INSERIR NOVO PEDIDOS NO DATABASE----------------------------
    public boolean insertpedidos(pedidos pedido) {

        connect();
        String sql = "INSERT INTO Pedido (id,livros,data,status,clientes_cpf) values (?,?,?,?,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, pedido.getIdPedido());
            pst.setString(2, pedido.getLivros());
            pst.setString(3, pedido.getData());
            pst.setString(4, pedido.getStatus());
            pst.setString(5, pedido.getPk_cpfCliente());
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro de conexao  = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException e) {
                System.out.println("Erro de conexao " + e.getMessage());
            }
        }
        return sucesso;
    }

    //------------------------DELETAR UM PEDIDO ESPECIFICO NO DATABASE----------------------------
    public boolean deletePedido(String cpf) {

        connect();

        String sql = "DELETE FROM Pedido WHERE clientes_cpf=?";

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

    //------------------------PROCURAR UM PEDIDO ESPECIFICO NO DATABASE----------------------------
    public void selectPedidos() {
        ArrayList<pedidos> listaPedidos = new ArrayList<>();

        connect();

        String sql = "SELECT * FROM Pedido";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql); //ref. a tabela resultante da busca
            while (resultSet.next()) {

                pedidos pedidoTemp = new pedidos(resultSet.getInt("id"), resultSet.getString("livros"), resultSet.getString("data"), resultSet.getString("clientes_cpf"), resultSet.getString("status"));
                System.out.println("ID = " + pedidoTemp.getIdPedido());
                System.out.println("Livros = " + pedidoTemp.getLivros());
                System.out.println("Data = " + pedidoTemp.getData());
                System.out.println("CPF do Cliente = " + pedidoTemp.getPk_cpfCliente());
                System.out.println("Status do Pedido = " + pedidoTemp.getStatus());
                System.out.println("---------------------------------");
                listaPedidos.add(pedidoTemp);
            }
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException ex) {
                System.out.println("Erro = " + ex.getMessage());
            }
        }
    }

    //------------------------ALTERANDO INFORMAÇÕES DO PEDIDO NO DATABASE----------------------------
    public boolean updatePedido(int id, String clientes_cpf) {

        connect();
        boolean validado;
        String sql = "UPDATE pedido SET status='Pedido finalizado' WHERE id=? AND clientes_cpf=?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            pst.setString(2, clientes_cpf);
            pst.execute();
            validado = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            validado = false;
        } finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException ex) {
                System.out.println("Erro = " + ex.getMessage());
            }
        }
        return validado;
    }
}

