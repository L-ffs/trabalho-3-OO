package com.sistema.graficos.telas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.sistema.graficos.Grafico;
import com.sistema.pessoa.Vendedor;
import com.sistema.contrato.Contrato;

public class TelaInfoVendedores extends Grafico {

    public static void mostrar() {
        limpar_tela();
        frame.setTitle("Ranking de Vendedores por Valor Vendido");

        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, tela_X, tela_y);

        
        Map<Vendedor, Double> totalVendido = new HashMap<>();
        
        try {
            List<Contrato> contratos = usuario.getFilial().getContratos();
            for (Contrato c : contratos) {
                Vendedor v = c.getVendedor();
                totalVendido.put(v, totalVendido.getOrDefault(v, 0.0) + c.getPreco());
            }
        } catch (NullPointerException e) {
            List<Contrato> contratos= new ArrayList<>();
        }

        // Ordena vendedores por valor vendido
        List<Map.Entry<Vendedor, Double>> ranking = new ArrayList<>(totalVendido.entrySet());
        ranking.sort(Map.Entry.<Vendedor, Double>comparingByValue().reversed());

        
        JPanel areaVendedores = new JPanel();
        areaVendedores.setLayout(new BoxLayout(areaVendedores, BoxLayout.Y_AXIS));

        int pos = 1;
        for (Map.Entry<Vendedor, Double> entry : ranking) {
            Vendedor v = entry.getKey();
            double valor = entry.getValue();
            JTextArea texto = new JTextArea(pos + "º - " + v.getNome() + " | CPF: " + v.getCpf() + " | Total vendido: R$" + String.format("%.2f", valor));
            texto.setEditable(false);
            texto.setLineWrap(true);
            areaVendedores.add(texto);
            pos++;
        }
        if (ranking.isEmpty()) {
            JTextArea texto = new JTextArea("Nenhuma venda registrada.");
            texto.setEditable(false);
            areaVendedores.add(texto);
        }

        JScrollPane scroll = new JScrollPane(areaVendedores);
        scroll.setBounds(Pcent('x', 10), Pcent('y', 15), Pcent('x', 80), Pcent('y', 60));
        panel.add(scroll);

        JLabel label = new JLabel("Ranking de Vendedores:");
        label.setBounds(Pcent('x', 10), Pcent('y', 5), 300, 30);
        panel.add(label);

        JButton botaoVoltar = new JButton("← Voltar");
        botaoVoltar.setBounds(Pcent('x', 80), Pcent('y', 80), 120, 35);
        botaoVoltar.addActionListener(e -> TelaPrincipalDono.mostrar());
        panel.add(botaoVoltar);

        frame.add(panel);
        atualizar_tela();
    }
}