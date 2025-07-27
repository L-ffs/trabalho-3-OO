package com.sistema.contrato;

public  class Contrato_venda extends Contrato {
    

    public Contrato_venda(){}; //construtor vazio para Gson
    public Contrato_venda(String comprador, String vendedor, String produto, double preco) {
        super(comprador, vendedor, produto, preco);
    }

    

   
}
