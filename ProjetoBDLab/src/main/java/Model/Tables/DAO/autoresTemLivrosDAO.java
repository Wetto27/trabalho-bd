package Model.Tables.DAO;

import Model.Classes.livros;

import java.sql.SQLException;

import static javax.management.remote.JMXConnectorFactory.connect;

public class autoresTemLivrosDAO extends ConnectionDAO{

    boolean sucesso = false;

    //------------------------INSERIR NOVA RELAÇÂO NA TABELA N:M DE AUTORES E LIVROS----------------------------
    public boolean insertAutorOnLivro(int livros_id, int autores_id){

        connect();
        String sql = "INSERT INTO autores_tem_livros (livros_id, autores_id) VALUES (?,?)";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, livros_id);
            pst.setInt(2, autores_id);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro de conexao = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException e) {
                System.out.println("Erro de conexao = " + e.getMessage());
            }
        }
        return sucesso;
    }

    //------------------------DELETAR RELAÇÂO ESPECIFICA NA TABELA N:M DE PEDIDOS E LIVROS----------------------------
    public boolean deleteAutorFromLivro(int autores_id, int livros_id){

        connect();
        boolean verifica;
        String sql = "DELETE FROM autores_tem_livros WHERE Id_Autor = ? AND Id_Livro = ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, livros_id);
            pst.setInt(2, autores_id);
            pst.execute();
            verifica = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            verifica = false;
        } finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException ex) {
                System.out.println("Erro = " + ex.getMessage());
            }
        }
        return verifica;
    }

    //------------------------DELETAR TODAS AS RELAÇÕES DE UM AUTOR ESPECIFICO----------------------------
    public boolean deleteFromAutorHasLivro(int autores_id) {

        connect();

        String sql = "DELETE FROM autores_tem_livros WHERE Autor_idAutor = ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, autores_id);
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
