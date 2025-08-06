package com.sistema.graficos.telas;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import com.sistema.franquia.filial.Filial;
import com.sistema.pessoa.Gerente;
import com.sistema.graficos.Grafico;

public class TelaGerenciarGerentes extends Grafico {

    public static void mostrar() {
        limpar_tela();
        frame.setTitle("Gerenciar Gerentes das Filiais");

        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, tela_X, tela_y);

        // Lista de filiais
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        for (Filial f : usuario.getFranquia().getFiliais()) {
            String texto_filial= f.getNome() + " - " + f.getEndereco();
            texto_filial= f.getGerente() == null ? texto_filial + "\nsem gerente" : texto_filial;
            modeloLista.addElement(texto_filial);
        }

        JList<String> listaFiliais = new JList<>(modeloLista);
        listaFiliais.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollLista = new JScrollPane(listaFiliais);
        scrollLista.setBounds(Pcent('x', 5), Pcent('y', 10), Pcent('x', 40), Pcent('y', 80));
        panel.add(scrollLista);

        // Botões de ação
        JButton botaoEditar = new JButton("Editar Gerente");
        JButton botaoCadastrar = new JButton("Cadastrar Gerente");
        JButton botaoRemover = new JButton("Remover Gerente");

        botaoEditar.setBounds(Pcent('x', 50), Pcent('y', 30), 180, 40);
        botaoCadastrar.setBounds(Pcent('x', 50), Pcent('y', 40), 180, 40);
        botaoRemover.setBounds(Pcent('x', 50), Pcent('y', 50), 180, 40);

        panel.add(botaoEditar);
        panel.add(botaoCadastrar);
        panel.add(botaoRemover);

        // Botão de voltar
        JButton botaoVoltar = new JButton("←");
        botaoVoltar.setBounds(10, 10, 50, 30);
        botaoVoltar.addActionListener(e -> TelaPrincipalDono.mostrar());
        panel.add(botaoVoltar);

        botaoEditar.addActionListener(e -> {
            int index = listaFiliais.getSelectedIndex();
            if (index < 0) {
                JOptionPane.showMessageDialog(frame, "Selecione uma filial.");
                return; //principio do fail fast de clean code
            }

            Filial filial = usuario.getFranquia().getFiliais().get(index);
            Gerente gerente = filial.getGerente();
            if (gerente == null) {
                JOptionPane.showMessageDialog(frame, "Esta filial não possui gerente cadastrado.");
                return;
            }
            Runnable fazNada= () -> {};
            TelaCriarEditarPessoa.CriarPessoa(gerente, fazNada, TelaGerenciarGerentes::mostrar);
        });

        botaoCadastrar.addActionListener(e -> {
            int index = listaFiliais.getSelectedIndex();
            if (index < 0) {
                JOptionPane.showMessageDialog(frame, "Selecione uma filial.");
                return;
            }

            Filial filial = usuario.getFranquia().getFiliais().get(index);
            if (filial.getGerente() != null) {
                JOptionPane.showMessageDialog(frame, "Esta filial já possui um gerente.");
            }
            Gerente novo_gerente= new Gerente();
            Runnable cadastrar_gerente= () -> filial.setGerente(novo_gerente);
            TelaCriarEditarPessoa.CriarPessoa(novo_gerente, cadastrar_gerente, TelaGerenciarGerentes::mostrar);


        });

        botaoRemover.addActionListener(e -> {
            int index = listaFiliais.getSelectedIndex();
            if (index >= 0) {
                Filial filial = usuario.getFranquia().getFiliais().get(index);
                if (filial.getGerente() != null) {
                    int confirm = JOptionPane.showConfirmDialog(frame, "Deseja remover o gerente desta filial?", "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        filial.setGerente(null);
                        JOptionPane.showMessageDialog(frame, "Gerente removido.");
                        mostrar();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Esta filial não possui gerente.");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Selecione uma filial.");
            }
        });

        frame.add(panel);
        atualizar_tela();
    }
}