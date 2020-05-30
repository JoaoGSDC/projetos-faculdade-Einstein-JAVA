package correios.einstein.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnderecosDAO {

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

    public ResultSet consultar() {
        try {
            ResultSet rs = null;
            PreparedStatement stmt
                    = (PreparedStatement) this.con.prepareStatement(
                            "SELECT l.*, b.*, c.* FROM logradouros AS l "
                            + "INNER JOIN bairros AS b ON l.bairros_idbairros = b.idbairros "
                            + "INNER JOIN cidades AS c ON l.idcidade = c.idcidade");
            rs = stmt.executeQuery();
            return rs;
        } catch (SQLException ex) {
            System.err.println("Erro INSERT: " + ex);
        }
        return null;
    }

    public ResultSet consultarCep(int cep) {
        try {
            ResultSet rs = null;
            PreparedStatement stmt
                    = (PreparedStatement) this.con.prepareStatement(
                            "SELECT l.*, b.*, c.* FROM logradouros AS l "
                            + "INNER JOIN bairros AS b ON l.bairros_idbairros = b.idbairros "
                            + "INNER JOIN cidades AS c ON l.idcidade = c.idcidade "
                            + "WHERE l.cep = " + cep);
            rs = stmt.executeQuery();
            return rs;
        } catch (SQLException ex) {
            System.err.println("Erro INSERT: " + ex);
        }
        return null;
    }

    public ResultSet consultarEndereco(int id) {
        try {
            ResultSet rs = null;
            PreparedStatement stmt
                    = (PreparedStatement) this.con.prepareStatement(
                            "SELECT l.*, b.*, c.* FROM logradouros AS l "
                            + "INNER JOIN bairros AS b ON l.bairros_idbairros = b.idbairros "
                            + "INNER JOIN cidades AS c ON l.idcidade = c.idcidade "
                            + "WHERE l.idlogradouros = " + id
                    );
            rs = stmt.executeQuery();
            return rs;
        } catch (SQLException ex) {
            System.err.println("Erro INSERT: " + ex);
        }
        return null;
    }
}
