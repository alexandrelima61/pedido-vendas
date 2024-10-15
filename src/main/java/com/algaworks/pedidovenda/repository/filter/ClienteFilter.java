/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.pedidovenda.repository.filter;

import java.io.Serializable;

/**
 *
 * @author jalima
 */
public class ClienteFilter implements Serializable {

    private String nome;
    private String documentoReceitaFederal;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumentoReceitaFederal() {
        return documentoReceitaFederal;
    }

    public void setDocumentoReceitaFederal(String documentoReceitaFederal) {
        this.documentoReceitaFederal = documentoReceitaFederal == null ? null : documentoReceitaFederal;
    }

}
