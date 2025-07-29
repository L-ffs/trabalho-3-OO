package com.sistema.graficos;

import com.sistema.Gerenciador_sistema;
import com.sistema.Produto.Produto;
import com.sistema.contrato.Contrato;
import com.sistema.contrato.Pedido_altereçao;
import com.sistema.franquia.filial.Filial;
import com.sistema.graficos.User;
import com.sistema.pessoa.*;

import java.util.ArrayList;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;

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
                sistema.verificar_notificaçoes(usuario);
                tela_principal();
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

        Pessoa pessoa = null;
        boolean emailExiste= sistema.getFranquia().Email_existe(email);
        pessoa= emailExiste ? sistema.getFranquia().getPessoaPorEmail(email) : null;
        boolean logavel= pessoa != null && pessoa.getSenha().equals(senha);

        if (logavel) {
                usuario = new User(pessoa, null, sistema.getFranquia());
                usuario.achar_filial();
                System.out.println("Login bem-sucedido para: " + usuario.getPessoa().getNome());
                return true;
            }
            return false;
    }
    
    public void tela_principal() {
        if (usuario.getPessoa() instanceof Dono) {
            //tela_principal_dono();
        } else if (usuario.getPessoa() instanceof Gerente) {
            //tela_principal_gerente();
        } else if (usuario.getPessoa() instanceof Vendedor) {
            tela_principal_vendedor();
        }
    } 

    public void tela_principal_vendedor() {
        limpar_tela();
        frame.setTitle("Painel do Vendedor");

        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, tela_X, tela_y);

        JLabel titulo = new JLabel("Bem-vindo, " + usuario.getPessoa().getNome()); 
        titulo.setBounds(Pcent('x', 40), Pcent('y', 10), 400, 30);
        panel.add(titulo);

        JButton btnRegistrarVenda = new JButton("Registrar Venda");
        JButton btnHistoricoVendas = new JButton("Histórico de Vendas");
        JButton btnSolicitarRetificacao = new JButton("Solicitar Retificação");

        
        btnRegistrarVenda.setBounds(Pcent('x', 40), Pcent('y', 25), 200, 30);
        btnHistoricoVendas.setBounds(Pcent('x', 40), Pcent('y', 35), 200, 30);
        btnSolicitarRetificacao.setBounds(Pcent('x', 40), Pcent('y', 45), 200, 30);

        panel.add(btnRegistrarVenda);
        panel.add(btnHistoricoVendas);
        panel.add(btnSolicitarRetificacao);

        frame.add(panel);
        atualizar_tela();

        // Aqui você adiciona o comportamento dos botões
        btnRegistrarVenda.addActionListener(e -> {
            // chamar tela de registrar venda
            tela_registrar_venda();
        });

        btnHistoricoVendas.addActionListener(e -> {
            // chamar tela de histórico de vendas
            tela_historico_vendas();
        });

        btnSolicitarRetificacao.addActionListener(e -> {
            // chamar tela de solicitação de retificação
            tela_solicitar_retificacao();
        });
      
    }

    public void tela_solicitar_retificacao() {

    if (usuario.getFilial().getContratos() == null || usuario.getFilial().getContratos().isEmpty()) {
        JOptionPane.showMessageDialog(frame, "Nenhuma venda registrada para solicitar retificação.", "Erro", JOptionPane.ERROR_MESSAGE);
        return;
    }
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
    for (Contrato c : usuario.getFilial().getContratos()) {
        if (c.getVendedor() != usuario.getPessoa()) {
            continue; // Ignora contratos que não são do vendedor atual
        }
        comboVendas.addItem(c.toString());
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
            JOptionPane.showMessageDialog(frame, "Retificação enviada com sucesso!");
            tela_principal_vendedor(); // Retorna para a tela anterior
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

    public void tela_registrar_venda() {
    limpar_tela();
    frame.setTitle("Registrar Venda");

    JPanel panel = new JPanel(null);
    panel.setBounds(0, 0, tela_X, tela_y);

    // Campo comprador
    JLabel labelComprador = new JLabel("Nome do Comprador:");
    JTextField campoComprador = new JTextField();
    labelComprador.setBounds(Pcent('x', 35), Pcent('y', 25), 150, 25);
    campoComprador.setBounds(Pcent('x', 50), Pcent('y', 25), 200, 25);

    // Produtos disponíveis
    JLabel labelProduto = new JLabel("Produto:");
    JComboBox<String> boxProduto = new JComboBox<>();
    labelProduto.setBounds(Pcent('x', 35), Pcent('y', 32), 100, 25);
    boxProduto.setBounds(Pcent('x', 50), Pcent('y', 32), 200, 25);

    // Preço
    JLabel labelPreco = new JLabel("Preço:");
    JTextField campoPreco = new JTextField();
    labelPreco.setBounds(Pcent('x', 35), Pcent('y', 39), 100, 25);
    campoPreco.setBounds(Pcent('x', 50), Pcent('y', 39), 200, 25);

    // Botão registrar
    JButton botaoRegistrar = new JButton("Registrar Venda");
    botaoRegistrar.setBounds(Pcent('x', 45), Pcent('y', 50), 150, 30);

    // Botão voltar
    JButton botaoVoltar = new JButton("←");
    botaoVoltar.setBounds(10, 10, 50, 30);
    botaoVoltar.addActionListener(e -> tela_principal_vendedor());

    // Preenche o comboBox com os produtos do estoque
    Map<Produto, Integer> estoque = usuario.getFilial().getEstoque();
    estoque= estoque == null ? Map.of() : estoque; // Evita NullPointerException
    for (Produto produto : estoque.keySet()) {
        if (estoque.get(produto) > 0) {
            boxProduto.addItem(produto.getNome() + " - R$" + produto.getPreco());
        }
    }

    botaoRegistrar.addActionListener(e -> {
        String comprador = campoComprador.getText().trim();
        String produto = (String) boxProduto.getSelectedItem();
        String precoStr = campoPreco.getText().trim();

        if (comprador.isEmpty() || produto == null || precoStr.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Preencha todos os campos corretamente.");
            return;
        }

        double preco;
        try {
            preco = Double.parseDouble(precoStr);
            if (preco <= 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Preço inválido.");
            return;
        }

        // Cria e registra o contrato
        Contrato novaVenda = new Contrato(comprador, (Vendedor)usuario.getPessoa(), produto, preco);
        usuario.getFilial().getContratos().add(novaVenda);
        JOptionPane.showMessageDialog(frame, "Venda registrada com sucesso!");

        tela_principal_vendedor();
    });

    panel.add(labelComprador);
    panel.add(campoComprador);
    panel.add(labelProduto);
    panel.add(boxProduto);
    panel.add(labelPreco);
    panel.add(campoPreco);
    panel.add(botaoRegistrar);
    panel.add(botaoVoltar);
    frame.add(panel);
    atualizar_tela();
}

    public void tela_historico_vendas() {
        if (usuario.getFilial().getContratos() == null) {
            JOptionPane.showMessageDialog(frame, "Nenhuma venda registrada.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
    limpar_tela();
    frame.setTitle("Histórico de Vendas");

    JPanel panel = new JPanel(null);
    panel.setBounds(0, 0, tela_X, tela_y);

    JTextArea areaVendas = new JTextArea();
    areaVendas.setEditable(false);
    JScrollPane scroll = new JScrollPane(areaVendas);
    scroll.setBounds(Pcent('x', 20), Pcent('y', 15), Pcent('x', 60), Pcent('y', 60));
    panel.add(scroll);

    // Verifica se é um vendedor e monta o texto
    StringBuilder sb = new StringBuilder();
    Pessoa p = usuario.getPessoa();

    if ((p instanceof Vendedor || p instanceof Gerente) && usuario.getFilial() != null) {

        for (Contrato c : usuario.getFilial().getContratos()) {
            if (c.getVendedor().equals(p) || p instanceof Gerente) {// p vendeu ou é gerente
                
                sb.append("ID: ").append(c.getId())
                  .append(" | Comprador: ").append(c.getComprador())
                  .append(" | Produto: ").append(c.getProduto())
                  .append(" | Preço: R$").append(c.getPreco())
                  .append("\n");
            }
        }
    } else {
        // if instance of dono
    }

    if (sb.length() == 0) {
        sb.append("Nenhuma venda encontrada.");
    }

    areaVendas.setText(sb.toString());

    JButton botaoVoltar = new JButton("←");
    botaoVoltar.setBounds(10, 10, 50, 30);
    botaoVoltar.addActionListener(e -> tela_principal_vendedor());
    panel.add(botaoVoltar);

    frame.add(panel);
    atualizar_tela();
}

    public void tela_principal_gerente() {
        limpar_tela();
    frame.setTitle("Painel do Gerente");

    JPanel panel = new JPanel(null);
    panel.setBounds(0, 0, tela_X, tela_y);

    int margemX = Pcent('x', 5);
    int margemY = Pcent('y', 5);
    int larguraColuna = Pcent('x', 40);
    int alturaTotal = Pcent('y', 80);

    // Painel de botões (esquerda)
    JPanel painelBotoes = new JPanel();
    painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));

    JScrollPane scrollBotoes = new JScrollPane(painelBotoes);
    scrollBotoes.setBounds(margemX, margemY, larguraColuna, alturaTotal);
    panel.add(scrollBotoes);

    // Adiciona botões de exemplo
    String[] nomesBotoes = {
        "Ver vendas da filial", 
        "Gerenciar vendedores", 
        "Ver retificações",
        "Estoque de produtos",
        "Notificações"
    };

    for (String nome : nomesBotoes) {
        JButton botao = new JButton(nome);
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao.setMaximumSize(new Dimension(larguraColuna - 40, 30));
        botao.setFocusPainted(false);
        botao.setMargin(new Insets(5, 10, 5, 10));
        painelBotoes.add(Box.createVerticalStrut(10)); // Espaçamento
        painelBotoes.add(botao);

        // AÇÕES DOS BOTÕES
        switch (nome) {
            case "Ver vendas da filial":
                botao.addActionListener(e -> {
                    // exemplo de ação: chamar tela de listagem
                    System.out.println("Ação: ver vendas da filial");
                    // tela_vendas_filial();
                });
                break;
            case "Gerenciar vendedores":
                botao.addActionListener(e -> {
                    System.out.println("Ação: gerenciar vendedores");
                    // tela_gerenciar_vendedores();
                });
                break;
            case "Ver retificações":
                botao.addActionListener(e -> {
                    System.out.println("Ação: ver retificações");
                    // tela_retificacoes();
                });
                break;
            case "Estoque de produtos":
                botao.addActionListener(e -> {
                    System.out.println("Ação: estoque");
                    // tela_estoque();
                });
                break;
            case "Notificações":
                botao.addActionListener(e -> {
                    System.out.println("Ação: notificações");
                    // tela_notificacoes();
                });
                break;
        }
    }

    // Painel de mensagens (direita)
    JPanel painelMensagens = new JPanel();
    painelMensagens.setLayout(new BoxLayout(painelMensagens, BoxLayout.Y_AXIS));

    JScrollPane scrollMensagens = new JScrollPane(painelMensagens);
    scrollMensagens.setBounds(margemX * 2 + larguraColuna, margemY, larguraColuna, alturaTotal);
    panel.add(scrollMensagens);

    // Adiciona algumas mensagens de exemplo
    for (int i = 1; i <= 5; i++) {
        JTextArea area = new JTextArea("Mensagem " + i + ": conteúdo de exemplo.");
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setEditable(false);
        area.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        painelMensagens.add(area);
    }

    

    frame.add(panel);
    atualizar_tela();
    }



}
