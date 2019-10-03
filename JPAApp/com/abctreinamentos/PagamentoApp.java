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
public class PagamentoApp {

    public static void main(String[] args) {
        try {
            Scanner entrada = new Scanner(System.in);
            int opcao = 0, id, codCurso;
            String datainscricao;
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAAppPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();

            while (opcao != 6) {
                System.out.println("Sistema de Gerenciamento de Pagamento");
                System.out.println("*******************************************************");
                System.out.println("Digite [1] para consultar todos os pagamentos");
                System.out.println("Digite [2] para consultar um pagamento especifico");
                System.out.println("Digite [3] para cadastrar um novo pagamento");
                System.out.println("Digite [4] para alterar um pagamento");
                System.out.println("Digite [5] para excluir um pagamento");
                System.out.println("Digite [6] para sair do sistema");
                System.out.println("*******************************************************");
                opcao = entrada.nextInt();

                switch (opcao) {
                    case 1: {
                        System.out.println("[1] Consultar Todos os Pagamentos");
                        TypedQuery<Pagamento> query = em.createQuery(""
                                + "Select p from Pagamento p", Pagamento.class);
                        List<Pagamento> pagamentos = query.getResultList();
                        pagamentos.forEach(System.out::println);
                        break;
                    }
                    case 2: {
                        entrada.nextLine();
                        System.out.println("[2] Consultar Pagamento Especifico");
                        System.out.println("Favor informar o ID do cliente >>>");
                        id = entrada.nextInt();
                        System.out.println("Favor informar o código do curso >>>");
                        codCurso = entrada.nextInt();
                        PagamentoID pgtoid = new PagamentoID(id, codCurso);
                        Pagamento pagamento = em.find(Pagamento.class, pgtoid);
                        System.out.println(pagamento);
                        break;
                    }
                    case 3: {
                        System.out.println("[3] Cadastrar o Pagamento");
                        System.out.println("Favor informar o ID do cliente >>>");
                        id = entrada.nextInt();
                        System.out.println("Favor informar o código do curso >>>");
                        codCurso = entrada.nextInt();
                        entrada.nextLine(); // eliminar buffer teclado
                        System.out.println("Favor informar data de inscrição >>>");
                        datainscricao = entrada.next();
                        PagamentoID pgId = new PagamentoID(id, codCurso);
                        Pagamento pagamento = new Pagamento(pgId, datainscricao);
                        tx.begin();
                        em.persist(pagamento);
                        tx.commit();
                        break;
                    }
                    case 4: {
                        System.out.println("[3] Alterar o Pagamento");
                        System.out.println("Favor informar o ID do cliente >>>");
                        id = entrada.nextInt();
                        System.out.println("Favor informar o código do curso >>>");
                        codCurso = entrada.nextInt();
                        entrada.nextLine(); // eliminar buffer teclado
                        System.out.println("Favor informar data de inscrição >>>");
                        datainscricao = entrada.next();
                        PagamentoID pgId = new PagamentoID(id, codCurso);
                        Pagamento pagamento = new Pagamento(pgId, datainscricao);
                        tx.begin();
                        em.merge(pagamento);
                        tx.commit();
                        break;
                    }
                    case 5: {
                        System.out.println("[5] Excluir o Pagamento");
                        System.out.println("Favor informar o ID do cliente >>>");
                        id = entrada.nextInt();
                        System.out.println("Favor informar o código do curso >>>");
                        codCurso = entrada.nextInt();
                        PagamentoID pgId = new PagamentoID(id, codCurso);
                        Pagamento pagamento = em.find(Pagamento.class, pgId);
                        tx.begin();
                        em.remove(pagamento);
                        tx.commit();

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
