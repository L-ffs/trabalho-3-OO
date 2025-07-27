package com.sistema.pessoa;

public class Pessoa {

    private String nome;
    private String cpf;
    private int idade;
    private String email;
    private String senha;

    public Pessoa(){}; //construtor vazio para Gson
    public Pessoa(String nome, String cpf, int idade, String email, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.email = email;
        this.senha = senha;
    }


    public void setNome(String nome) {this.nome = nome;}
    public void setCpf(String cpf) {this.cpf = cpf;}
    public void setIdade(int idade) {this.idade = idade;}
    public void setEmail(String email) {this.email = email;}
    public void setSenha(String senha) {this.senha = senha;}

    public void indentificador_pessoa() {
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Idade: " + idade);
        System.out.println("Email: " + email);
        System.out.println("Senha: " + senha);
    }
}
