package com.sistema.graficos.telas;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sistema.contrato.Contrato;
import com.sistema.graficos.Grafico;

public class TelaEditarVenda extends Grafico {

    public static void mostrar(Contrato venda) {
        if (venda == null) {
            JOptionPane.showMessageDialog(frame, "Venda inválida.");
            return;
        }

        limpar_tela();
        frame.setTitle("Editar Venda");

        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, tela_X, tela_y);

        // Campo comprador
        JLabel labelComprador = new JLabel("Nome do Comprador:");
        JTextField campoComprador = new JTextField(venda.getComprador());
        labelComprador.setBounds(Pcent('x', 35), Pcent('y', 25), 150, 25);
        campoComprador.setBounds(Pcent('x', 50), Pcent('y', 25), 200, 25);

        // Preço
        JLabel labelPreco = new JLabel("Preço:");
        JTextField campoPreco = new JTextField(String.valueOf(venda.getPreco()));
        labelPreco.setBounds(Pcent('x', 35), Pcent('y', 39), 100, 25);
        campoPreco.setBounds(Pcent('x', 50), Pcent('y', 39), 200, 25);

        // Botão confirmar edição
        JButton botaoConfirmar = new JButton("Confirmar Edição");
        botaoConfirmar.setBounds(Pcent('x', 45), Pcent('y', 50), 150, 30);

        // Botão voltar
        JButton botaoVoltar = new JButton("←");
        botaoVoltar.setBounds(10, 10, 50, 30);
        botaoVoltar.addActionListener(e -> usuario.getPessoa().MostrarTelaInicial());

        botaoConfirmar.addActionListener(e -> {
            String comprador = campoComprador.getText().trim();
            String precoStr = campoPreco.getText().trim();

            if (comprador.isEmpty() || precoStr.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Preencha todos os campos corretamente.");
                return;
            }

            double preco;
            try {
                preco = Double.parseDouble(precoStr);
                if (preco <= 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Preço inválido.");
                return;
            }

            venda.setComprador(comprador);
            venda.setPreco(preco);

            JOptionPane.showMessageDialog(frame, "Venda editada com sucesso!");
            usuario.getPessoa().MostrarTelaInicial();
        });

        panel.add(labelComprador);
        panel.add(campoComprador);
        panel.add(labelPreco);
        panel.add(campoPreco);
        panel.add(botaoConfirmar);
        panel.add(botaoVoltar);
        frame.add(panel);
        atualizar_tela();
    }
}