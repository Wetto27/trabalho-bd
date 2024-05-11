package Model.Tables.DAO;

import Model.Classes.autores;

import java.sql.SQLException;

import static javax.management.remote.JMXConnectorFactory.connect;

public class autoresDAO extends ConnectionDAO{

    boolean sucesso = false;

    //------------------------INSERIR NOVO REGISTRO DE CLIENTE NO DATABASE----------------------------
    public boolean insertAutores(autores autor) {

        connect();

        String sql = "insert into autores (IdAutor, nome, email) values(?,?,?)";

        try{
            pst = connection.prepareStatement(sql);
            pst.setInt(1,autor.getId());
            pst.setString(2, autor.getNome());
            pst.setString(3,autor.getEmail());
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

    //------------------------SELECIONAR CLIENTE POR CPF NO DATABASE----------------------------
    public boolean selectAutoresID(int id) {

        connect();
        boolean verificado = false;

        String sql = "SELECT * FROM autores";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql); // ref. a tabela resultante da busca
            while (resultSet.next()) {
                autores autorTemp = new autores(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getString("email"));
                if(autorTemp.getId() == id) {
                    verificado = true;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            verificado = false;
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException ex) {
                System.out.println("Erro = " + ex.getMessage());
            }
        }
        return verificado;
    }

    //------------------------SELECIONAR NOME E EMAIL DE AUTOR ESPECÌFICO NO DATABASE----------------------------
    public String selectAutorNomeEmail(int id) {

        connect();

        String nome = null;
        String email = null;
        String sql = "SELECT * FROM autores";

        try {

            pst = connection.prepareStatement(sql);
            resultSet = pst.executeQuery();

            while(resultSet.next()) {
                autores autorTemp = new autores(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getString("email"));

                if(autorTemp.getId() == id) {
                    nome = autorTemp.getNome();
                    email = autorTemp.getEmail();
                }
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
        String info = nome + "" + email;
        return info;
    }


}
