package Model.Tables.DAO;

import Model.Classes.autores;
import Model.Classes.empregados;
import com.mysql.cj.jdbc.ConnectionImpl;

import java.sql.SQLException;
import java.util.ArrayList;

import static javax.management.remote.JMXConnectorFactory.connect;

public class autoresDAO extends ConnectionDAO {

    boolean sucesso = false;

    //------------------------INSERIR NOVO REGISTRO DE AUTOR NO DATABASE----------------------------
    public boolean insertAutores(autores autores) {

        connect();

        String sql = "insert into autores (id, nome, email) values(?,?,?)";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, autores.getId());
            pst.setString(2, autores.getNome());
            pst.setString(3, autores.getEmail());
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro de conexao " + ex.getMessage());
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

    //------------------------ALTERANDO INFORMAÇÕES DO AUTOR NO DATABASE----------------------------
    public boolean updateAutor(int id, String novoEmail) {

        connect();
        boolean validado;
        String sql = "UPDATE autores SET email= ? WHERE id=?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, novoEmail);
            pst.setInt(2, id);
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

//------------------------DELETANDO AUTOR DO DATABASE----------------------------
public boolean deleteAutor(int id) {

    connect();

    String sql = "DELETE FROM autores WHERE id =?";

    try {
        pst = connection.prepareStatement(sql);
        pst.setInt(1, id);
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

//------------------------BUSCAR AUTOR NO DATABASE----------------------------
public void selectAutor() {
    ArrayList<autores> listaDeLivros = new ArrayList<>();

    connect();

    String sql = "SELECT * FROM autores";

    try {
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql); //ref. a tabela resultante da busca
        while (resultSet.next()) {

            autores autorTemp = new autores(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getString("email"));
            System.out.println("Id = " + autorTemp.getId());
            System.out.println("Nome = " + autorTemp.getNome());
            System.out.println("Email = " + autorTemp.getEmail());
            System.out.println("---------------------------------");
            listaDeLivros.add(autorTemp);
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
}