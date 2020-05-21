/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correios.einstein.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author JoaoGSDC
 */
public class PessoasDAO {

    Connection con;

    public boolean conecta() {
        boolean retorno = false;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/prova1?useTimezone=true&serverTimezone=UTC", "root", "admin");
            retorno = true;
        } catch (SQLException e) {
            System.err.println("Erro de conexão:\n" + e);
        }
        return retorno;
    }

    public void Salvar(String nome, String endereco, String bairro, String cidade, String estado, String cep, String telefone, String email, String presencaWeb) {
        try {
            PreparedStatement stmt;
            stmt = (PreparedStatement) this.con.prepareStatement(
                    "INSERT INTO pessoas ("
                            + "nome, endereco, bairro, cidade, estado, cep, telefone, email, presencaWeb) "
                            + "VALUES ("
                            + "'" + nome + "', "
                            + "'" + endereco + "', "
                            + "'" + bairro + "', "
                            + "'" + cidade + "', "
                            + "'" + estado + "', "
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
}
