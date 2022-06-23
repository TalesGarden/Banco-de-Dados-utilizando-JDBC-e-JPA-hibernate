package com.digitalinovation;

import com.mysql.cj.jdbc.ha.BalanceStrategy;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Aluno {

    public Aluno() {
    }

    public Aluno(String nome, int idade, Estado estado) {
        this.nome = nome;
        this.idade = idade;
        this.estado = estado;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // O BANCO DE DADOS FICA RESPONSÁVEL POR GERAR O ID
    private int id;

    @Column
    private String nome;

    @Column
    private int idade;

    @ManyToOne(fetch = FetchType.EAGER)
    private Estado estado;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public int getIdade() {
        return idade;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return id == aluno.id && idade == aluno.idade && Objects.equals(nome, aluno.nome) && Objects.equals(estado, aluno.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, idade, estado);
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", estado=" + estado +
                '}';
    }
}
