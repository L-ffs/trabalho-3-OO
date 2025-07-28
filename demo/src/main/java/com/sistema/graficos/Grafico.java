package com.sistema.graficos;

import com.sistema.Gerenciador_sistema;
import com.sistema.graficos.User;
import com.sistema.pessoa.Pessoa;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Grafico {

    JFrame frame;
    int tela_X;
    int tela_y;
    User usuario; //variavel para armazenar o usuario logado
    

    public Grafico() {
        tela_X = 1280;
        tela_y = 960;
        usuario= null;
        frame= new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setBounds(0, 0, tela_X, tela_y);
        frame.setVisible(true);
        

    }

    public void tela_login_principal(Gerenciador_sistema sistema) {
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
            if (validadorLogin(email, senha, sistema)) {
                //proxima tela
            }
        });

        atualizar_tela();
    }

    private void limpar_tela() {
        frame.getContentPane().removeAll();
        atualizar_tela();
    }

    private void atualizar_tela() {
        frame.revalidate();
        frame.repaint();
    }

    private int Pcent(char eixo,double percent) {

        boolean ForaIntervalo= percent < 0 || percent > 100;
        boolean EixoInvalido= eixo != 'x' && eixo != 'y';
        if (ForaIntervalo && EixoInvalido) {
            System.out.println("Percentual inválido. Deve estar entre 0 e 100.");
            return 1;
        }
        int valor= (eixo == 'x' ? tela_X : tela_y);
        return (int) (valor * (percent / 100));
    }   

    public boolean validadorLogin(String email, String senha, Gerenciador_sistema sistema) {
        System.out.println("enrtou na validadorLogin");
        Pessoa pessoa = null;
        boolean emailExiste= sistema.getFranquia().Email_existe(email);
        pessoa= emailExiste ? sistema.getFranquia().getPessoaPorEmail(email) : null;
        boolean logavel= pessoa != null && pessoa.getSenha().equals(senha);

        if (logavel) {
                usuario = new User(pessoa, null, sistema.getFranquia());
                usuario.achar_filial();
                System.out.println("Login bem-sucedido para: " + email);
                return true;
            }
            return false;
        }
    
        

}
