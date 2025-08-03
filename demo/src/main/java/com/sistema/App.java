package com.sistema;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import com.sistema.Gson.GsonUtil;
import com.sistema.franquia.filial.Filial;
import com.sistema.graficos.Grafico;
import com.sistema.graficos.telas.TelaCriarEditarPessoa;
import com.sistema.graficos.telas.TelaPrincipalDono;
import com.sistema.pessoa.Dono;
import com.sistema.pessoa.Gerente;
import com.sistema.pessoa.Pessoa;
import com.sistema.pessoa.Vendedor;
import com.sistema.validadores.Validar;



public class App {

    public static void main( String[] args ) {

        
        System.out.println( "\n\n" ); //espaçamento
        
        //Grafico graficos = new Grafico();
        
        //TelaCriarEditarPessoa.CriarPessoa(new Dono(), null);
        //graficos.tela_principal_gerente();
        
        Gerenciador_sistema sistema = new Gerenciador_sistema();
        sistema.Start_sistema(); //inicia o sistema


        /*
        Dono carlos= new Dono("carlos", "34523523", 32, "car@gmail.com", "Aa1234");
        Dono vazio= new Dono();
        System.out.println("carlos: " + carlos.vazio());
        System.out.println("vazio :" + vazio.vazio());
        */


        System.out.println( "\n\n" ); //espaçamento
        
        

        

        
    }
}
