package com.sistema.graficos.telas;

import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import com.sistema.Produto.Produto;
import com.sistema.graficos.Grafico;


public class TelaManipularProdutos extends Grafico {
    

    public static void mostrar() {

        limpar_tela();
        frame.setTitle("Manipular Produtos");

        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, tela_X, tela_y);

        // Lista de produtos (String) para exibição
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        Map<String, Integer> estoque = usuario.getFilial().getEstoque() == null ? Map.of() : usuario.getFilial().getEstoque();
        for (String nomeProduto : estoque.keySet()) {
            modeloLista.addElement(nomeProduto + " (Qtd: " + estoque.get(nomeProduto) + ")");
        }

        JList<String> listaProdutos = new JList<>(modeloLista);
        listaProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollLista = new JScrollPane(listaProdutos);
        scrollLista.setBounds(Pcent('x', 5), Pcent('y', 10), Pcent('x', 40), Pcent('y', 80));
        panel.add(scrollLista);

        // Botões
        JButton botaoExcluir = new JButton("Excluir Produto");
        JButton botaoEditar = new JButton("Editar Produto");
        JButton botaoNovo = new JButton("Novo Produto");


        botaoExcluir.setBounds(Pcent('x', 50), Pcent('y', 30), 120, 40);
        botaoEditar.setBounds(Pcent('x', 50), Pcent('y', 40), 120, 40);
        botaoNovo.setBounds(Pcent('x', 50), Pcent('y', 50), 120, 40);

        panel.add(botaoExcluir);
        panel.add(botaoEditar);
        panel.add(botaoNovo);

        botaoExcluir.addActionListener(e -> {
            int index = listaProdutos.getSelectedIndex();
            if (index >= 0) { // -1 se nao escolhido
                String selecionado = (String) estoque.keySet().toArray()[index];
                Produto produto= usuario.getFilial().ProdutoPorNome(selecionado);
                estoque.remove(selecionado);
                usuario.getFilial().getProdutos().remove(produto);
                modeloLista.remove(index);
            } else {
                JOptionPane.showMessageDialog(frame, "Selecione um produto para excluir.");
            }
        });

        botaoEditar.addActionListener(e -> {
            int index = listaProdutos.getSelectedIndex();
            if (index >= 0) {
                String nomeSelecionado= (String) estoque.keySet().toArray()[index];
                Produto selecionado = usuario.getFilial().ProdutoPorNome(nomeSelecionado);
                // Aqui você pode chamar outra função para abrir a tela de edição
                TelaCriarEditarProduto.mostrar(selecionado);
            } else {
                JOptionPane.showMessageDialog(frame, "Selecione um produto para editar.");
            }
        });

        botaoNovo.addActionListener(e -> {
            // Chame aqui uma função para criar novo produto
            TelaCriarEditarProduto.mostrar(null);
        });

        frame.add(panel);
        atualizar_tela();
    }
}
