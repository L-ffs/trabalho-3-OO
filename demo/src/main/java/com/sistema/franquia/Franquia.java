package com.sistema.franquia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.sistema.contrato.Pedido_altereçao;
import com.sistema.franquia.filial.Filial;
import com.sistema.graficos.Grafico;
import com.sistema.graficos.User;
import com.sistema.pessoa.*;

public class Franquia {

    private Dono dono;
    private List<Filial> filiais = new ArrayList<>();
    private List<Pessoa> funcionarios = new ArrayList<>(); 
    
    
    public Franquia() {};
    

    
    
    public Dono getDono() {
        return dono;
    }
    public void setDono(Dono dono) {
        this.dono = dono;
    }
    public List<Filial> getFiliais() {
        return filiais;
    }
    public void setFiliais(List<Filial> filiais) {
        this.filiais = filiais;
    }
    public List<Pessoa> getFuncionarios() {
        return funcionarios;
    }
    public void atualiza_funcionarios() {

        funcionarios.clear();

        funcionarios.add(dono);
        for (Filial filial : filiais) {
            funcionarios.add(filial.getGerente());
            for (Vendedor vendedor : filial.getVendedores()) {
                funcionarios.add(vendedor);
            }
        }
    }
    
    public boolean Email_existe(String email) {
        for (Pessoa funcionario : funcionarios) {
            if (funcionario.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public Pessoa getPessoaPorEmail(String email) {
        for (Pessoa funcionario : funcionarios) {
            if (funcionario.getEmail().equals(email)) {
                return funcionario;
            }
        }
        return null; // Retorna null se não encontrar
    }

    public void inicializaEstruturasfiliais() {
        if (getFiliais() == null) return;
        for (Filial filial : getFiliais()) {
            filial.inicializaEstruturas();
        }
    }
    
        




    
}
