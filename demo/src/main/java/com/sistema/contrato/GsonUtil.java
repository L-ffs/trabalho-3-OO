package com.sistema.contrato;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.sistema.franquia.filial.Filial;
import com.sistema.pessoa.Dono;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.*;
import java.util.*;

public class GsonUtil {
    private static final Gson gson = new GsonBuilder()
        .setPrettyPrinting()
        .serializeNulls()
        .create();

    public static void salvarFiliaisJSON(Path caminhoArquivo, List<Filial> filiais) throws IOException {
        Objects.requireNonNull(caminhoArquivo, "Caminho do arquivo não pode ser nulo");
        Objects.requireNonNull(filiais, "Lista de filiais não pode ser nula");
        
        try (Writer writer = Files.newBufferedWriter(caminhoArquivo)) {
            gson.toJson(filiais, writer);
        } catch (JsonIOException e) {
            throw new IOException("Falha na serialização para JSON", e);
        }
    }

    public static List<Filial> carregarFiliaisObjeto(Path caminhoArquivo) throws IOException {
        Objects.requireNonNull(caminhoArquivo, "Caminho do arquivo não pode ser nulo");
        
        if (!Files.exists(caminhoArquivo)) {
            throw new FileNotFoundException("Arquivo não encontrado: " + caminhoArquivo);
        }

        Type filialListType = new TypeToken<List<Filial>>(){}.getType();
        try (Reader reader = Files.newBufferedReader(caminhoArquivo)) {
            return gson.fromJson(reader, filialListType);
        } catch (JsonSyntaxException e) {
            throw new IOException("JSON inválido no arquivo: " + caminhoArquivo, e);
        }
    }
    
    public static String paraJson(List<Filial> filiais) {
        return gson.toJson(filiais);
    }
    
    public static List<Filial> deJson(String json) throws JsonSyntaxException {

        if (json == null || json.trim().isEmpty()) {
            throw new JsonSyntaxException("JSON não pode ser nulo ou vazio");
        }
        Type filialListType = new TypeToken<List<Filial>>(){}.getType();
        return gson.fromJson(json, filialListType);
    }

    public static void salvarDonoJSON(Path caminhoArquivo, Dono dono) throws IOException {
        Objects.requireNonNull(caminhoArquivo, "Caminho do arquivo não pode ser nulo");
        Objects.requireNonNull(dono, "Objeto Dono não pode ser nulo");
        
        try (Writer writer = Files.newBufferedWriter(caminhoArquivo)) {
            gson.toJson(dono, writer);
        } catch (JsonIOException e) {
            throw new IOException("Falha na serialização do Dono para JSON", e);
        }
    }

    public static Dono carregarDonoObjeto(Path caminhoArquivo) throws IOException {
        Objects.requireNonNull(caminhoArquivo, "Caminho do arquivo não pode ser nulo");
        
        if (!Files.exists(caminhoArquivo)) {
            throw new FileNotFoundException("Arquivo não encontrado: " + caminhoArquivo);
        }

        try (Reader reader = Files.newBufferedReader(caminhoArquivo)) {
            return gson.fromJson(reader, Dono.class);
        } catch (JsonSyntaxException e) {
            throw new IOException("JSON inválido no arquivo: " + caminhoArquivo, e);
        }
    }

}