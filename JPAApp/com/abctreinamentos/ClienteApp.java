/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abctreinamentos;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAAppPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            
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
                        TypedQuery<Cliente> query = em.createQuery("" + "Select c from Cliente", Cliente.class);
                        List<Cliente> clientes = query.getResultList();
                        clientes.forEach(System.out::println);
                        break;
                    }
                    case 2: {
                        entrada.nextLine();
                        System.out.println("[2] Consultar Cliente Especifico");
                        System.out.println("Favor informar o ID do cliente >>>");
                        id = entrada.nextInt();
                        Cliente cliente = em.find(Cliente.class, id);
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
                        tx.begin();
                        em.persist(cliente);
                        tx.commit();
                        em.close();
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
                        Cliente cliente = new Cliente(id, cpf, nome, email);
                        tx.begin();
                        em.merge(cliente);
                        tx.commit();
                        em.close();
                        break;
                    }
                    case 5: {
                        System.out.println("[5] Excluir o Cliente");
                        System.out.println("Favor informar o ID do cliente >>>");
                        id = entrada.nextInt();
                        Cliente cliente = em.find(Cliente.class, id);
                        tx.begin();
                        em.remove(cliente);
                        tx.commit();
                        em.close();
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
