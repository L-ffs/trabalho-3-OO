package com.sistema.Produto;

import java.util.Objects;

//202465558C Wesley Santos de Lima
//202465505B Luiz Fernando Ferreira Silva

public class Produto {
    
    private String nome;
    private double preco;


    public Produto(){}; //construtor vazio para Gson
    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }



    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Produto produto = (Produto) obj;
        return Double.compare(produto.preco, preco) == 0 && nome.equals(produto.nome);
    }
    @Override
    public int hashCode() {
        return Objects.hash(nome, preco);
    }
}
