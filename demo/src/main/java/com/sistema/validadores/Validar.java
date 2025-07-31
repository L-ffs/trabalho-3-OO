package com.sistema.validadores;

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
}
