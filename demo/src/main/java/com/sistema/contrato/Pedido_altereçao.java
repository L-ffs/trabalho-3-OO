package com.sistema.contrato;

import com.sistema.pessoa.Pessoa;

public class Pedido_altereçao {
    
    private Pessoa requeridor;
    private Contrato venda;
    private String motivo;


    public Pedido_altereçao() {} // Construtor vazio para o Gson
    public Pedido_altereçao(Pessoa requeridor, Contrato venda, String motivo) {
        this.requeridor = requeridor;
        this.venda = venda;
        this.motivo = motivo;
    }


    public Pessoa getRequeridor() {
        return requeridor;
    }
    public void setRequeridor(Pessoa requeridor) {
        this.requeridor = requeridor;
    }
    public Contrato getVenda() {
        return venda;
    }
    public void setVenda(Contrato venda) {
        this.venda = venda;
    }
    public String getMotivo() {
        return motivo;
    }
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
