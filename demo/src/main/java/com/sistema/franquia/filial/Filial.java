package com.sistema.franquia.filial;
import java.util.ArrayList;
import com.sistema.pessoa.*;


public class Filial {
    private String endereco;
    private Gerente gerente;
    private ArrayList<Vendedor> vendedores= new ArrayList<>();


    public Filial(String endereco, Gerente gerente, Vendedor vendedor) {
        
        this.endereco = endereco;
        this.gerente = gerente;
        this.vendedores.add(vendedor);
    }

    public Filial(){}; //filial vazia para o Gson

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }

    public void setVendedores(ArrayList<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

    
}
