package com.sistema;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sistema.franquia.filial.Filial;
import com.sistema.graficos.GsonUtil;
import com.sistema.pessoa.Dono;
import com.sistema.pessoa.Gerente;
import com.sistema.pessoa.Vendedor;


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
        
        Vendedor vendedor= new Vendedor("joao", "09876543211", 31, "vendedor@vendedor.com", "Aa1234");
        ArrayList<Vendedor> vendedores = new ArrayList<>();
        vendedores.add(vendedor);
        Gerente gerente= new Gerente("roberto", "12345678900", 28, "gerente@gerente.com", "Aa1234");
        Filial filial= new Filial("Rua A, 123", gerente, vendedores, null, null);
        ArrayList<Filial> filiais = new ArrayList<>();
        filiais.add(filial);
        try {
            GsonUtil.salvarFiliaisJSON(path.resolve("filiais.json"),filiais);
            
        } catch (Exception e) {
            // TODO: handle exception
        }

        System.out.println( "\n\n" );
        
        

        

        
    }
}
