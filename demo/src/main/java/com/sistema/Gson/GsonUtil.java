package com.sistema.Gson;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.sistema.Gerenciador_sistema;
import com.sistema.franquia.filial.Filial;
import com.sistema.pessoa.Dono;
import java.io.*;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.util.*;

public class GsonUtil {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    private static Path CAMINHO_ARQUIVO_DONO;
    private static Path CAMINHO_ARQUIVO_FILIAIS;

    public GsonUtil() {
        try {// Define os caminhos dos arquivos de permanência
            URL url= Gerenciador_sistema.class.getClassLoader().getResource("arquivosPermanencia");
            CAMINHO_ARQUIVO_FILIAIS= Paths.get(url.toURI()).resolve("filiais.json");
            CAMINHO_ARQUIVO_DONO= Paths.get(url.toURI()).resolve("dono.json");
        } catch (URISyntaxException e) {
            System.out.println("Erro ao obter o caminho dos arquivos de permanência: " + e.getMessage());
            return; // Encerra o método se houver erro
        }
    }

    // Métodos para permanência do objeto Filial
    public void salvarFiliaisJSON(List<Filial> filiais) throws IOException {
        Objects.requireNonNull(CAMINHO_ARQUIVO_FILIAIS, "Caminho do arquivo não pode ser nulo");
        Objects.requireNonNull(filiais, "Lista de filiais não pode ser nula");
        
        try (Writer writer = Files.newBufferedWriter(CAMINHO_ARQUIVO_FILIAIS)) {
            gson.toJson(filiais, writer);
        } catch (JsonIOException e) {
            throw new IOException("Falha na serialização para JSON", e);
        }
    }

    public List<Filial> carregarFiliaisObjeto() throws IOException {
        Objects.requireNonNull(CAMINHO_ARQUIVO_FILIAIS, "Caminho do arquivo não pode ser nulo");
        
        if (!Files.exists(CAMINHO_ARQUIVO_FILIAIS)) {
            throw new FileNotFoundException("Arquivo não encontrado: " + CAMINHO_ARQUIVO_FILIAIS);
        }

        Type filialListType = new TypeToken<List<Filial>>(){}.getType();
        try (Reader reader = Files.newBufferedReader(CAMINHO_ARQUIVO_FILIAIS)) {
            return gson.fromJson(reader, filialListType);
        } catch (JsonSyntaxException e) {
            throw new IOException("JSON inválido no arquivo: " + CAMINHO_ARQUIVO_FILIAIS, e);
        }
    }
    
    
    //metodos pra permanencia do objeto dono
    public void salvarDonoJSON(Dono dono) throws IOException {
        Objects.requireNonNull(CAMINHO_ARQUIVO_DONO, "Caminho do arquivo não pode ser nulo");
        Objects.requireNonNull(dono, "Objeto Dono não pode ser nulo");
        
        try (Writer writer = Files.newBufferedWriter(CAMINHO_ARQUIVO_DONO)) {
            gson.toJson(dono, writer);
        } catch (JsonIOException e) {
            throw new IOException("Falha na serialização do Dono para JSON", e);
        }
    }

    public Dono carregarDonoObjeto() throws IOException {
        Objects.requireNonNull(CAMINHO_ARQUIVO_DONO, "Caminho do arquivo não pode ser nulo");
        
        if (!Files.exists(CAMINHO_ARQUIVO_DONO)) {
            throw new FileNotFoundException("Arquivo não encontrado: " + CAMINHO_ARQUIVO_DONO);
        }

        try (Reader reader = Files.newBufferedReader(CAMINHO_ARQUIVO_DONO)) {
            return gson.fromJson(reader, Dono.class);
        } catch (JsonSyntaxException e) {
            throw new IOException("JSON inválido no arquivo: " + CAMINHO_ARQUIVO_DONO, e);
        }
    }

    public boolean arquivoDonoExiste() {
    try {
        // Verifica se o caminho foi inicializado corretamente
        if (CAMINHO_ARQUIVO_DONO == null) {
            
            if (CAMINHO_ARQUIVO_DONO == null) {
                return false;
            }
        }
        
        // Verifica existência do arquivo
        if (!Files.exists(CAMINHO_ARQUIVO_DONO)) {
            return false;
        }
        
        // Verifica se o arquivo não está vazio
        if (Files.size(CAMINHO_ARQUIVO_DONO) == 0) {
            return false;
        }
        
        // Tenta ler o arquivo para verificar se é um JSON válido
        try (Reader reader = Files.newBufferedReader(CAMINHO_ARQUIVO_DONO)) {
            gson.fromJson(reader, Dono.class);
            return true;
        }
        
    } catch (Exception e) {
        System.err.println("Erro ao verificar arquivo do dono: " + e.getMessage());
        return false;
    }
}


}