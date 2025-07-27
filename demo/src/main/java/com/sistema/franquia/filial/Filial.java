package com.sistema.franquia.filial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.sistema.Produto.Produto;
import com.sistema.contrato.Contrato;
import com.sistema.pessoa.*;


public class Filial {

    private String endereco;
    private Gerente gerente;
    private ArrayList<Vendedor> vendedores= new ArrayList<>();
    private ArrayList<Contrato> contratos= new ArrayList<>();
    private Map<Produto, Integer> estoque = new HashMap<>();

    public Filial(){}; //construtor vazio para o Gson
    public Filial(String endereco, Gerente gerente, ArrayList<Vendedor> vendedores,
    ArrayList<Contrato> contratos, Map<Produto, Integer> estoque)
    {
        this.endereco = endereco;
        this.gerente = gerente;
        this.vendedores = vendedores;
        this.contratos = contratos;
        this.estoque = estoque;
    }


    // Getters e Setters
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public Gerente getGerente() {
        return gerente;
    }
    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }
    public ArrayList<Vendedor> getVendedores() {
        return vendedores;
    }
    public void setVendedores(ArrayList<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }
    public ArrayList<Contrato> getContratos() {
        return contratos;
    }
    public void setContratos(ArrayList<Contrato> contratos) {
        this.contratos = contratos;
    }
    public Map<Produto, Integer> getEstoque() {
        return estoque;
    }
    public void setEstoque(Map<Produto, Integer> estoque) {
        this.estoque = estoque;
    }


    

    
}
