package com.sistema.graficos.telas;

import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import com.sistema.franquia.Franquia;
import com.sistema.franquia.filial.Filial;
import com.sistema.graficos.Grafico;

public class TelaManipularFilial extends Grafico {

    public static void mostrar() {
        limpar_tela();
        frame.setTitle("Manipular Filiais");

        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, tela_X, tela_y);


        // Lista de filiais
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        try {
            for (Filial f : usuario.getFranquia().getFiliais()) {
            modeloLista.addElement(f.getNome() + " - " + f.getEndereco());
            }
        } catch (NullPointerException e) {
        }

        JList<String> listaFiliais = new JList<>(modeloLista);
        listaFiliais.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollLista = new JScrollPane(listaFiliais);
        scrollLista.setBounds(Pcent('x', 5), Pcent('y', 10), Pcent('x', 40), Pcent('y', 80));
        panel.add(scrollLista);

        //Botao de voltar
        JButton botaoVoltar = new JButton("←");
        botaoVoltar.setBounds(10, 10, 50, 30);
        botaoVoltar.addActionListener(e -> {
            TelaPrincipalDono.mostrar();
        });
        panel.add(botaoVoltar);
    
        // Botões ao lado da lista
        JButton botaoExcluir = new JButton("Excluir Filial");
        JButton botaoNova = new JButton("Nova Filial");

        botaoExcluir.setBounds(Pcent('x', 50), Pcent('y', 30), 150, 40);
        botaoNova.setBounds(Pcent('x', 50), Pcent('y', 40), 150, 40);

        panel.add(botaoExcluir);
        panel.add(botaoNova);

        botaoExcluir.addActionListener(e -> {
            int index = listaFiliais.getSelectedIndex();
            if (index >= 0) {
                int confirm = JOptionPane.showConfirmDialog(frame, "Deseja realmente excluir esta filial?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    usuario.getFranquia().getFiliais().remove(index);
                    modeloLista.remove(index);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Selecione uma filial para excluir.");
            }
        });

        botaoNova.addActionListener(e -> {
            Filial novaFilial = new Filial();

            Runnable salvarFilial = () -> usuario.getFranquia().getFiliais().add(novaFilial);
            TelaCriarEditarFilial.CriarFilial(novaFilial, salvarFilial, TelaManipularFilial::mostrar);
        });

        frame.add(panel);
        atualizar_tela();
    }
}