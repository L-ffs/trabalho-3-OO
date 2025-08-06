package com.sistema.validadores;

import java.util.stream.IntStream;

import com.sistema.Gerenciador_sistema;
import com.sistema.franquia.Franquia;
import com.sistema.graficos.User;
import com.sistema.pessoa.Pessoa;

//202465558C Wesley Santos de Lima
//202465505B Luiz Fernando Ferreira Silva

public class Validar {
    
    //public static boolean

    public static boolean senha(String senha) {

        return senha != null && senha.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,}$");
    }

    public static boolean email(String email) {
        
        return email != null && email.matches(".+@.+\\..+");
    }

    public static boolean dadosLogin(String email, String senha, Franquia franquia) {

        
        boolean emailExiste= franquia.Email_existe(email);
        Pessoa pessoa= emailExiste ? franquia.getPessoaPorEmail(email) : null;
        boolean logavel= emailExiste && pessoa.getSenha().equals(senha);

        if (logavel) {
                return true;
            }
        return false;
    }

    public static boolean CPF(String cpf) { //modelo simplificado

        String CPF = cpf.replaceAll("\\D", "");
        if (CPF.length() != 11 || CPF.matches("(\\d)\\1{10}")) return false;
        return true;
        /*  
        // Verifica tamanho e dígitos repetidos
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) return false;
        
        // Calcula primeiro dígito
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int digito1 = (soma % 11) < 2 ? 0 : 11 - (soma % 11);
        
        // Calcula segundo dígito
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int digito2 = (soma % 11) < 2 ? 0 : 11 - (soma % 11);
        
        return (Character.getNumericValue(cpf.charAt(9)) == digito1) && 
            (Character.getNumericValue(cpf.charAt(10)) == digito2);
        */
        // Verifica se tem 11 dígitos e não é uma sequência repetida
    }

    public static boolean nome(String nome) {
        return nome != null && !nome.trim().isEmpty();
    }
    
    public static boolean idade(int idade) {
        return idade >= 18 && idade <= 120;
    }



}
