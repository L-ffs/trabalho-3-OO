package com.sistema.graficos.telas;

import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.sistema.franquia.filial.Filial;
import com.sistema.pessoa.Vendedor;
import com.sistema.graficos.Grafico;

public class TelaInfoVendedoresFilial extends Grafico {

    public static void mostrar(Filial filial) {
        limpar_tela();
        frame.setTitle("Vendedores da Filial: " + filial.getNome());

        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, tela_X, tela_y);

        JLabel titulo = new JLabel("Vendedores da Filial");
        titulo.setBounds(30, 10, 300, 30);
        panel.add(titulo);

        List<Vendedor> vendedores = filial.getVendedores();
        JPanel painelVendedores = new JPanel();
        painelVendedores.setLayout(new BoxLayout(painelVendedores, BoxLayout.Y_AXIS));

        if (vendedores == null || vendedores.isEmpty()) {
            JTextArea info = new JTextArea("Nenhum vendedor cadastrado nesta filial.");
            info.setEditable(false);
            painelVendedores.add(info);
        } else {
            for (Vendedor v : vendedores) {
                JTextArea info = new JTextArea(
                    "Nome: " + v.getNome() +
                    "\nIdade: " + v.getIdade() +
                    "\nCPF: " + v.getCpf() +
                    "\n-----------------------------"
                );
                info.setEditable(false);
                info.setLineWrap(true);
                info.setWrapStyleWord(true);
                painelVendedores.add(info);
            }
        }

        JScrollPane scroll = new JScrollPane(painelVendedores);
        scroll.setBounds(30, 50, tela_X - 80, tela_y - 120);
        panel.add(scroll);

        JButton botaoVoltar = new JButton("← Voltar");
        botaoVoltar.setBounds(10, 10, 100, 30);
        botaoVoltar.addActionListener(e -> TelaInfoFiliais.mostrar());
        panel.add(botaoVoltar);

        frame.add(panel);
        atualizar_tela();
    }
}