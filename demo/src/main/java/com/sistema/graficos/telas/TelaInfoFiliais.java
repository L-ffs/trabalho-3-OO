package com.sistema.graficos.telas;

import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JOptionPane;

import com.sistema.contrato.Contrato;
import com.sistema.franquia.filial.Filial;
import com.sistema.graficos.Grafico;

public class TelaInfoFiliais extends Grafico {

    public static void mostrar() {
        limpar_tela();
        frame.setTitle("Informações das Filiais");

        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, tela_X, tela_y);

        JLabel titulo = new JLabel("Lista de Filiais");
        titulo.setBounds(30, 10, 300, 30);
        panel.add(titulo);

        List<Filial> filiais = usuario.getFranquia().getFiliais();
        DefaultListModel<String> modeloLista = new DefaultListModel<>();

        if (filiais == null || filiais.isEmpty()) {
            modeloLista.addElement("Nenhuma filial cadastrada.");
        } else {

            for (Filial f : filiais) {
                double valor_vendas = 0;
                try {
                    for (Contrato c : f.getContratos()) {
                        valor_vendas += c.getPreco();
                    }
                } catch (NullPointerException e) {}
                String gerenteNome = (f.getGerente() != null) ? f.getGerente().getNome() : "Sem gerente";
                modeloLista.addElement(
                    "Nome: " + f.getNome() +
                    " | Endereço: " + f.getEndereco() +
                    " | Gerente: " + gerenteNome +
                    " | Valor em vendas: " + valor_vendas
                );
            }
            
        }

        JList<String> listaFiliais = new JList<>(modeloLista);
        listaFiliais.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(listaFiliais);
        scroll.setBounds(30, 50, tela_X - 80, tela_y - 170);
        panel.add(scroll);

        JButton botaoVendedores = new JButton("Ver Vendedores da Filial");
        botaoVendedores.setBounds(30, tela_y - 100, 220, 35);
        botaoVendedores.addActionListener(e -> {
            int index = listaFiliais.getSelectedIndex();
            if (filiais == null || filiais.isEmpty() || index < 0) {
                JOptionPane.showMessageDialog(frame, "Selecione uma filial.");
                return;
            }
            Filial filialSelecionada = filiais.get(index);
            TelaInfoVendedoresFilial.mostrar(filialSelecionada);
        });
        panel.add(botaoVendedores);

        JButton botaoVoltar = new JButton("← Voltar");
        botaoVoltar.setBounds(10, 10, 100, 30);
        botaoVoltar.addActionListener(e -> TelaPrincipalDono.mostrar());
        panel.add(botaoVoltar);

        frame.add(panel);
        atualizar_tela();
    }
}