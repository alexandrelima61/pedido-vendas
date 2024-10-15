/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.pedidovenda.repository.filter;

import com.algaworks.pedidovenda.validation.SKU;
import java.io.Serializable;

/**
 *
 * @author jalima
 */
public class ProdutoFilter implements Serializable {

    private String sku;
    private String nome;

    @SKU
    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku == null ? null : sku.toUpperCase();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
