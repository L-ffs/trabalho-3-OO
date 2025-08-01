package com.sistema.graficos.telas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import com.sistema.graficos.Grafico;
import com.sistema.pessoa.Vendedor;

public class TelaManipularVendedores extends Grafico {
    
    
    public static void mostrar() {

        limpar_tela();
    frame.setTitle("Manipular Vendedores");

    JPanel panel = new JPanel(null);
    panel.setBounds(0, 0, tela_X, tela_y);

    // Lista de vendedores
    DefaultListModel<String> modeloLista = new DefaultListModel<>();
    List<Vendedor> vendedores = usuario.getFilial().getVendedores() == null ?
     new ArrayList<Vendedor>() : usuario.getFilial().getVendedores(); // Supondo que você tenha uma lista em algum lugar
    
    
    for (Vendedor v : vendedores) {
        modeloLista.addElement(v.getNome() + " (CPF: " + v.getCpf() + ")");
    }

    JList<String> listaVendedores = new JList<>(modeloLista);
    listaVendedores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    JScrollPane scrollLista = new JScrollPane(listaVendedores);
    scrollLista.setBounds(Pcent('x', 5), Pcent('y', 10), Pcent('x', 40), Pcent('y', 80));
    panel.add(scrollLista);

    // Botões
    JButton botaoExcluir = new JButton("Excluir Vendedor");
    JButton botaoEditar = new JButton("Editar Vendedor");
    JButton botaoNovo = new JButton("Novo Vendedor");

    botaoExcluir.setBounds(Pcent('x', 50), Pcent('y', 30), 150, 40);
    botaoEditar.setBounds(Pcent('x', 50), Pcent('y', 40), 150, 40);
    botaoNovo.setBounds(Pcent('x', 50), Pcent('y', 50), 150, 40);

    panel.add(botaoExcluir);
    panel.add(botaoEditar);
    panel.add(botaoNovo);

    // Ações
    botaoExcluir.addActionListener(e -> {
        int index = listaVendedores.getSelectedIndex();
        if (index >= 0) {
            vendedores.remove(index);
            modeloLista.remove(index);
        } else {
            JOptionPane.showMessageDialog(frame, "Selecione um vendedor para excluir.");
        }
    });

    botaoEditar.addActionListener(e -> {
        int index = listaVendedores.getSelectedIndex();
        if (index >= 0) {
            Vendedor selecionado = vendedores.get(index);
            TelaCriarEditarPessoa.CriarPessoa(selecionado, TelaManipularVendedores::mostrar);
        } else {
            JOptionPane.showMessageDialog(frame, "Selecione um vendedor para editar.");
        }
    });

    botaoNovo.addActionListener(e -> {
        Vendedor vendedor = new Vendedor();
        TelaCriarEditarPessoa.CriarPessoa(vendedor, TelaManipularVendedores::mostrar);
        usuario.getFilial().getVendedores().add(vendedor);
    });

    frame.add(panel);
    atualizar_tela();
    }
}
