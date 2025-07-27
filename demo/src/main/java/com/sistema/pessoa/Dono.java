package com.sistema.pessoa;

public class Dono extends Pessoa {
    
    public Dono(){}; //construtor vazio para Gson
    public Dono(String nome, String cpf, int idade, String email, String senha) {
        super(nome, cpf, idade, email, senha);
    }
}
