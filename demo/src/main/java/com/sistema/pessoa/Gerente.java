package com.sistema.pessoa;

import com.sistema.contrato.Pedido_altereçao;
import com.sistema.graficos.Grafico;
import com.sistema.graficos.User;

public class Gerente extends Pessoa {

    public Gerente(){}; //construtor vazio para Gson
    public Gerente(String nome, String cpf, int idade, String email, String senha) {
        super(nome, cpf, idade, email, senha);
    }
    


    @Override
    public void notificar(User usuario) {
        
        try {
            boolean pedidosAlteracaoExistem = !usuario.getFilial().getPedidos_alteracao().isEmpty();
            if (pedidosAlteracaoExistem) {
                Grafico.mostrarNotificacao("A filial possui pedidos de alteração.");
                return;
            } 
        } catch (NullPointerException e) {

        }
            
    }
}
