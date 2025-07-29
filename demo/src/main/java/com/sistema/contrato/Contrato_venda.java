package com.sistema.contrato;

import com.sistema.pessoa.Vendedor;

public  class Contrato_venda extends Contrato {
    

    public Contrato_venda(){}; //construtor vazio para Gson
    public Contrato_venda(String comprador, Vendedor vendedor, String produto, double preco) {
        super(comprador, vendedor, produto, preco);
    }

    

   
}
