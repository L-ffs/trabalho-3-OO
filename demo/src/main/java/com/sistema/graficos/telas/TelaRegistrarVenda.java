package com.sistema.graficos.telas;

import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sistema.Produto.Produto;
import com.sistema.contrato.Contrato;
import com.sistema.graficos.Grafico;
import com.sistema.pessoa.Vendedor;

public class TelaRegistrarVenda extends Grafico {
    

    public static void mostrar() {

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
    botaoVoltar.addActionListener(e -> usuario.getPessoa().MostrarTelaInicial());

    // Preenche o comboBox com os produtos do estoque
    Map<String, Integer> estoque = usuario.getFilial().getEstoque();
    estoque= estoque == null ? Map.of() : estoque; // Evita NullPointerException
    for (String NomeProduto : estoque.keySet()) {
        if (estoque.get(NomeProduto) > 0) {
            Produto produto= usuario.getFilial().ProdutoPorNome(NomeProduto);
            boxProduto.addItem(NomeProduto + " - R$" + produto.getPreco());
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
        
        Map<String, Integer> estoq= usuario.getFilial().getEstoque();
        int qntd_produto= estoq.getOrDefault(produto, 0);
        estoq.put(produto, qntd_produto-1);

        JOptionPane.showMessageDialog(frame, "Venda registrada com sucesso!");

        usuario.getPessoa().MostrarTelaInicial();
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
}
