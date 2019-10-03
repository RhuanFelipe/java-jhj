/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidade3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author rhuan
 */
public class ClienteApp {

    static String url = "jdbc:mysql://localhost:3306/curso_java8";
    static String usuario = "root";
    static String senha = "";
    static Connection conexao;

    public static void conectar() throws SQLException {
        conexao = DriverManager.getConnection(url, usuario, senha);
        conexao.setAutoCommit(false);
    }

    public static void desconectar() throws SQLException {
        conexao.close();
    }

    public static void inserir(String cpf, String nome, String email) throws SQLException {
        String sql = "INSERT INTO cliente(cpf,nome,email) values('" + cpf + "','" + nome + "','" + email + "')";
        Statement statement = conexao.createStatement();
        statement.execute(sql);
        conexao.commit();
    }
    
       public static void inserirPS(String cpf, String nome, String email) throws SQLException {
        String sql = "INSERT INTO cliente(cpf,nome,email) values(?,?,?)";
        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setString(1, cpf);
        statement.setString(2, nome);
        statement.setString(3, email);
        statement.executeUpdate();
        conexao.commit();
    }

    public static void consultar(String cpf) throws SQLException {
        String sql = "SELECT * FROM cliente where cpf='" + cpf + "'";
        Statement statement = conexao.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            System.out.println("cpf:" + rs.getBigDecimal(1) + " nome: " + rs.getString(2) + " email: " + rs.getString(3));
        }
    }

    public static void consultarTodos() throws SQLException {
        String sql = "SELECT * FROM cliente";
        Statement statement = conexao.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        int contador = 0;

        while (rs.next()) {
            System.out.println("ID: " + rs.getInt(1) + " cpf: " + rs.getString(2) + " nome: " + rs.getString(3) + " email: " + rs.getString(4));
            contador++;
            System.out.println("*******************************************************************************");
        }
        System.out.println("Numero de clientes listados >>>> " + contador);
    }

    public static void alterar(String cpf, String nome, String email) throws SQLException {
        String sql = "update cliente set nome = '" + nome + "',  email = '" + email + "' where cpf = '" + cpf + "'";
        Statement statement = conexao.createStatement();
        statement.executeUpdate(sql);
        conexao.commit();
    }

    public static void excluir(String cpf) throws SQLException {
        String sql = "delete from cliente where cpf='" + cpf + "'";
        Statement statement = conexao.createStatement();
        statement.executeUpdate(sql);
        conexao.commit();
    }

    public static void main(String[] args) {
        try {
            conectar();
            Scanner entrada = new Scanner(System.in);
            int opcao = 0;
            String nome, email, cpf;
            
            while (opcao != 6) {
                System.out.println("Sistema de Gerenciamento de Cliente");
                System.out.println("*******************************************************");
                System.out.println("Digite [1] para consultar todos os clientes");
                System.out.println("Digite [2] para consultar um cliente especifico");
                System.out.println("Digite [3] para cadastrar um novo cliente");
                System.out.println("Digite [4] para alterar um cliente");
                System.out.println("Digite [5] para excluir um cliente");
                System.out.println("Digite [6] para sair do sistema");
                System.out.println("*******************************************************");
                opcao = entrada.nextInt();

                switch (opcao) {
                    case 1: {
                        System.out.println("[1] Consultar Todos");
                        consultarTodos();
                        break;
                    }
                    case 2: {
                        entrada.nextLine();
                        System.out.println("[2] Consultar Cliente Especifico");
                        System.out.println("Favor informar o cpf >>>");
                        cpf = entrada.nextLine();
                        consultar(cpf);
                        break;
                    }
                    case 3: {
                        System.out.println("[3] Cadastrar o Cliente");
                        System.out.println("Favor informar o cpf >>>");
                        cpf = entrada.next();
                        entrada.nextLine(); // eliminar buffer teclado
                        System.out.println("Favor informar o nome >>>");
                        nome = entrada.nextLine();
                        System.out.println("Favor informar o email >>>");
                        email = entrada.nextLine();
                        //inserir(cpf, nome, email);
                        inserirPS(cpf, nome, email);
                        break;
                    }
                    case 4: {
                        System.out.println("[4] alterar o Cliente");
                        System.out.println("Favor informar o cpf >>>");
                        cpf = entrada.next();
                        entrada.nextLine(); // eliminar buffer teclado
                        System.out.println("Favor informar o nome >>>");
                        nome = entrada.nextLine();
                        System.out.println("Favor informar o email >>>");
                        email = entrada.nextLine();
                        alterar(cpf, nome, email);
                        break;
                    }
                    case 5: {
                        System.out.println("[5] excluir o Cliente");
                        System.out.println("Favor informar o cpf >>>");
                        cpf = entrada.next();
                        excluir(cpf);
                        break;
                    }
                    case 6: { // Sair
                        System.out.println("Encerrando sistema...");
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
