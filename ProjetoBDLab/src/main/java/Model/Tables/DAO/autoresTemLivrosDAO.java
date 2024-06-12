package Model.Tables.DAO;

import Model.Classes.livros;

import java.sql.SQLException;

import static javax.management.remote.JMXConnectorFactory.connect;

public class autoresTemLivrosDAO extends ConnectionDAO{

    boolean sucesso = false;

    //------------------------INSERIR NOVA RELAÇÂO NA TABELA N:M DE AUTORES E LIVROS----------------------------
    public boolean insertAutorOnLivro(int Id_Autor, int Id_Livro){

        connect();
        String sql = "INSERT INTO autores (Id_Livro, Id_Autor) VALUES (?,?)";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, Id_Livro);
            pst.setInt(2, Id_Autor);
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
    public boolean deleteAutorFromLivro(int Id_Autor, int Id_Livro){

        connect();
        boolean verifica;
        String sql = "DELETE FROM autores_tem_livros WHERE Id_Autor = ? AND Id_Livro = ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, Id_Livro);
            pst.setInt(2, Id_Autor);
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
    public boolean deleteFromAutorHasLivro(int idAutor) {

        connect();

        String sql = "DELETE FROM autores_tem_livros WHERE Autor_idAutor = ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, idAutor);
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
