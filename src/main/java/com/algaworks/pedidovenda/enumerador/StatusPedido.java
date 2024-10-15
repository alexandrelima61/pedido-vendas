/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.pedidovenda.enumerador;

/**
 *
 * @author jalima
 */
public enum StatusPedido {

    ORCAMENTO("Orçamento"), 
    EMITIDO("Emitido"), 
    CANCELADO("Cancelado");

    private String descricao;

    private StatusPedido(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
