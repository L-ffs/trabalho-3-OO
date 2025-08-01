package com.sistema.pessoa;

import com.sistema.franquia.filial.Filial;
import com.sistema.graficos.Grafico;
import com.sistema.graficos.User;

public class Dono extends Pessoa {
    
    public Dono(){}; //construtor vazio para Gson
    public Dono(String nome, String cpf, int idade, String email, String senha) {
        super(nome, cpf, idade, email, senha);
    }

    @Override
    public void notificar(User usuario) {
        
        for (Filial filial : usuario.getFranquia().getFiliais()) {
            if (filial.getGerente() == null) {
                Grafico.mostrarNotificacao("a filial " + filial.getNome() + "nao possui um gerente.");
            }
        }  
    }

    @Override
    public void MostrarTelaInicial() {
        //TelaPrincipalDono.mostrar();
    }


}
