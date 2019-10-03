/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidade3;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author rhuan
 */
public class acessoDB {

    static String url = "jdbc:mysql://localhost/curso_java8";
    static String usuario = "root";
    static String senha = "";
    static Connection conexao;

    public static void conectar() throws SQLException {
        conexao = DriverManager.getConnection(url, usuario, senha);
        conexao.setAutoCommit(false);
    }

    public static void consultarCliente() throws SQLException {
        String consulta = "SELECT * FROM cliente";
        Statement statement = conexao.createStatement();
        ResultSet rs = statement.executeQuery(consulta);
        while (rs.next()) {
            JOptionPane.showMessageDialog(null,"ID: " + rs.getString(1) + " cpf: " + rs.getString(2) + " nome: " + rs.getString(3) + " email: " + rs.getString(4));
        }

    }

    public static void mostrarMetaInfoDB() throws SQLException {
        DatabaseMetaData meta = conexao.getMetaData();
        String fabricante = meta.getDatabaseProductName();
        String versao = meta.getDatabaseProductVersion();
        JOptionPane.showMessageDialog(null, "Fabricante = " + fabricante + ", Versão = " + versao);
    }

    public static void main(String[] args) {
        try {
            conectar();
            mostrarMetaInfoDB();
            consultarCliente();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
