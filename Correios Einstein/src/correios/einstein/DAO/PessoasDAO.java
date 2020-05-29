package correios.einstein.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author JoaoGSDC
 */
public class PessoasDAO {

    Connection con;

    public boolean conectar() {
        boolean retorno = false;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/prova1?useTimezone=true&serverTimezone=UTC", "root", "admin");
            retorno = true;
        } catch (SQLException e) {
            System.err.println("Erro de conexão:\n" + e);
        }
        return retorno;
    }

    public void salvar(String nome, int cep, String telefone, String email, String presencaWeb) {
        try {
            PreparedStatement stmt;
            stmt = (PreparedStatement) this.con.prepareStatement(
                    "INSERT INTO pessoas ("
                    + "nome, cep, telefone, email, presencaWeb) "
                    + "VALUES ("
                    + "'" + nome + "', "
                    + "'" + cep + "', "
                    + "'" + telefone + "', "
                    + "'" + email + "', "
                    + "'" + presencaWeb + "');");
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            System.err.println("Erro INSERT: " + ex);
        }
    }

    public boolean editar(int id, String nome, String cep, String telefone, String email, String presencaWeb) {
        boolean retorno = false;
        try {
            PreparedStatement stmt;
            stmt = (PreparedStatement) this.con.prepareStatement(
                    "UPDATE pessoas SET "
                    + "nome = '" + nome + "', "
                    + "cep = '" + cep + "', "
                    + "telefone = '" + telefone + "', "
                    + "presencaWeb = '" + presencaWeb + "' "
                    + "WHERE id = " + id + ";");
            stmt.execute();
            stmt.close();
            retorno = true;
        } catch (SQLException ex) {
            System.err.println("Erro UPDATE: " + ex);
        }
        return retorno;
    }

    public ResultSet consultar() {
        try {
            ResultSet rs = null;
            PreparedStatement stmt = (PreparedStatement) this.con.prepareStatement("SELECT * FROM pessoas");
            rs = stmt.executeQuery();
            return rs;
        } catch (SQLException ex) {
            System.err.println("Erro INSERT: " + ex);
        }
        return null;
    }

    public boolean excluir(int id) {
        boolean retorno = false;
        try {
            PreparedStatement stmt;
            stmt = (PreparedStatement) this.con.prepareStatement("DELETE FROM pessoas WHERE id = " + id);
            stmt.execute();
            stmt.close();
            retorno = true;
        } catch (SQLException ex) {
            System.err.println("Erro DELETE: " + ex);
        }
        return retorno;
    }
}
