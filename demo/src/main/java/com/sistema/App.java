package com.sistema;
import java.io.FileWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sistema.pessoa.Dono;


/**
 * Hello world!
 *
 */
public class App {

    public static void main( String[] args ) {

        System.out.println( "\n\n" );
        
        Gson gson = new Gson();
        Dono dono = new Dono("joao", "2981298342", 30, "joao@gmail.com", "1234");
        String donoJson = gson.toJson(dono);
        System.out.println("Dono JSON: " + donoJson);

        
        
        try(FileWriter fileWriter = new FileWriter("dono.json")) {
            fileWriter.write(donoJson);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println( "\n\n" );
        

        
    }
}
