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
public enum masktipoPessoa {
    MaskCpf("999.999.999-99"),MaskCnpj("99.999.999/9999-99");
    
    private final String dado;

    private masktipoPessoa(String dado) {
        this.dado = dado;
    }

    public String getDado() {
        return dado;
    }

    @Override
    public String toString() {
        return dado;
    }
}
