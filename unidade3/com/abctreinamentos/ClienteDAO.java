/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unidade3.com.abctreinamentos;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author rhuan.silva
 */
public class ClienteDAO {

    static String url = "jdbc:mysql://localhost:3306/curso_java8";
    static String usuario = "root";
    static String senha = "";
    static Connection conexao;

    public static void conectar() throws SQLException {
        conexao = DriverManager.getConnection(url, usuario, senha);
        DatabaseMetaData meta = conexao.getMetaData();
        conexao.setAutoCommit(false);
        System.out.println(">>> conectado na base de dados = " + meta.getDatabaseProductVersion());
    }

    public static void desconectar() throws SQLException {
        conexao.close();
    }

    public static void inserir(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO cliente(cpf,nome,email) values('" + cliente.getCpf() + "',"
                + "'" + cliente.getNome() + "','" + cliente.getCpf() + "')";

        Statement statement = conexao.createStatement();
        statement.execute(sql);
        conexao.commit();
    }

    public static Cliente consultar(String cpf) throws SQLException {
        String sql = "SELECT * FROM cliente where cpf='" + cpf + "'";
        Statement statement = conexao.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        Cliente cliente = null;

        while (rs.next()) {
            cliente = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
        }
        return cliente;
    }

    public static List<Cliente> consultarTodos() throws SQLException {
        String sql = "SELECT * FROM cliente";
        Statement statement = conexao.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Cliente> clientes = new LinkedList<>();

        while (rs.next()) {
            Cliente cliente = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            clientes.add(cliente);
        }
        return clientes;
    }

    public static void alterar(Cliente cliente) throws SQLException {
        String sql = "update cliente set nome = '" + cliente.getNome() + "',  "
                + "email = '" + cliente.getEmail() + "' where cpf = '" + cliente.getCpf() + "'";

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
                        List<Cliente> clientes = ClienteDAO.consultarTodos();
                        clientes.forEach(System.out::println);
                        System.out.println("Total de Clientes >>> " + clientes.size());
                        System.out.println(opcao);
                        break;
                    }
                    case 2: {
                        entrada.nextLine();
                        System.out.println("[2] Consultar Cliente Especifico");
                        System.out.println("Favor informar o cpf >>>");
                        cpf = entrada.nextLine();
                        Cliente cliente = ClienteDAO.consultar(cpf);
                        System.out.println(cliente);
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
                        Cliente cliente = new Cliente(cpf, nome, email);
                        ClienteDAO.inserir(cliente);
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
                        Cliente cliente = new Cliente(cpf, nome, email);
                        ClienteDAO.alterar(cliente);
                        break;
                    }
                    case 5: {
                        System.out.println("[5] excluir o Cliente");
                        System.out.println("Favor informar o cpf >>>");
                        cpf = entrada.next();
                        ClienteDAO.excluir(cpf);
                        break;
                    }
                    case 6: { // Sair
                        System.out.println("Encerrando sistema...");
                        break;
                    }
                }
            }
            desconectar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
