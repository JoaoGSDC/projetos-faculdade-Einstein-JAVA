/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula.poo2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Fernando Bryan Frizzarin
 */
public class dados {

    Connection con;

    public boolean conecta() {
        boolean retorno = false;
        try {
           con = DriverManager.getConnection("jdbc:mysql://localhost/prova1?useTimezone=true&serverTimezone=UTC","root","admin");
           retorno = true;
        } catch (SQLException e) {
            System.err.println("Erro de conexão:\n" + e);
        }
        return retorno;
    }
    
    public boolean insere(String marca, String modelo, int ano) {
       boolean retorno = false;
       try {
            PreparedStatement stmt = (PreparedStatement) this.con.prepareStatement("INSERT INTO carro (marca, modelo, ano) values ('" + marca + "', '" + modelo + "', " + ano + ");");
            stmt.execute();
            stmt.close();
            retorno = true;
       } catch (SQLException ex) {
	System.err.println("Erro INSERT: " + ex);
       }
       return retorno;
   }
    
    public boolean editar(String marca, String modelo, int ano, String marcaPk) {
       boolean retorno = false;
       try {
            PreparedStatement stmt = (PreparedStatement) this.con.prepareStatement("UPDATE carro SET marca = '" + marca + "', modelo = '" + modelo + "', ano = " + ano + " WHERE marca = '" + marcaPk + "';");
            stmt.execute();
            stmt.close();
            retorno = true;
       } catch (SQLException ex) {
	System.err.println("Erro UPDATE: " + ex);
       }
       return retorno;
   }
    
    public boolean excluir(String marca) {
       boolean retorno = false;
       try {
            PreparedStatement stmt = (PreparedStatement) this.con.prepareStatement("DELETE FROM carro WHERE marca = '" + marca + "';");
            stmt.execute();
            stmt.close();
            retorno = true;
       } catch (SQLException ex) {
	System.err.println("Erro DELETE: " + ex);
       }
       return retorno;
   }
    
    public ResultSet consulta() {
        ResultSet rs = null;
        try {
            PreparedStatement stmt = (PreparedStatement)
            this.con.prepareStatement("SELECT * FROM carro");
            rs = stmt.executeQuery();
        } catch (Exception e) {
            System.err.println("Erro CONSULTA: " + e);
        }
        return rs;
    }
    
    public ResultSet buscar(String busca) {
        ResultSet rs = null;
        try {
            PreparedStatement stmt = (PreparedStatement) this.con.prepareStatement("SELECT * FROM carro WHERE marca LIKE '%" + busca + "%';");
            rs = stmt.executeQuery();          
        } catch (Exception e) {
            System.err.println("Erro CONSULTA: " + e);
        }
        return rs;
    }

}

