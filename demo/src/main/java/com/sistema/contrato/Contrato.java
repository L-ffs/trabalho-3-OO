package com.sistema.contrato;

import com.sistema.pessoa.Vendedor;

public class Contrato {
    private static int idCounter = 0;  //contador estatico para ids unicos
    private int id;  //id unico do contrato
    private String comprador;
    private Vendedor vendedor;
    private String produto;
    private double preco;

    public Contrato() {} // Contrato vazio para o Gson
    public Contrato(String comprador, Vendedor vendedor, String produto, double preco) {
        this.id = ++idCounter;  //incrementa o contador e atribui um novo id
        this.comprador = comprador;
        this.vendedor = vendedor;
        this.produto = produto;
        this.preco = preco;
    }


    public static int getIdCounter() {
        return idCounter;
    }
    public static void setIdCounter(int idCounter) {
        Contrato.idCounter = idCounter;
    }
    @Override
    public String toString() {
        return "id: " + id + ", comprador: " + comprador + ", produto: " + produto
         + ", preco: " + preco;
    }
    // Getters and Setters
    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor= vendedor;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
