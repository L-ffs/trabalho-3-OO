package com.sistema.pessoa;

public class Pessoa {

    private String nome;
    private String cpf;
    private int idade;
    private String email;
    private String senha;

    
    public Pessoa(String nome, String cpf, int idade, String email, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.email = email;
        this.senha = senha;
    }

}
