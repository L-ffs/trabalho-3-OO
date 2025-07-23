package com.sistema.validadores;

public class Validar {
    
    //public static boolean

    public static boolean senha(String senha) {

        return senha != null && senha.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,}$");
    }

    public static boolean email(String email) {
        
        return email != null && email.matches(".+@.+\\..+");
    }
}
