package com.sistema;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
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
        
        try {// Define os caminhos dos arquivos de permanência
            URL url= Gerenciador_sistema.class.getClassLoader().getResource("arquivosPermanencia");
            CAMINHO_ARQUIVO_FILIAIS= Paths.get(url.toURI()).resolve("filiais.json");
            CAMINHO_ARQUIVO_DONO= Paths.get(url.toURI()).resolve("dono.json");
        } catch (URISyntaxException e) {
            System.out.println("Erro ao obter o caminho dos arquivos de permanência: " + e.getMessage());
            return; // Encerra o método se houver erro
        }
        

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
        graficos = new Grafico(franquia);
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

    

    //public void adcioar_filial()
}
