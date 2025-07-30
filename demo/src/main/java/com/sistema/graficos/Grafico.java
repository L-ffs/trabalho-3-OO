package com.sistema.graficos;

import com.sistema.Gerenciador_sistema;
import com.sistema.Produto.Produto;
import com.sistema.contrato.Contrato;
import com.sistema.contrato.Pedido_altereçao;
import com.sistema.franquia.filial.Filial;
import com.sistema.graficos.User;
import com.sistema.pessoa.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.*;
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
            tela_principal_gerente();
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
    scroll.setBounds(120, Pcent('y', 15), Pcent('x', 60), Pcent('y', 60));
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
        "Produtos da filial", 
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
            case "Produtos da filial":
                botao.addActionListener(e -> {
                    // exemplo de ação: chamar tela de listagem
                    System.out.println("Produtos da filial");
                    tela_manipular_produtos();
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

    public void tela_manipular_produtos() {
        limpar_tela();
        frame.setTitle("Manipular Produtos");

        JPanel panel = new JPanel(null);
        panel.setBounds(0, 0, tela_X, tela_y);

        // Lista de produtos (String) para exibição
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        Map<Produto, Integer> estoque = usuario.getFilial().getEstoque() == null ? Map.of() : usuario.getFilial().getEstoque();
        for (Produto p : estoque.keySet()) {
            modeloLista.addElement(p.getNome() + " (Qtd: " + estoque.get(p) + ")");
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

        // Adicionar ações (apenas esqueleto aqui, você pode completar com lógica real)
        botaoExcluir.addActionListener(e -> {
            int index = listaProdutos.getSelectedIndex();
            if (index >= 0) { // -1 se nao escolhido
                Produto selecionado = (Produto) estoque.keySet().toArray()[index];
                estoque.remove(selecionado);
                modeloLista.remove(index);
            } else {
                JOptionPane.showMessageDialog(frame, "Selecione um produto para excluir.");
            }
        });

        botaoEditar.addActionListener(e -> {
            int index = listaProdutos.getSelectedIndex();
            if (index >= 0) {
                Produto selecionado = (Produto) estoque.keySet().toArray()[index];
                // Aqui você pode chamar outra função para abrir a tela de edição
                tela_criar_ou_editar_produto(selecionado);
            } else {
                JOptionPane.showMessageDialog(frame, "Selecione um produto para editar.");
            }
        });

        botaoNovo.addActionListener(e -> {
            // Chame aqui uma função para criar novo produto
            tela_criar_ou_editar_produto(null);
        });

        frame.add(panel);
        atualizar_tela();
    }

    public void tela_criar_ou_editar_produto(Produto produtoExistente) {
    limpar_tela();
    frame.setTitle(produtoExistente == null ? "Criar Produto" : "Editar Produto");

    JPanel panel = new JPanel(null);
    panel.setBounds(0, 0, tela_X, tela_y);

    // Campos
    JLabel labelNome = new JLabel("Nome:");
    JTextField campoNome = new JTextField();
    JLabel labelValor = new JLabel("Valor:");
    JTextField campoValor = new JTextField();
    JLabel labelQtd = new JLabel("Quantidade:");
    JTextField campoQtd = new JTextField();


    labelNome.setBounds(Pcent('x', 40), Pcent('y', 25), 100, 25);
    campoNome.setBounds(Pcent('x', 40) + 100, Pcent('y', 25), 200, 25);
    labelValor.setBounds(Pcent('x', 40), Pcent('y', 32), 100, 25);
    campoValor.setBounds(Pcent('x', 40) + 100, Pcent('y', 32), 200, 25);
    labelQtd.setBounds(Pcent('x', 40), Pcent('y', 39), 100, 25);
    campoQtd.setBounds(Pcent('x', 40) + 100, Pcent('y', 39), 200, 25);

    // Se for edição, preencher os campos
    if (produtoExistente != null) {
        campoNome.setText(produtoExistente.getNome());
        campoValor.setText(String.valueOf(produtoExistente.getPreco()));
        campoQtd.setText(String.valueOf(usuario.getFilial().getEstoque().get(produtoExistente)));
    }

    // Botão confirmar
    JButton botaoConfirmar = new JButton("Confirmar");
    botaoConfirmar.setBounds(Pcent('x', 47), Pcent('y', 50), 120, 30);

    botaoConfirmar.addActionListener(e -> {
        String nome = campoNome.getText().trim();
        String valorTexto = campoValor.getText().trim();
        String qtdTexto = campoQtd.getText().trim();

        if (nome.isEmpty() || valorTexto.isEmpty() || qtdTexto.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Preencha todos os campos.");
            return;
        }

        try {
            double preco = Double.parseDouble(valorTexto);
            int quantidade = Integer.parseInt(qtdTexto);

            if (produtoExistente == null) {
                Produto novo = new Produto(nome, preco);
                if( usuario.getFilial().getEstoque() == null) {
                    usuario.getFilial().setEstoque(new java.util.HashMap<>());
                }
                usuario.getFilial().getEstoque().put(novo, quantidade);
            } else {
                usuario.getFilial().getEstoque().remove(produtoExistente);
                produtoExistente.setNome(nome);
                produtoExistente.setPreco(preco);
                usuario.getFilial().getEstoque().put(produtoExistente, quantidade);
            }

            tela_manipular_produtos(); // Volta para tela anterior
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Valor ou quantidade inválidos.");
        }
    });

    // Adicionar componentes
    panel.add(labelNome); panel.add(campoNome);
    panel.add(labelValor); panel.add(campoValor);
    panel.add(labelQtd); panel.add(campoQtd);
    panel.add(botaoConfirmar);

    frame.add(panel);
    atualizar_tela();
}

public void tela_manipular_vendedores() {
    limpar_tela();
    frame.setTitle("Manipular Vendedores");

    JPanel panel = new JPanel(null);
    panel.setBounds(0, 0, tela_X, tela_y);

    // Lista de vendedores
    DefaultListModel<String> modeloLista = new DefaultListModel<>();
    List<Vendedor> vendedores = usuario.getFilial().getVendedores() == null ? usuario.getFilial().getVendedores() : new ArrayList<Vendedor>(); // Supondo que você tenha uma lista em algum lugar
    
    
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
            tela_criar_ou_editar_vendedor(selecionado);
        } else {
            JOptionPane.showMessageDialog(frame, "Selecione um vendedor para editar.");
        }
    });

    botaoNovo.addActionListener(e -> {
        tela_criar_ou_editar_vendedor(null);
    });

    frame.add(panel);
    atualizar_tela();
}

