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
import com.sistema.graficos.telas.TelaCriarEditarPessoa;
import com.sistema.graficos.telas.TelaLoginPrincipal;
import com.sistema.pessoa.*;

//202465558C Wesley Santos de Lima
//202465505B Luiz Fernando Ferreira Silva

public class Gerenciador_sistema {

   
    Grafico graficos;
    Franquia franquia;
    GsonUtil gsonUtil;


    public Gerenciador_sistema() {
        gsonUtil = new GsonUtil();
        franquia = new Franquia();
        graficos = new Grafico(franquia);
    }

    public void Start_sistema() {
        System.out.println("Iniciando o sistema...");
        
        

        // Carrega os dados da franquia
        try {

            franquia.setDono(gsonUtil.carregarDonoObjeto());
            franquia.setFiliais(gsonUtil.carregarFiliaisObjeto());
        } catch (IOException e) {

            System.out.println("Erro ao carregar os dados da franquia: " + e.getMessage());
        }catch (Exception e) {

            System.out.println("Erro inesperado ao carregar os dados da franquia: " + e.getMessage());
        }
        franquia.atualiza_funcionarios();
        franquia.inicializaEstruturasfiliais();

        //inicia os graficos

        if (franquia.getDono() == null || franquia.getDono().vazio()) {
            //ESSENCIAL MANTER ESSA ORDEM DE VALIDAÇAO
            Dono dono= new Dono();
            Runnable salvarDono= () -> franquia.setDono(dono);
            TelaCriarEditarPessoa.CriarPessoa(dono, salvarDono, Grafico::fecharESalvar);
            
        }
        else {
            
            TelaLoginPrincipal.mostrar();
        }

        //TelaLoginPrincipal.mostrar();



    }

    

    
    public Franquia getFranquia() {
        return franquia;
    }

    public static void mostrarNotificacao(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Notificação", JOptionPane.INFORMATION_MESSAGE);
    }

    

    //public void adcioar_filial()
}
