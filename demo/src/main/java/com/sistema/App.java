package com.sistema;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sistema.contrato.GsonUtil;
import com.sistema.pessoa.Dono;


/**
 * Hello world!
 *
 */
public class App {

    public static void main( String[] args ) {

        System.out.println( "\n\n" );
        
        
        Path path= Paths.get("").toAbsolutePath();
        path= path.resolve(Paths.get("demo", "src", "main", "arquivosPermanencia"));
        System.out.println("path: " + path);

        GsonUtil gsonUtil = new GsonUtil();
        Dono dono= null;
        try{
        dono= GsonUtil.carregarDonoObjeto(path.resolve("dono.json"));
        }catch(Exception e){
            System.out.println("Erro ao carregar o dono: " + e.getMessage());
        }
        dono.indentificador_pessoa();

        System.out.println( "\n\n" );
        
        

        

        
    }
}
