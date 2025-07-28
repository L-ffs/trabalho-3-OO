package com.sistema.franquia;

import java.util.ArrayList;
import com.sistema.franquia.filial.Filial;
import com.sistema.pessoa.*;

public class Franquia {

    private Dono dono;
    private ArrayList<Filial> filiais = new ArrayList<>();
    private ArrayList<Pessoa> funcionarios = new ArrayList<>(); 
    
    
    public Franquia() {};
    

    
    
    public Dono getDono() {
        return dono;
    }
    public void setDono(Dono dono) {
        this.dono = dono;
    }
    public ArrayList<Filial> getFiliais() {
        return filiais;
    }
    public void setFiliais(ArrayList<Filial> filiais) {
        this.filiais = filiais;
    }
    public ArrayList<Pessoa> getFuncionarios() {
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

    
}
