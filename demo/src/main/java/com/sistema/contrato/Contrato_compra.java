package com.sistema.contrato;

import com.sistema.pessoa.Vendedor;

public class Contrato_compra extends Contrato {
    

    public Contrato_compra(){}; //construtor vazio para Gson
    public Contrato_compra(String comprador, Vendedor vendedor, String produto, double preco) {
        super(comprador, vendedor, produto, preco);
    }

    


}
