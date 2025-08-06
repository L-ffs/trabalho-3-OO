package com.sistema;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.sistema.validadores.Validar;

//202465558C Wesley Santos de Lima
//202465505B Luiz Fernando Ferreira Silva

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testSenha() {
        assertTrue(Validar.senha("Abc123"));         // válido
        assertFalse(Validar.senha("abc123"));        // sem maiúscula
        assertFalse(Validar.senha("ABC123"));        // sem minúscula
        assertFalse(Validar.senha("Abcde"));         // sem número
    }

    @Test
    public void testEmail() {
        assertTrue(Validar.email("teste@email.com")); // válido
        assertFalse(Validar.email("testeemail.com")); // sem @
        assertFalse(Validar.email("teste@com"));      // sem ponto
        assertFalse(Validar.email(null));             // nulo
    }

    @Test
    public void testCPF() {
        assertTrue(Validar.CPF("12345678909"));       // válido (formato)
        assertFalse(Validar.CPF("11111111111"));      // repetido
        assertFalse(Validar.CPF("1234567890"));       // menos de 11 dígitos
        assertFalse(Validar.CPF("abc45678909"));      // contém letras
    }

    @Test
    public void testNome() {
        assertTrue(Validar.nome("João"));             // válido
        assertFalse(Validar.nome(""));                // vazio
        assertFalse(Validar.nome("   "));             // espaços
        assertFalse(Validar.nome(null));              // nulo
    }

    @Test
    public void testIdade() {
        assertTrue(Validar.idade(18));                // mínimo válido
        assertTrue(Validar.idade(50));                // valor intermediário
        assertFalse(Validar.idade(17));               // abaixo do mínimo
        assertFalse(Validar.idade(121));              // acima do máximo
    }

}
