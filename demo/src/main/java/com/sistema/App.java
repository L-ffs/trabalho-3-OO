package com.sistema;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import com.sistema.Gson.GsonUtil;
import com.sistema.franquia.filial.Filial;
import com.sistema.graficos.Grafico;
import com.sistema.pessoa.Dono;
import com.sistema.pessoa.Gerente;
import com.sistema.pessoa.Vendedor;



public class App {

    public static void main( String[] args ) {

        System.out.println( "\n\n" );
        
        Gerenciador_sistema sistema = new Gerenciador_sistema();
        sistema.Start_sistema(); //inicia o sistema
        

        System.out.println( "\n\n" );
        
        

        

        
    }
}
