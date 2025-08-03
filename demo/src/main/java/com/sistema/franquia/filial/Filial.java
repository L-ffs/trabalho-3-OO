package com.sistema.franquia.filial;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.sistema.Produto.Produto;
import com.sistema.contrato.Contrato;
import com.sistema.contrato.Pedido_altereçao;
import com.sistema.pessoa.*;


public class Filial {

    private String nome;
    private String endereco;
    private Gerente gerente;
    private List<Vendedor> vendedores= new ArrayList<>();
    private List<Contrato> contratos= new ArrayList<>();

    private Map<String, Integer> estoque = new HashMap<>();
    private List<Produto> produtos= new ArrayList<>();

    private List<Pedido_altereçao> pedidos_alteracao = new ArrayList<>();

    

    public Filial(){}; //construtor vazio para o Gson
    public Filial(String endereco, Gerente gerente, ArrayList<Vendedor> vendedores,
    ArrayList<Contrato> contratos, Map<String, Integer> estoque)
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
    public List<Vendedor> getVendedores() {
        return vendedores;
    }
    public void setVendedores(List<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }
    public List<Contrato> getContratos() {
        return contratos;
    }
    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }
    public Map<String, Integer> getEstoque() {
        return estoque;
    }
    public void setEstoque(Map<String, Integer> estoque) {
        this.estoque = estoque;
    }
    public List<Produto> getProdutos() {
        return produtos;
    }
    public void setProduto(List<Produto> produtos) {
        this.produtos= produtos;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public List<Pedido_altereçao> getPedidos_alteracao() {
        return pedidos_alteracao;
    }
    public void setPedidos_alteracao(List<Pedido_altereçao> pedidos_alteracao) {
        this.pedidos_alteracao = pedidos_alteracao;
    }

    public void inicializaEstruturas() {
        estoque= estoque== null ? new HashMap<>() : estoque;
        pedidos_alteracao= pedidos_alteracao == null ? new ArrayList<>() : pedidos_alteracao;
        contratos= contratos == null ? new ArrayList<>() : contratos;
        vendedores= vendedores == null ? new ArrayList<>() : vendedores;
    }

    public List<String> GetInfoCompradores() {
       
        class infoComprador {
            public String nome;
            public int numeroCompras;
            public double valorGastoTotal;
            public double gastoMedio;
            @Override
            public String toString() {
                return "Nome: " + nome + "\nCompras: " + numeroCompras + 
                       "\nTotal gasto: R$" + valorGastoTotal + 
                       "\nGasto médio: R$" + gastoMedio;
            }
        }
        List<infoComprador> compradores = new ArrayList<>();
        List<String> cardInfoCompradores = new ArrayList<>();
        try {
            for (Contrato contrato : contratos) {
                
                if (compradores.contains(contrato.getComprador())) {
                    // Se o comprador já existe, atualiza as informações
                    infoComprador comprador = compradores.get(compradores.indexOf(contrato.getComprador()));
                    comprador.numeroCompras++;
                    comprador.valorGastoTotal += contrato.getPreco();
                    comprador.gastoMedio = comprador.valorGastoTotal / comprador.numeroCompras;
                } else {
                    // Se não existe, cria um novo registro
                    infoComprador novoComprador = new infoComprador();
                    novoComprador.nome = contrato.getComprador();
                    novoComprador.numeroCompras = 1;
                    novoComprador.valorGastoTotal = contrato.getPreco();
                    novoComprador.gastoMedio = contrato.getPreco();
                    compradores.add(novoComprador);
                }
            }
            compradores.sort((c1, c2) -> Integer.compare(c2.numeroCompras, c1.numeroCompras));
            for (infoComprador comprador : compradores) {
                cardInfoCompradores.add(comprador.toString());
            }
            if (compradores.isEmpty()) cardInfoCompradores.add("Nenhum comprador encontrado.");
            
        } catch (NullPointerException e) {
            cardInfoCompradores.add("Nenhum comprador encontrado.");
        }

        return cardInfoCompradores;
    }
    
    public Produto ProdutoPorNome(String nome) {

        try {
            for (Produto produto : produtos) {
                if (produto.getNome().equals(nome)){
                    return produto;
                }
            }
        } catch (Exception e) {
            
        }
        return null;
    }

}
