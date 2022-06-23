package com.digitalinovation;

import org.hibernate.criterion.CriteriaQuery;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.Root;
import java.util.List;


public class Main {

   public static void main(String[] args){

        // Passo inicial para criar um gerenciamento de entidades com o banco de dados especificado no arquivo persistence.xml
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("DIO-Parte1");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Cria instâncias para serem inseridas no banco de dados
        Estado novoEstado = new Estado("São Paulo", "SP");
        Aluno novoAluno = new Aluno("Kayke", 27, novoEstado );

        //Inicia uma transação para adicionar as instâncias no banco de dados
        entityManager.getTransaction().begin();
        entityManager.persist(novoEstado);
        entityManager.persist(novoAluno);
        entityManager.getTransaction().commit();



        // RESGATAR INSTÂNCIAS DO BANCO DE DADOS

        Estado estadoEncontrado = entityManager.find(Estado.class,2);
        Aluno alunoEncontrado = entityManager.find(Aluno.class,1);

        System.out.println(estadoEncontrado);
        System.out.println(alunoEncontrado);


        // ALTERA UMA ENTIDADE NO BANCO DE DADOS
//        entityManager.getTransaction().begin();
//
//        alunoEncontrado.setIdade(55);
//        alunoEncontrado.setNome("JOSÉ");
//        alunoEncontrado.setEstado(estadoEncontrado);
//        entityManager.getTransaction().commit();

        //Remove uma entidade do banco de dados

//        entityManager.getTransaction().begin();
//        entityManager.remove(alunoEncontrado);
//        entityManager.getTransaction().commit();

        // ---------------SQL NATIVO---------------

        String nome = "Kayke";
        String sql = "SELECT * FROM ALUNO WHERE NOME = :nome";
        List<Aluno> alunoSQL = entityManager
                .createNativeQuery(sql, Aluno.class)
                .setParameter("nome", nome)
                .getResultList();

        System.out.println("-----------------------------------");
        alunoSQL.forEach(System.out::println);

        // ---------------JPQL---------------

        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

        String jpql = "select a from Aluno a where a.nome = :nome";
        List<Aluno> alunosJPQL = entityManager
                .createQuery(jpql, Aluno.class)
                .setParameter("nome", nome)
                .getResultList();
        alunosJPQL.forEach(System.out::println);


        entityManager.close();
        entityManagerFactory.close();


        //-- JPA criteria API + JPA METAMODEL



   }
}
