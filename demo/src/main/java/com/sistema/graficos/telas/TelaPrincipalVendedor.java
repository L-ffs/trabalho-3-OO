package com.sistema.graficos.telas;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sistema.graficos.Grafico;

public class TelaPrincipalVendedor extends Grafico {
    


    public static void mostrar() {

        limpar_tela();
        frame.setTitle("Painel do Vendedor");

        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, tela_X, tela_y);

        JLabel titulo = new JLabel("Bem-vindo, " + usuario.getPessoa().getNome()); 
        titulo.setBounds(Pcent('x', 40), Pcent('y', 10), 400, 30);
        panel.add(titulo);

        JButton btnRegistrarVenda = new JButton("Registrar Venda");
        JButton btnHistoricoVendas = new JButton("Histórico de Vendas");
        JButton btnSolicitarRetificacao = new JButton("Solicitar Retificação");

        
        btnRegistrarVenda.setBounds(Pcent('x', 40), Pcent('y', 25), 200, 30);
        btnHistoricoVendas.setBounds(Pcent('x', 40), Pcent('y', 35), 200, 30);
        btnSolicitarRetificacao.setBounds(Pcent('x', 40), Pcent('y', 45), 200, 30);

        panel.add(btnRegistrarVenda);
        panel.add(btnHistoricoVendas);
        panel.add(btnSolicitarRetificacao);

        frame.add(panel);
        atualizar_tela();

        // Aqui você adiciona o comportamento dos botões
        btnRegistrarVenda.addActionListener(e -> {
            // chamar tela de registrar venda
            TelaRegistrarVenda.mostrar();
        });

        btnHistoricoVendas.addActionListener(e -> {
            // chamar tela de histórico de vendas
            TelaHistoricoVendas.mostrar();
        });

        btnSolicitarRetificacao.addActionListener(e -> {
            // chamar tela de solicitação de retificação
            TelaSolicitarRetificaçao.mostrar();
        });
    }
}
