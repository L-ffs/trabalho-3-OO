package com.sistema.pessoa;

public class Gerente extends Pessoa {

    public Gerente(){}; //construtor vazio para Gson
    public Gerente(String nome, String cpf, int idade, String email, String senha) {
        super(nome, cpf, idade, email, senha);
    }
    
}
