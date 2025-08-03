package com.sistema.graficos.telas;

import java.util.List;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import com.sistema.contrato.Contrato;
import com.sistema.graficos.Grafico;

public class TelaEdiçaoPedidos extends Grafico {

    public static void mostrar() {
        limpar_tela();
        frame.setTitle("Histórico de Vendas (Gerente)");

        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, tela_X, tela_y);

        // Lista de vendas
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        List<Contrato> vendas = new ArrayList<>();
        try {
            for (Contrato c : usuario.getFilial().getContratos()) {
                modeloLista.addElement("ID: " + c.getId() + " | Comprador: " + c.getComprador() +
                        " | Produto: " + c.getProduto() + " | Preço: R$" + c.getPreco());
                vendas.add(c);
            }
        } catch (NullPointerException e) {
        }

        JList<String> listaVendas = new JList<>(modeloLista);
        listaVendas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollLista = new JScrollPane(listaVendas);
        scrollLista.setBounds(Pcent('x', 10), Pcent('y', 15), Pcent('x', 60), Pcent('y', 60));
        panel.add(scrollLista);

        // Botões
        JButton botaoExcluir = new JButton("Excluir Venda");
        JButton botaoEditar = new JButton("Editar Venda");
        botaoExcluir.setBounds(Pcent('x', 75), Pcent('y', 30), 150, 40);
        botaoEditar.setBounds(Pcent('x', 75), Pcent('y', 40), 150, 40);

        panel.add(botaoExcluir);
        panel.add(botaoEditar);

        // Botão voltar
        JButton botaoVoltar = new JButton("← Voltar");
        botaoVoltar.setBounds(10, 10, 100, 30);
        botaoVoltar.addActionListener(e -> usuario.getPessoa().MostrarTelaInicial());
        panel.add(botaoVoltar);

        // Ações dos botões
        botaoExcluir.addActionListener(e -> {
            int index = listaVendas.getSelectedIndex();
            if (index >= 0) {
                Contrato selecionado = vendas.get(index);
                usuario.getFilial().getContratos().remove(selecionado);
                modeloLista.remove(index);
                JOptionPane.showMessageDialog(frame, "Venda excluída com sucesso.");
            } else {
                JOptionPane.showMessageDialog(frame, "Selecione uma venda para excluir.");
            }
        });

        botaoEditar.addActionListener(e -> {
            int index = listaVendas.getSelectedIndex();
            if (index >= 0) {
                Contrato selecionado = vendas.get(index);
                // Aqui você pode chamar uma tela de edição de venda
                TelaEditarVenda.mostrar(selecionado);
                JOptionPane.showMessageDialog(frame, "Função de edição não implementada.");
            } else {
                JOptionPane.showMessageDialog(frame, "Selecione uma venda para editar.");
            }
        });

        frame.add(panel);
        atualizar_tela();
    }
}