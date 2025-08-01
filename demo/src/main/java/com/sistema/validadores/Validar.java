package com.sistema.validadores;

import java.util.stream.IntStream;

import com.sistema.Gerenciador_sistema;
import com.sistema.franquia.Franquia;
import com.sistema.graficos.User;
import com.sistema.pessoa.Pessoa;

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

    public static boolean CPF(String cpf) {

        /* 
        if (CPF.length() != 11 || CPF.matches("(\\d)\\1{10}")) return false;

        return IntStream.range(0, 2).allMatch(i -> 
        CPF.charAt(9 + i) - '0' == (IntStream.range(0, 9 + i)
        .map(j -> (CPF.charAt(j) - '0') * (10 + i - j)).sum() % 11 % 10));
        */
        String CPF = cpf.replaceAll("\\D", "");
        // Verifica se tem 11 dígitos e não é uma sequência repetida
        if (CPF.length() != 11 || CPF.matches("(\\d)\\1{10}")) return false;
        return true;
    }

    public static boolean nome(String nome) {
        return nome != null && !nome.trim().isEmpty();
    }
    
    public static boolean idade(int idade) {
        return idade >= 18 && idade <= 120;
    }



}
