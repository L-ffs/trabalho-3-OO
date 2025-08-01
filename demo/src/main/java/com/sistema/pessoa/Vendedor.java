package com.sistema.pessoa;

import com.sistema.graficos.telas.TelaPrincipalVendedor;

public class Vendedor extends Pessoa {

    public Vendedor(){}; //construtor vazio para Gson
    public Vendedor(String nome, String cpf, int idade, String email, String senha) {
        super(nome, cpf, idade, email, senha);
    }
    

    @Override
    public void MostrarTelaInicial() {
        TelaPrincipalVendedor.mostrar();
    }
}
