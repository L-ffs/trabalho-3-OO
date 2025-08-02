package com.sistema.graficos.telas;

import java.awt.List;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.sistema.graficos.Grafico;

public class TelaVisualisarCompradores extends Grafico {

    public static void mostrar() {
        limpar_tela();
        frame.setTitle("Visualizar Compradores");

        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, tela_X, tela_y);

        JPanel areaCompradores = new JPanel();
        areaCompradores.setLayout(new BoxLayout(areaCompradores, BoxLayout.Y_AXIS));

        JScrollPane scroll = new JScrollPane(areaCompradores);
        scroll.setBounds(120, Pcent('y', 15), Pcent('x', 60), Pcent('y', 60));
        panel.add(scroll);

        
        for (String comprador : usuario.getFilial().GetInfoCompradores()) {
            JTextArea texto= new JTextArea();
            texto.setText(comprador);
            texto.setEditable(false);
            texto.setLineWrap(true);
            texto.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            areaCompradores.add(texto);
        }
        if (areaCompradores.getComponentCount() == 0) {
            JTextArea texto= new JTextArea("Nenhum comprador encontrado.");
            texto.setEditable(false);
            texto.setLineWrap(true);
            texto.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            areaCompradores.add(texto);
        }
        
        
        JLabel label = new JLabel("Lista de Compradores:");
        label.setBounds(Pcent('x', 20), Pcent('y', 10), 200, 30);
        panel.add(label);

        JButton voltarButton = new JButton("← Voltar");
        voltarButton.setBounds(Pcent('x', 80), Pcent('y', 80), 120, 35);
        voltarButton.addActionListener(e -> {
            TelaPrincipalGerente.mostrar();
        });
        panel.add(voltarButton);

        frame.add(panel);
        atualizar_tela();
    }
    
}
