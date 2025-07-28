package com.sistema.graficos;

import com.sistema.franquia.Franquia;
import com.sistema.franquia.filial.Filial;
import com.sistema.pessoa.Pessoa;
import com.sistema.pessoa.Vendedor;

public class User {

    private Pessoa pessoa;
    private Filial filial;
    private Franquia franquia;



    public User(){}
    public User(Pessoa pessoa, Filial filial, Franquia franquia) {
        this.pessoa = pessoa;
        this.filial = filial;
        this.franquia = franquia;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    public Filial getFilial() {
        return filial;
    }
    public void setFilial(Filial filial) {
        this.filial = filial;
    }
    public Franquia getFranquia() {
        return franquia;
    }
    public void setFranquia(Franquia franquia) {
        this.franquia = franquia;
    }
    

    public boolean verificaEmail(String email) {
        
        if(this.pessoa == null) {return false;}
        return pessoa.getEmail().equals(email);
    }
    public boolean verificaSenha(String senha) {
        if(this.pessoa == null) {return false;}
        return pessoa.getSenha().equals(senha);
    }

    public void achar_filial() {
        
        if (this.pessoa == getFranquia().getDono()) {
            filial= null; // O dono não tem uma filial específica
        }
        for (Filial filial : franquia.getFiliais()) {
            if (filial.getGerente() == this.pessoa) {
                this.filial = filial;
                return;
            }
            for (Vendedor vendedor : filial.getVendedores()) {
                if (vendedor == this.pessoa) {
                    this.filial = filial;
                    return;
                }
            }
        }
    }
}