package com.sistema.contrato;

public class Contrato_compra extends Contrato {
    

    public Contrato_compra(){}; //construtor vazio para Gson
    public Contrato_compra(String comprador, String vendedor, String produto, double preco) {
        super(comprador, vendedor, produto, preco);
    }

    


}
