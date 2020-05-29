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
public class EnderecosDAO {

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

    public void salvar(int idCidade, int idBairro, int idLogradouro, String uf, String descricaoLogradouro, String descricaoCidade, String descricaoBairro, int cepLogradouro, int cepCidade) {
        try {
            PreparedStatement stmt;
            stmt = (PreparedStatement) this.con.prepareStatement(
                    // ------ UPDATE TABELA CIDADES
                    "UPDATE cidades SET "
                    + "descricao = '" + descricaoCidade + "', "
                    + "cep = " + cepCidade + ", "
                    + "uf = '" + uf + "' "
                    + "WHERE idcidade = " + idCidade + "; "
                    // ------ UPDATE TABELA BAIRROS
                    + "UPDATE bairros SET "
                    + "descricao = '" + descricaoBairro + "', "
                    + "uf = '" + uf + "', "
                    + "cidades_idcidade = " + idCidade
                    + " WHERE idbairros = " + idBairro + "; "
                    // ------ UPDATE TABELA LOGRADOUROS
                    + "UPDATE logradouros SET "
                    + "descricao = '" + descricaoBairro + "', "
                    + "uf = '" + uf + "', "
                    + "idcidade = " + idCidade + ", "
                    + "bairros_idbairros = " + idBairro + ", "
                    + "cep = " + cepLogradouro
                    + " WHERE idlogradouros = " + idLogradouro + "; "
            );
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

    public void consultar() {
        try {
            ResultSet rs = null;
            PreparedStatement stmt = (PreparedStatement) this.con.prepareStatement("SELECT * FROM pessoas");
            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            System.err.println("Erro INSERT: " + ex);
        }
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
