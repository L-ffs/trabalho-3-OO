package com.sistema.Gson;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.sistema.franquia.filial.Filial;
import com.sistema.pessoa.Dono;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.*;
import java.util.*;

public class GsonUtil {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    private static final Path PASTA_DADOS;
    private static final Path CAMINHO_ARQUIVO_DONO;
    private static final Path CAMINHO_ARQUIVO_FILIAIS;

    static {
        // Inicialização dos caminhos
        PASTA_DADOS = Paths.get(System.getProperty("user.dir")).resolve("dados");

        // Garante que a pasta "dados" exista
        try {
            Files.createDirectories(PASTA_DADOS);
        } catch (IOException e) {
            System.err.println("Erro ao criar pasta de dados: " + e.getMessage());
        }

        CAMINHO_ARQUIVO_FILIAIS = PASTA_DADOS.resolve("filiais.json");
        CAMINHO_ARQUIVO_DONO = PASTA_DADOS.resolve("dono.json");
    }

    // Métodos para salvar e carregar lista de filiais
    public void salvarFiliaisJSON(List<Filial> filiais) throws IOException {
        Objects.requireNonNull(filiais, "Lista de filiais não pode ser nula");

        try (Writer writer = Files.newBufferedWriter(CAMINHO_ARQUIVO_FILIAIS)) {
            gson.toJson(filiais, writer);
        } catch (JsonIOException e) {
            throw new IOException("Falha na serialização para JSON", e);
        }
    }

    public List<Filial> carregarFiliaisObjeto() throws IOException {
        if (!Files.exists(CAMINHO_ARQUIVO_FILIAIS)) {
            return new ArrayList<>(); // Retorna lista vazia se não existir
        }

        Type filialListType = new TypeToken<List<Filial>>() {}.getType();
        try (Reader reader = Files.newBufferedReader(CAMINHO_ARQUIVO_FILIAIS)) {
            return gson.fromJson(reader, filialListType);
        } catch (JsonSyntaxException e) {
            throw new IOException("JSON inválido no arquivo: " + CAMINHO_ARQUIVO_FILIAIS, e);
        }
    }

    // Métodos para salvar e carregar Dono
    public void salvarDonoJSON(Dono dono) throws IOException {
        Objects.requireNonNull(dono, "Objeto Dono não pode ser nulo");

        try (Writer writer = Files.newBufferedWriter(CAMINHO_ARQUIVO_DONO)) {
            gson.toJson(dono, writer);
        } catch (JsonIOException e) {
            throw new IOException("Falha na serialização do Dono para JSON", e);
        }
    }

    public Dono carregarDonoObjeto() throws IOException {
        if (!Files.exists(CAMINHO_ARQUIVO_DONO)) {
            return null;
        }

        try (Reader reader = Files.newBufferedReader(CAMINHO_ARQUIVO_DONO)) {
            return gson.fromJson(reader, Dono.class);
        } catch (JsonSyntaxException e) {
            throw new IOException("JSON inválido no arquivo: " + CAMINHO_ARQUIVO_DONO, e);
        }
    }

    public boolean arquivoDonoExiste() {
        try {
            if (!Files.exists(CAMINHO_ARQUIVO_DONO) || Files.size(CAMINHO_ARQUIVO_DONO) == 0) {
                return false;
            }

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