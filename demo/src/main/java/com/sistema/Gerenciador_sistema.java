package com.sistema;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

import com.sistema.Gson.GsonUtil;
import com.sistema.contrato.Pedido_altereçao;
import com.sistema.franquia.Franquia;
import com.sistema.franquia.filial.Filial;
import com.sistema.graficos.Grafico;
import com.sistema.graficos.User;
import com.sistema.pessoa.*;

public class Gerenciador_sistema {

    Path CAMINHO_ARQUIVO_DONO;
    Path CAMINHO_ARQUIVO_FILIAIS;
    Grafico graficos;
    Franquia franquia;


    public void Start_sistema() {
        System.out.println("Iniciando o sistema...");
        
        // Define os caminhos dos arquivos de permanência
        CAMINHO_ARQUIVO_DONO= Paths.get("").toAbsolutePath().resolve(Paths.get("trabalho-3-OO","demo", "src", "main", "arquivosPermanencia"));

        CAMINHO_ARQUIVO_FILIAIS= CAMINHO_ARQUIVO_DONO.resolve("filiais.json");
        CAMINHO_ARQUIVO_DONO= CAMINHO_ARQUIVO_DONO.resolve("dono.json");

        // Inicializa a franquia
        franquia = new Franquia();

        // Carrega os dados da franquia
        try {
            franquia.setDono(GsonUtil.carregarDonoObjeto(CAMINHO_ARQUIVO_DONO));
            franquia.setFiliais(GsonUtil.carregarFiliaisObjeto(CAMINHO_ARQUIVO_FILIAIS));
        } catch (IOException e) {
            System.out.println("Erro ao carregar os dados da franquia: " + e.getMessage());
        }catch (Exception e) {
            System.out.println("Erro inesperado ao carregar os dados da franquia: " + e.getMessage());
        }
        franquia.atualiza_funcionarios(); 

        //inicia os graficos
        graficos = new Grafico();
        graficos.tela_login_principal(this); //passa o endereço do gerenciador para os graficos
    }

    public void caminho() { //para verificaçao dos caminhos
        System.out.println("Caminho do arquivo de dono: " + CAMINHO_ARQUIVO_DONO);
        System.out.println("Caminho do arquivo de filiais: " + CAMINHO_ARQUIVO_FILIAIS);
    }

    public Franquia getFranquia() {
        return franquia;
    }

    public static void mostrarNotificacao(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Notificação", JOptionPane.INFORMATION_MESSAGE);
    }

    public void verificar_notificaçoes(User usuario) {
        
        if (usuario.getPessoa() instanceof Dono) {
            for (Filial filial : usuario.getFranquia().getFiliais()) {
                if (filial.getGerente() == null) {
                    mostrarNotificacao("a filial " + filial.getNome() + "nao possui um gerente.");
                }
            }  
        } else if (usuario.getPessoa() instanceof Gerente && usuario.getFilial().getPedidos_alteracao() != null) {
            for (Pedido_altereçao pedido : usuario.getFilial().getPedidos_alteracao()) {
                mostrarNotificacao("Novo pedido de alteração recebido de " + pedido.getRequeridor().getNome() + 
                " para o contrato: " + pedido.getVenda().getId() + ". Motivo: " + pedido.getMotivo());
            }
        }
    }

    //public void adcioar_filial()
}
