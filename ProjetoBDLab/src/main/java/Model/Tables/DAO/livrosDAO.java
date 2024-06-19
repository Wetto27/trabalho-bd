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
        String sql = "INSERT INTO livros (id, titulo, autores, tema, empregado_cpf) values (?,?,?,?,?)";

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
    public ArrayList<livros> selectLivro() {
        ArrayList<livros> listaDeLivros = new ArrayList<>();
        connect();
        String sql = "SELECT * FROM livros";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                livros livroTemp = new livros(
                        resultSet.getInt("Id"),
                        resultSet.getString("Titulo"),
                        resultSet.getString("Autores"),
                        resultSet.getString("Tema"),
                        resultSet.getString("empregado_cpf")
                );
                System.out.println("ID = " + livroTemp.getId());
                System.out.println("Título = " + livroTemp.getTitulo());
                System.out.println("Autor(es) = " + livroTemp.getAutores());
                System.out.println("Tema = " + livroTemp.getTema());
                System.out.println("---------------------------------");
            }
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException ex) {
                System.out.println("Erro = " + ex.getMessage());
            }
        }
        return listaDeLivros;
    }

    //------------------------BUSCAR LIVRO ESPECIFICO NO DATABASE----------------------------
    public boolean selectLivroTitulo(String tituloLivro) {

        boolean verificado = false;
        connect();

        String sql = "SELECT * FROM livros WHERE titulo = ?";
        try {

            pst = connection.prepareStatement(sql);
            pst.setString(1, tituloLivro);
            resultSet = pst.executeQuery();

            while(resultSet.next()) {
                livros livroTemp = new livros(resultSet.getInt("id"), resultSet.getString("Titulo"), resultSet.getString("Autores"), resultSet.getString("Tema"),resultSet.getString("empregado_cpf"));
                if(livroTemp.getTitulo().equalsIgnoreCase(tituloLivro)) {
                    System.out.println("ID = " + livroTemp.getId());
                    System.out.println("Título = " + livroTemp.getTitulo());
                    System.out.println("Autor(es) = " + livroTemp.getAutores());
                    System.out.println("Tema = " + livroTemp.getTema());
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
}