public void tela_criar_ou_editar_vendedor(Vendedor vendedorExistente) {
    limpar_tela();
    frame.setTitle(vendedorExistente == null ? "Criar Vendedor" : "Editar Vendedor");

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

    int x = Pcent('x', 30), y = Pcent('y', 20), h = 25, wLabel = 100, wCampo = 200, dy = 35;

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
    if (vendedorExistente != null) {
        campoNome.setText(vendedorExistente.getNome());
        campoCpf.setText(vendedorExistente.getCpf());
        campoIdade.setText(String.valueOf(vendedorExistente.getIdade()));
        campoEmail.setText(vendedorExistente.getEmail());
        campoSenha.setText(vendedorExistente.getSenha());
    }

    // Botão confirmar
    JButton botaoConfirmar = new JButton("Confirmar");
    botaoConfirmar.setBounds(Pcent('x', 47), y + dy + 20, 120, 30);

    botaoConfirmar.addActionListener(e -> {
        String nome = campoNome.getText().trim();
        String cpf = campoCpf.getText().trim();
        String idadeTexto = campoIdade.getText().trim();
        String email = campoEmail.getText().trim();
        String senha = campoSenha.getText().trim();

        if (nome.isEmpty() || cpf.isEmpty() || idadeTexto.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Preencha todos os campos.");
            return;
        }

        try {
            int idade = Integer.parseInt(idadeTexto);

            if (vendedorExistente == null) {
                Vendedor novo = new Vendedor(nome, cpf, idade, email, senha);
                usuario.getFilial().getVendedores().add(novo);
            } else {
                vendedorExistente.setNome(nome);
                vendedorExistente.setCpf(cpf);
                vendedorExistente.setIdade(idade);
                vendedorExistente.setEmail(email);
                vendedorExistente.setSenha(senha);
            }

            tela_manipular_vendedores();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Idade inválida.");
        }
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
