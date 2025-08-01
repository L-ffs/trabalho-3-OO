package com.sistema.graficos.telas;

import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sistema.Produto.Produto;
import com.sistema.contrato.Contrato;
import com.sistema.graficos.Grafico;
import com.sistema.pessoa.Gerente;
import com.sistema.pessoa.Pessoa;
import com.sistema.pessoa.Vendedor;


public class TelaHistoricoVendas extends Grafico{


    public static void mostrar() {

     
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


        try {
            
            for (Contrato c : usuario.getFilial().getContratos()) {
                
                if (c.getVendedor().equals(p) || p instanceof Gerente) {// p vendeu ou é gerente
                        
                    sb.append("ID: ").append(c.getId())
                        .append(" | Comprador: ").append(c.getComprador())
                        .append(" | Produto: ").append(c.getProduto())
                        .append(" | Preço: R$").append(c.getPreco())
                        .append("\n");    
                    }    
            } 
        } catch (NullPointerException e) {
            sb.append("Nenhuma venda encontrada.");
        }
        

        if (sb.length() == 0) {
            sb.append("Nenhuma venda encontrada.");
        }

        areaVendas.setText(sb.toString());

        JButton botaoVoltar = new JButton("←");
        botaoVoltar.setBounds(10, 10, 50, 30);
        botaoVoltar.addActionListener(e -> usuario.getPessoa().MostrarTelaInicial());
        panel.add(botaoVoltar);

        frame.add(panel);
        atualizar_tela();
    }
}