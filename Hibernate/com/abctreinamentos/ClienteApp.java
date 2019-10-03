/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abctreinamentos;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author rhuan
 */
public class ClienteApp {

    public static void main(String[] args) {
        try {
            Scanner entrada = new Scanner(System.in);
            int opcao = 0, id;
            String nome, email, cpf;
            ClienteDAO dao = new ClienteDAO();
            
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
                        System.out.println("[1] Consultar Todos os Clientes");
                        List<Cliente> clientes = dao.findAll();
                        clientes.forEach(System.out::println);
                        break;
                    }
                    case 2: {
                        entrada.nextLine();
                        System.out.println("[2] Consultar Cliente Especifico");
                        System.out.println("Favor informar o ID do cliente >>>");
                        id = entrada.nextInt();
                        System.out.println(dao.find(id));
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
                        dao.persist(cliente);
                        break;
                    }
                    case 4: {
                        System.out.println("[4] alterar o Cliente");
                        System.out.println("Favor informar o ID do Cliente >>>");
                        id = entrada.nextInt();
                        System.out.println("Favor informar o cpf >>>");
                        cpf = entrada.next();
                        entrada.nextLine(); // eliminar buffer teclado
                        System.out.println("Favor informar o nome >>>");
                        nome = entrada.nextLine();
                        System.out.println("Favor informar o email >>>");
                        email = entrada.nextLine();
                        Cliente cliente = new Cliente(id,cpf, nome, email);
                        dao.merge(cliente);
                        break;
                    }
                    case 5: {
                        System.out.println("[5] Excluir o Cliente");
                        System.out.println("Favor informar o ID do cliente >>>");
                        id = entrada.nextInt();
                        Cliente cliente = dao.find(id);
                        dao.delete(cliente);
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
