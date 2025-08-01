package com.sistema.graficos.telas;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.sistema.contrato.Contrato;
import com.sistema.contrato.Pedido_altereçao;
import com.sistema.graficos.Grafico;

public class TelaSolicitarRetificaçao extends Grafico {
    

    public static void mostrar() {

        limpar_tela();
        frame.setTitle("Solicitar Retificação");

        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, tela_X, tela_y);

        JLabel labelVenda = new JLabel("Selecione a venda:");
        JComboBox<String> comboVendas = new JComboBox<>();
        JLabel labelMotivo = new JLabel("Motivo:");
        JTextArea campoMotivo = new JTextArea();
        JButton btnConfirmar = new JButton("Confirmar");

        // Preenche o combo com as vendas disponíveis
        try{
            for (Contrato c : usuario.getFilial().getContratos()) {
            if (c.getVendedor() != usuario.getPessoa()) {
                continue; // Ignora contratos que não são do vendedor atual
            }
            comboVendas.addItem(c.toString());
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(frame, "Nenhuma venda registrada para solicitar retificação.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Posicionamento
        labelVenda.setBounds(Pcent('x', 35), Pcent('y', 25), 150, 25);
        comboVendas.setBounds(Pcent('x', 45), Pcent('y', 25), 300, 25);
        labelMotivo.setBounds(Pcent('x', 35), Pcent('y', 35), 150, 25);
        campoMotivo.setBounds(Pcent('x', 45), Pcent('y', 35), 300, 100);
        btnConfirmar.setBounds(Pcent('x', 47), Pcent('y', 60), 100, 30);

        // Ação do botão
        btnConfirmar.addActionListener(e -> {
            Contrato selecionado = (Contrato) comboVendas.getSelectedItem();
            String motivo = campoMotivo.getText().trim();

            if (selecionado == null || motivo.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Selecione uma venda e informe o motivo.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                Pedido_altereçao ret = new Pedido_altereçao(usuario.getPessoa(), selecionado, motivo);
                usuario.getFilial().getPedidos_alteracao().add(ret);
                JOptionPane.showMessageDialog(frame, "Pedido de retificação enviada com sucesso!");
                usuario.getPessoa().MostrarTelaInicial(); // Retorna para a tela anterior
            }
        });

        panel.add(labelVenda);
        panel.add(comboVendas);
        panel.add(labelMotivo);
        panel.add(campoMotivo);
        panel.add(btnConfirmar);
        frame.add(panel);

        atualizar_tela();
    }
}
