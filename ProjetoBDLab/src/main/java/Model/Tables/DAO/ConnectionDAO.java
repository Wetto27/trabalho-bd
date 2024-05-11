package Model.Tables.DAO;

import java.sql.*;

public abstract class ConnectionDAO {
    Connection connection; //conexão
    PreparedStatement pst; // declaração(query) preparada - código em sql
    Statement statement; //declaração(query) - código em sql
    ResultSet resultSet; //resposta do banco

    String database = "mydb";//nome do BD
    String user = "root";
    String password = "root";
    String url = "jdbc:mysql://localhost:3306/" + database + "?useTimezone=true&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";


    public void connect() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexao deu certo!");
        } catch(SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());
        }
    }
}
