/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.pedidovenda.controller;

import com.algaworks.pedidovenda.model.Endereco;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author jalima
 */
@Named
@ViewScoped
public class PesquisaEndereco implements Serializable {

    private final List<Endereco> enderecosFiltrados = new ArrayList<>();
    private Endereco endereco = new Endereco();
    private Endereco enderecoSelecionado;

    public PesquisaEndereco() {

    }

    public void DeletarEndereco() {
        enderecosFiltrados.remove(enderecoSelecionado);
    }

    public void IncluirEndereco() {

    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Endereco> getEnderecosFiltrados() {
        return enderecosFiltrados;
    }

    public Endereco getEnderecoSelecionado() {
        return enderecoSelecionado;
    }

    public void setEnderecoSelecionado(Endereco enderecoSelecionado) {
        this.enderecoSelecionado = enderecoSelecionado;
    }

}
