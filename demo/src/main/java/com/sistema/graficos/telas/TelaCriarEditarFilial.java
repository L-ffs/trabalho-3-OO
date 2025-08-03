package com.sistema.graficos.telas;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sistema.franquia.filial.Filial;
import com.sistema.pessoa.Gerente;
import com.sistema.graficos.Grafico;
import com.sistema.validadores.Validar;

public class TelaCriarEditarFilial extends Grafico {

    public static void CriarFilial(Filial filial, Runnable salvarFilial, Runnable voltarTela) {
        limpar_tela();
        frame.setTitle("Criar Nova Filial");

        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, tela_X, tela_y);

        // Campos da filial
        JLabel labelNomeFilial = new JLabel("Nome da Filial:");
        JTextField campoNomeFilial = new JTextField();
        JLabel labelEnderecoFilial = new JLabel("Endereço da Filial:");
        JTextField campoEnderecoFilial = new JTextField();

        labelNomeFilial.setBounds(Pcent('x', 30), Pcent('y', 15), 150, 25);
        campoNomeFilial.setBounds(Pcent('x', 45), Pcent('y', 15), 250, 25);
        labelEnderecoFilial.setBounds(Pcent('x', 30), Pcent('y', 22), 150, 25);
        campoEnderecoFilial.setBounds(Pcent('x', 45), Pcent('y', 22), 250, 25);

        panel.add(labelNomeFilial);
        panel.add(campoNomeFilial);
        panel.add(labelEnderecoFilial);
        panel.add(campoEnderecoFilial);

        // Separador
        JLabel labelGerente = new JLabel("Dados do Gerente da Nova Filial:");
        labelGerente.setBounds(Pcent('x', 30), Pcent('y', 30), 300, 25);
        panel.add(labelGerente);

        // Campos do gerente
        JLabel labelNomeGerente = new JLabel("Nome:");
        JTextField campoNomeGerente = new JTextField();
        JLabel labelCpfGerente = new JLabel("CPF:");
        JTextField campoCpfGerente = new JTextField();
        JLabel labelEmailGerente = new JLabel("Email:");
        JTextField campoEmailGerente = new JTextField();
        JLabel labelSenhaGerente = new JLabel("Senha:");
        JTextField campoSenhaGerente = new JTextField();

        int x = Pcent('x', 30), y = Pcent('y', 37), h = 25, wLabel = 100, wCampo = 200, dy = 35;

        labelNomeGerente.setBounds(x, y, wLabel, h);
        campoNomeGerente.setBounds(x + wLabel, y, wCampo, h);
        labelCpfGerente.setBounds(x, y += dy, wLabel, h);
        campoCpfGerente.setBounds(x + wLabel, y, wCampo, h);
        labelEmailGerente.setBounds(x, y += dy, wLabel, h);
        campoEmailGerente.setBounds(x + wLabel, y, wCampo, h);
        labelSenhaGerente.setBounds(x, y += dy, wLabel, h);
        campoSenhaGerente.setBounds(x + wLabel, y, wCampo, h);

        panel.add(labelNomeGerente); panel.add(campoNomeGerente);
        panel.add(labelCpfGerente); panel.add(campoCpfGerente);
        panel.add(labelEmailGerente); panel.add(campoEmailGerente);
        panel.add(labelSenhaGerente); panel.add(campoSenhaGerente);

        // Botão confirmar
        JButton botaoConfirmar = new JButton("Confirmar");
        botaoConfirmar.setBounds(Pcent('x', 47), y + dy + 20, 120, 30);

        botaoConfirmar.addActionListener(e -> {
            String nomeFilial = campoNomeFilial.getText().trim();
            String enderecoFilial = campoEnderecoFilial.getText().trim();
            String nomeGerente = campoNomeGerente.getText().trim();
            String cpfGerente = campoCpfGerente.getText().trim();
            String emailGerente = campoEmailGerente.getText().trim();
            String senhaGerente = campoSenhaGerente.getText().trim();

            // Validação simples
            if (nomeFilial.isEmpty() || enderecoFilial.isEmpty() ||
                nomeGerente.isEmpty() || cpfGerente.isEmpty() ||
                emailGerente.isEmpty() || senhaGerente.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Preencha todos os campos.");
                return;
            }
            if (!Validar.CPF(cpfGerente)) {
                JOptionPane.showMessageDialog(frame, "CPF do gerente inválido.");
                return;
            }
            if (!Validar.email(emailGerente)) {
                JOptionPane.showMessageDialog(frame, "Email do gerente inválido.");
                return;
            }
            if (!Validar.senha(senhaGerente)) {
                JOptionPane.showMessageDialog(frame, "Senha do gerente inválida.");
                return;
            }

            // Preenche objeto filial e gerente
            filial.setNome(nomeFilial);
            filial.setEndereco(enderecoFilial);
            Gerente gerente = new Gerente(nomeGerente, cpfGerente, 18, emailGerente, senhaGerente); // idade padrão 18
            filial.setGerente(gerente);

            salvarFilial.run();
            voltarTela.run();
        });

        panel.add(botaoConfirmar);

        frame.add(panel);
        atualizar_tela();
    }
}