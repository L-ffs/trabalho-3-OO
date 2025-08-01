package com.sistema.graficos.telas;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.sistema.contrato.Pedido_altereçao;
import com.sistema.graficos.Grafico;

public class TelaPrincipalGerente extends Grafico {
    


    public static void mostrar() {

        limpar_tela();
        frame.setTitle("Painel do Gerente");

        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, tela_X, tela_y);

        int margemX = Pcent('x', 5);
        int margemY = Pcent('y', 5);
        int larguraColuna = Pcent('x', 40);
        int alturaTotal = Pcent('y', 80);

        // Painel de botões (esquerda)
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));

        JScrollPane scrollBotoes = new JScrollPane(painelBotoes);
        scrollBotoes.setBounds(margemX, margemY, larguraColuna, alturaTotal);
        panel.add(scrollBotoes);

        // Adiciona botões de exemplo
        String[] nomesBotoes = {
            "Produtos da filial", 
            "Gerenciar vendedores", 
            "Ver retificações",
            "Estoque de produtos",
            "Notificações"
        };

        for (String nome : nomesBotoes) {
            JButton botao = new JButton(nome);
            botao.setAlignmentX(Component.CENTER_ALIGNMENT);
            botao.setMaximumSize(new Dimension(larguraColuna - 40, 30));
            botao.setFocusPainted(false);
            botao.setMargin(new Insets(5, 10, 5, 10));
            painelBotoes.add(Box.createVerticalStrut(10)); // Espaçamento
            painelBotoes.add(botao);

            // AÇÕES DOS BOTÕES
            switch (nome) {
                case "Produtos da filial":
                    botao.addActionListener(e -> {
                        // exemplo de ação: chamar tela de listagem
                        System.out.println("Produtos da filial");
                        TelaManipularProdutos.mostrar();
                        // tela_vendas_filial();
                    });
                    break;
                case "Gerenciar vendedores":
                    botao.addActionListener(e -> {
                        System.out.println("Ação: gerenciar vendedores");
                        TelaManipularVendedores.mostrar();
                    });
                    break;
                    case "Ver retificações":
                    botao.addActionListener(e -> {
                        System.out.println("Ação: ver retificações");
                        // tela_retificacoes();
                    });
                    break;
                case "Estoque de produtos":
                botao.addActionListener(e -> {
                    System.out.println("Ação: estoque");
                    // tela_estoque();
                });
                break;
                case "Notificações":
                botao.addActionListener(e -> {
                    System.out.println("Ação: notificações");
                    // tela_notificacoes();
                    });
                    break;
                }
            }
            
            // Painel de mensagens (direita)
            
            JPanel painelMensagens = new JPanel();
            painelMensagens.setLayout(new BoxLayout(painelMensagens, BoxLayout.Y_AXIS));
            
            JScrollPane scrollMensagens = new JScrollPane(painelMensagens);
            scrollMensagens.setBounds(margemX * 2 + larguraColuna, margemY, larguraColuna, alturaTotal);
            panel.add(scrollMensagens);
            
            // Adiciona algumas mensagens de exemplo
            try {
                for (Pedido_altereçao pedido : usuario.getFilial().getPedidos_alteracao()) {
                    JTextArea area = new JTextArea(pedido.getMotivo() +"\nid do pedido: " + pedido.getVenda().getId());
                    area.setLineWrap(true);
                    area.setWrapStyleWord(true);
                    area.setEditable(false);
                    area.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                    painelMensagens.add(area);
                }
            } catch (NullPointerException e) {
                JTextArea area = new JTextArea("Nenhum pedido de alteração encontrado.");
                area.setLineWrap(true);
                area.setWrapStyleWord(true);
                area.setEditable(false);
                area.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                painelMensagens.add(area);
            }
        
        

        frame.add(panel);
        atualizar_tela();
    }
}
