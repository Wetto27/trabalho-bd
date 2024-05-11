package Model.Tables.DAO;

import Model.Classes.livros;

import java.sql.SQLException;
import java.util.ArrayList;

import static javax.management.remote.JMXConnectorFactory.connect;

public class livrosDAO extends ConnectionDAO{

    boolean sucesso = false;

    //------------------------INSERIR NOVO LIVRO NO DATABASE----------------------------
    public boolean insertLivro(livros livro) {

        connect();
        String sql = "INSERT INTO livros (id, titulo, autores, tema, empregadoCPF) values (?,?,?,?,?)";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, livro.getId());
            pst.setString(2, livro.getTitulo());
            pst.setString(3, livro.getAutores());
            pst.setString(4, livro.getTema());
            pst.setString(5, livro.getEmpregadoCPF());
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

    //------------------------BUSCAR LIVROS NO DATABASE----------------------------
    public void selectLivro() {
        ArrayList<livros> listaDeLivros = new ArrayList<>();

        connect();

        String sql = "SELECT * FROM livros";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql); // ref. a tabela resultante da busca
            while(resultSet.next()) {
                livros livrotemp = new livros(resultSet.getInt("Idlivro"), resultSet.getString("Titulo"), resultSet.getString("Autores"), resultSet.getString("Tema"),resultSet.getString("Empregado CPF"));
                System.out.println("Id = " + livrotemp.getId());
                System.out.println("Titulo = " + livrotemp.getTitulo());
                System.out.println("Autores = " + livrotemp.getAutores());
                System.out.println("Tema = " + livrotemp.getTema());
                System.out.println("Empregado CPF = " + livrotemp.getEmpregadoCPF());
                System.out.println("---------------------------------");
                listaDeLivros.add(livrotemp);
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

    //------------------------BUSCAR LIVRO ESPECIFICO NO DATABASE----------------------------
    public boolean selectLivroId(int Id_Livro) {

        boolean verificado = false;
        connect();

        String sql = "SELECT * FROM livros WHERE IdLivro = ?";
        try {

            pst = connection.prepareStatement(sql);
            pst.setInt(1, Id_Livro);
            resultSet = pst.executeQuery();

            while(resultSet.next()) {
                livros livroTemp = new livros(resultSet.getInt("Idlivro"), resultSet.getString("Titulo"), resultSet.getString("Autores"), resultSet.getString("Tema"),resultSet.getString("Empregado CPF"));
                if(livroTemp.getId() == Id_Livro) {
                    verificado = true;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
        } finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException ex) {
                System.out.println("Erro = " + ex.getMessage());
            }
        }
        return verificado;
    }

    //------------------------BUSCAR AUTOR DE LIVRO ESPECIFICO NO DATABASE----------------------------
    public String selectLivroAutores(int Id_Livro) {

        connect();

        String autores = null;
        String sql = "SELECT * FROM livros WHERE IdLivro = ?";

        try {

            pst = connection.prepareStatement(sql);
            pst.setInt(1, Id_Livro);
            resultSet = pst.executeQuery();

            while(resultSet.next()) {
                livros livroTemp = new livros(resultSet.getInt("Idlivro"), resultSet.getString("Titulo"), resultSet.getString("Autores"), resultSet.getString("Tema"),resultSet.getString("Empregado CPF"));
                        autores = livroTemp.getAutores();
            }
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
        } finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException ex) {
                System.out.println("Erro = " + ex.getMessage());
            }
        }
        return autores;

    }


}
