package Model.Tables.DAO;

import Model.Classes.livros;

import java.sql.SQLException;

import static javax.management.remote.JMXConnectorFactory.connect;

public class autoresTemLivrosDAO extends ConnectionDAO{

    boolean sucesso = false;

    //------------------------INSERIR NOVA RELAÇÂO NA TABELA N:M DE AUTORES E LIVROS----------------------------
    public boolean insertLivroOnAutor(int livros_id, String autores_nome){

        connect();
        String sql = "INSERT INTO autores_tem_livros (livros_id, autores_nome) VALUES (?,?)";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, livros_id);
            pst.setString(2, autores_nome);
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

    //------------------------DELETAR TODAS AS RELAÇÕES DE UM AUTOR ESPECIFICO----------------------------
    public boolean deleteFromAutorHasLivro(String autores_nome) {

        connect();

        String sql = "DELETE FROM autores_tem_livros WHERE autores_nome = ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, autores_nome);
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
