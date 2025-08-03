package com.sistema.graficos.telas;

import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sistema.Produto.Produto;
import com.sistema.graficos.Grafico;

public class TelaCriarEditarProduto extends Grafico {
    
    public static void mostrar(Produto produtoExistente) {

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
                    usuario.getFilial().setEstoque(new HashMap<>());
                }
                usuario.getFilial().getEstoque().put(novo.getNome(), quantidade);
                usuario.getFilial().getProdutos().add(novo);
            } else {
                usuario.getFilial().getEstoque().remove(produtoExistente.getNome());
                usuario.getFilial().getProdutos().remove(produtoExistente);
                produtoExistente.setNome(nome);
                produtoExistente.setPreco(preco);
                usuario.getFilial().getEstoque().put(produtoExistente.getNome(), quantidade);
                usuario.getFilial().getProdutos().add(produtoExistente);
            }

            TelaManipularProdutos.mostrar(); // Volta para tela anterior
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
}
