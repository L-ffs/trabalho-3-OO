package com.sistema.contrato;

public class Contrato {
    String comprador;
    String vendedor;
    String produto;
    double preco;

    public Contrato() {} // Contrato vazio para o Gson
    public Contrato(String comprador, String vendedor, String produto, double preco) {
        this.comprador = comprador;
        this.vendedor = vendedor;
        this.produto = produto;
        this.preco = preco;
    }




    // Getters and Setters
    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
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
}
