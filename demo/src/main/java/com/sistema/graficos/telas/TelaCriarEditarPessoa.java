package com.sistema.graficos.telas;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sistema.graficos.Grafico;
import com.sistema.pessoa.Pessoa;
import com.sistema.pessoa.Vendedor;
import com.sistema.validadores.Validar;

public class TelaCriarEditarPessoa extends Grafico {
    
    public static void CriarPessoa(Pessoa pessoaExistente, Runnable pegarPessoa, Runnable voltarTela) {
        
        limpar_tela();
        frame.setTitle(pessoaExistente.vazio() ? "Criar pessoa" : "Editar pessoa");

        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, tela_X, tela_y);

        // Campos
        JLabel labelNome = new JLabel("Nome:");
        JTextField campoNome = new JTextField();
        JLabel labelCpf = new JLabel("CPF:");
        JTextField campoCpf = new JTextField();
        JLabel labelIdade = new JLabel("Idade:");
        JTextField campoIdade = new JTextField();
        JLabel labelEmail = new JLabel("Email:");
        JTextField campoEmail = new JTextField();
        JLabel labelSenha = new JLabel("Senha:");
        JTextField campoSenha = new JTextField();

        int x = Pcent('x', 30), 
        y = Pcent('y', 20), 
        h = 25, 
        wLabel = 100, 
        wCampo = 200, 
        dy = 35;

        labelNome.setBounds(x, y, wLabel, h);
        campoNome.setBounds(x + wLabel, y, wCampo, h);
        labelCpf.setBounds(x, y += dy, wLabel, h);
        campoCpf.setBounds(x + wLabel, y, wCampo, h);
        labelIdade.setBounds(x, y += dy, wLabel, h);
        campoIdade.setBounds(x + wLabel, y, wCampo, h);
        labelEmail.setBounds(x, y += dy, wLabel, h);
        campoEmail.setBounds(x + wLabel, y, wCampo, h);
        labelSenha.setBounds(x, y += dy, wLabel, h);
        campoSenha.setBounds(x + wLabel, y, wCampo, h);

        // Preenchimento para edição
        if (!pessoaExistente.vazio()) {
            campoNome.setText(pessoaExistente.getNome());
            campoCpf.setText(pessoaExistente.getCpf());
            campoIdade.setText(String.valueOf(pessoaExistente.getIdade()));
            campoEmail.setText(pessoaExistente.getEmail());
            campoSenha.setText(pessoaExistente.getSenha());
        }

        // Botão confirmar
        JButton botaoConfirmar = new JButton("Confirmar");
        botaoConfirmar.setBounds(Pcent('x', 47), y + dy + 20, 120, 30);

        botaoConfirmar.addActionListener(e -> {
            String nome = campoNome.getText().trim();
            String cpf = campoCpf.getText().trim();
            String idadeTexto = campoIdade.getText().trim();
            int idade;
            String email = campoEmail.getText().trim();
            String senha = campoSenha.getText().trim();

            //verificadores 
            if (nome.isEmpty() || cpf.isEmpty() || idadeTexto.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Preencha todos os campos.");
                return;
            }
            if (!Validar.CPF(cpf)) {
                JOptionPane.showMessageDialog(frame, "CPF inválido.");
                return;
            }
            if (!Validar.email(email)) {
                JOptionPane.showMessageDialog(frame, "Email inválido.");
                return;
            }
            if (!Validar.senha(senha)) {
                JOptionPane.showMessageDialog(frame, "Senha inválida.");
                return;
            }
            if (!Validar.nome(nome)) {
                JOptionPane.showMessageDialog(frame, "Nome inválido.");
                return;
            }
            try {
                idade = Integer.parseInt(idadeTexto);
                if (!Validar.idade(idade)) {
                    JOptionPane.showMessageDialog(frame, "Idade inválida.");
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Idade inválida.");
                return;
            }
            
                           
            pessoaExistente.setNome(nome);                
            pessoaExistente.setCpf(cpf);               
            pessoaExistente.setIdade(idade);
            pessoaExistente.setEmail(email);
            pessoaExistente.setSenha(senha);
            
            pegarPessoa.run();
            voltarTela.run(); // Volta para a tela anterior          
        });

        // Adiciona ao painel
        panel.add(labelNome); panel.add(campoNome);
        panel.add(labelCpf); panel.add(campoCpf);
        panel.add(labelIdade); panel.add(campoIdade);
        panel.add(labelEmail); panel.add(campoEmail);
        panel.add(labelSenha); panel.add(campoSenha);
        panel.add(botaoConfirmar);

        frame.add(panel);
        atualizar_tela();
    }
}
