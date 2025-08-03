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

import com.sistema.graficos.Grafico;

public class TelaPrincipalDono extends Grafico {

    public static void mostrar() {
        limpar_tela();
        frame.setTitle("Painel do Dono");

        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, tela_X, tela_y);

        int margemX = Pcent('x', 5);
        int margemY = Pcent('y', 5);
        int larguraColuna = Pcent('x', 40);
        int alturaTotal = Pcent('y', 80);

        // Botão de voltar
        JButton botaoVoltar = new JButton("← Voltar");
        botaoVoltar.setBounds(10, 10, 100, 30);
        botaoVoltar.addActionListener(e -> {
            System.out.println("Voltando para a tela anterior");
            TelaLoginPrincipal.mostrar(); 
        });
        panel.add(botaoVoltar);

        // Painel de botões (esquerda)
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));

        JScrollPane scrollBotoes = new JScrollPane(painelBotoes);
        scrollBotoes.setBounds(margemX, margemY + 40, larguraColuna, alturaTotal);
        panel.add(scrollBotoes);

        // Botões do dono
        String[] nomesBotoes = {
            "Cadastrar nova filial", 
            "Gerenciar gerentes", 
            "Visualizar relatórios",
            "Ver estatísticas globais",
            "Configurações do sistema"
        };

        for (String nome : nomesBotoes) {
            JButton botao = new JButton(nome);
            botao.setAlignmentX(Component.CENTER_ALIGNMENT);
            botao.setMaximumSize(new Dimension(larguraColuna - 40, 30));
            botao.setFocusPainted(false);
            botao.setMargin(new Insets(5, 10, 5, 10));
            painelBotoes.add(Box.createVerticalStrut(10));
            painelBotoes.add(botao);

            botao.addActionListener(e -> {
                switch (nome) {
                    case "Cadastrar nova filial":
                        System.out.println("Ação: Cadastrar nova filial");
                        // TelaCadastroFilial.mostrar(); // exemplo
                        break;
                    case "Gerenciar gerentes":
                        System.out.println("Ação: Gerenciar gerentes");
                        // TelaGerenciarGerentes.mostrar();
                        break;
                    case "Visualizar relatórios":
                        System.out.println("Ação: Visualizar relatórios");
                        // TelaRelatorios.mostrar();
                        break;
                    case "Ver estatísticas globais":
                        System.out.println("Ação: Ver estatísticas globais");
                        // TelaEstatisticasGlobais.mostrar();
                        break;
                    case "Configurações do sistema":
                        System.out.println("Ação: Configurações do sistema");
                        // TelaConfiguracoes.mostrar();
                        break;
                    default:
                        System.out.println("Ação não definida: " + nome);
                }
            });
        }

        // Painel lateral (direita)
        JPanel painelLateral = new JPanel();
        painelLateral.setLayout(new BoxLayout(painelLateral, BoxLayout.Y_AXIS));

        JScrollPane scrollLateral = new JScrollPane(painelLateral);
        scrollLateral.setBounds(margemX * 2 + larguraColuna, margemY + 40, larguraColuna, alturaTotal);
        panel.add(scrollLateral);

        // Conteúdo exemplo
        
        //FAZER UM LOOP COM TODAS FILIAIS E VERIFICAR SE GETENTE == NULL OU .VAZIO()
        JTextArea info = new JTextArea("Mensagens, notificações ou gráficos poderão ser exibidos aqui.");
        info.setLineWrap(true);
        info.setWrapStyleWord(true);
        info.setEditable(false);
        info.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painelLateral.add(info);

        frame.add(panel);
        atualizar_tela();
    }
}