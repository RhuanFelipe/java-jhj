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
public class CursoApp {

    public static void main(String[] args) {
        try {
            Scanner entrada = new Scanner(System.in);
            int opcao = 0, codCurso;
            String nome, url;
            long valor;

            CursoDAO dao = new CursoDAO();

            while (opcao != 6) {
                System.out.println("Sistema de Gerenciamento de Cursos");
                System.out.println("*******************************************************");
                System.out.println("Digite [1] para consultar todos os cursos");
                System.out.println("Digite [2] para consultar um curso especifico");
                System.out.println("Digite [3] para cadastrar um novo curso");
                System.out.println("Digite [4] para alterar um curso");
                System.out.println("Digite [5] para excluir um curso");
                System.out.println("Digite [6] para sair do sistema");
                System.out.println("*******************************************************");
                opcao = entrada.nextInt();

                switch (opcao) {
                    case 1: {
                        System.out.println("[1] Consultar Todos os Cursos");
                        List<Curso> cursos = dao.findAll();
                        cursos.forEach(System.out::println);
                        break;
                    }
                    case 2: {
                        entrada.nextLine();
                        System.out.println("[2] Consultar Curso Especifico");
                        System.out.println("Favor informar o ID do curso >>>");
                        codCurso = entrada.nextInt();
                        System.out.println(dao.find(codCurso));
                        break;
                    }
                    case 3: {
                        System.out.println("[3] Cadastrar o Curso");
                        System.out.println("Favor informar o código do curso >>>");
                        codCurso = entrada.nextInt();
                        entrada.nextLine(); // eliminar buffer teclado
                        System.out.println("Favor informar o nome do curso >>>");
                        nome = entrada.nextLine();
                        System.out.println("Favor informar o valor do curso >>>");
                        valor = entrada.nextLong();
                        entrada.nextLine(); // eliminar buffer teclado
                        System.out.println("Favor informar a url do curso >>>");
                        url = entrada.nextLine();
                        Curso curso = new Curso(codCurso, nome, valor, url);
                        dao.persist(curso);
                        break;
                    }
                    case 4: {
                        System.out.println("[3] Alterar o Curso");
                        System.out.println("Favor informar o código do curso >>>");
                        codCurso = entrada.nextInt();
                        entrada.nextLine(); // eliminar buffer teclado
                        System.out.println("Favor informar o nome do curso>>>");
                        nome = entrada.next();
                        System.out.println("Favor informar o valor do curso >>>");
                        valor = entrada.nextLong();
                        entrada.nextLine(); // eliminar buffer teclado
                        System.out.println("Favor informar a url do curso >>>");
                        url = entrada.nextLine();
                        Curso curso = new Curso(codCurso, nome, valor, url);
                        dao.merge(curso);
                        break;
                    }
                    case 5: {
                        System.out.println("[5] Excluir o Curso");
                        System.out.println("Favor informar o código do curso >>>");
                        codCurso = entrada.nextInt();
                        Curso curso = dao.find(codCurso);
                        dao.delete(curso);
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
