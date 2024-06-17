package Model.Tables.DAO;

import Model.Classes.pedidos;
import Model.Tables.DAO.ConnectionDAO;

import java.sql.SQLException;

public class pedidosDAO extends ConnectionDAO {

    boolean sucesso = false;

    //------------------------INSERIR NOVO PEDIDOS NO DATABASE----------------------------
    public boolean insertpedidos(pedidos pedido) {

        connect();
        String sql = "INSERT INTO Pedido (id,livros,data,clientes_cpf) values (?,?,?,?)";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, pedido.getIdPedido());
            pst.setString(2, pedido.getLivros());
            pst.setString(3, pedido.getData());
            pst.setString(4, pedido.getPk_cpfCliente());
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
}