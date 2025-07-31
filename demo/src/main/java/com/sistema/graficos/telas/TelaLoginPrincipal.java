package com.sistema.graficos.telas;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.sistema.graficos.Grafico;
import com.sistema.validadores.Validar;

public class TelaLoginPrincipal extends Grafico{

    public TelaLoginPrincipal() {
        super();
    }

    @Override
    public void mostrar() {

        limpar_tela();
        
        frame.setTitle("Tela de Login");
        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, tela_X, tela_y);

        JLabel labelEmail = new JLabel("Email:");
        JTextField campoEmail = new JTextField();
        JLabel labelSenha = new JLabel("Senha:");
        JPasswordField campoSenha = new JPasswordField();
        JButton botaoLogin = new JButton("Entrar");

        labelEmail.setBounds(Pcent('x', 40), Pcent('y', 30), 100, 25);
        campoEmail.setBounds(Pcent('x', 50), Pcent('y', 30), 200, 25);
        labelSenha.setBounds(Pcent('x', 40), Pcent('y', 35), 100, 25);
        campoSenha.setBounds(Pcent('x', 50), Pcent('y', 35), 200, 25);
        botaoLogin.setBounds(Pcent('x', 47), Pcent('y', 45), 100, 30);

        panel.add(labelEmail);
        panel.add(campoEmail);
        panel.add(labelSenha);
        panel.add(campoSenha);
        panel.add(botaoLogin);
        frame.add(panel);
        
        
        botaoLogin.addActionListener(e -> {
            String email = campoEmail.getText();
            String senha = new String(campoSenha.getPassword());

            if (Validar.dadosLogin(email, senha, usuario.getFranquia())) {
                
                usuario.setPessoa(usuario.getFranquia().getPessoaPorEmail(email));
                usuario.achar_filial();
                
                tela_principal();
            }
        });

        atualizar_tela();
    }
    
}