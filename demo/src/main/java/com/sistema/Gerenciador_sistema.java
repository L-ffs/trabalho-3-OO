package com.sistema;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.sistema.Gson.GsonUtil;
import com.sistema.franquia.Franquia;
import com.sistema.graficos.Grafico;
import com.sistema.pessoa.Dono;

public class Gerenciador_sistema {

    Path CAMINHO_ARQUIVO_DONO;
    Path CAMINHO_ARQUIVO_FILIAIS;
    Grafico graficos;
    Franquia franquia;


    public void Start_sistema() {
        System.out.println("Iniciando o sistema...");
        
        // Define os caminhos dos arquivos de permanência
        CAMINHO_ARQUIVO_DONO= Paths.get("").toAbsolutePath()
        .resolve(Paths.get("demo", "src", "main", "arquivosPermanencia"));

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

    //public void adcioar_filial()
}
