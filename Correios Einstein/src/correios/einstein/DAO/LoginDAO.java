package correios.einstein.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    Connection con;

    public boolean conectar() {
        boolean retorno = false;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/prova2?useTimezone=true&serverTimezone=UTC", "root", "admin");
            retorno = true;
        } catch (SQLException e) {
            System.err.println("Erro de conexão:\n" + e);
        }
        return retorno;
    }

    public void salvar(String username, String password) {
        try {
            PreparedStatement stmt;
            stmt = (PreparedStatement) this.con.prepareStatement(
                    "INSERT INTO login ("
                    + "username, password) "
                    + "VALUES ("
                    + "'" + username + "', "
                    + "'" + password + "');");
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            System.err.println("Erro INSERT: " + ex);
        }
    }

    public ResultSet consultar(String username, String password) {
        try {
            ResultSet rs = null;
            PreparedStatement stmt
                    = (PreparedStatement) this.con.prepareStatement(
                            "SELECT * FROM login WHERE username = '" + username + "' AND password = '" + password + "';");
            rs = stmt.executeQuery();
            return rs;
        } catch (SQLException ex) {
            System.err.println("Erro INSERT: " + ex);
        }
        return null;
    }
}
