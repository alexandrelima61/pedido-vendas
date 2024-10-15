/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.pedidovenda.controller;

import com.algaworks.pedidovenda.enumerador.TipoPessoaCliente;
import com.algaworks.pedidovenda.enumerador.masktipoPessoa;
import com.algaworks.pedidovenda.model.Cliente;
import com.algaworks.pedidovenda.repository.Clientes;
import com.algaworks.pedidovenda.repository.filter.ClienteFilter;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class PesquisaClientesBean implements Serializable {

    @Inject
    private Clientes clientes;

    private ClienteFilter filter;

    private List<Cliente> clientesFiltrados;

    private Cliente clienteSelecionado;

    private int tipo = 1;
    private String cnpjcpf = TipoPessoaCliente.CPF.toString();
    private String mascara = masktipoPessoa.MaskCpf.getDado();

    public void defineTipoClienteP() {
        if (getTipo() == 1) {
            cnpjcpf = TipoPessoaCliente.CPF.toString();
            mascara = masktipoPessoa.MaskCpf.getDado();
        } else if (getTipo() == 2) {
            cnpjcpf = TipoPessoaCliente.CNPJ.toString();
            mascara = masktipoPessoa.MaskCnpj.getDado();
        }
    }

    public PesquisaClientesBean() {
        clientesFiltrados = new ArrayList<>();
        filter = new ClienteFilter();
    }

    public void pesquisar() {
        clientesFiltrados = clientes.clientesFiltrados(filter);
    }

    public void excluirCliente() {
        clientes.remover(clienteSelecionado);
        clientesFiltrados.remove(clienteSelecionado);

        FacesUtil.addInfoMessage("Cliente " + clienteSelecionado.getNome()
                + " excluído com sucesso.");
    }

    public List<Cliente> getCleintesFiltrados() {
        return clientesFiltrados;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getCnpjcpf() {
        return cnpjcpf;
    }

    public void setCnpjcpf(String cnpjcpf) {
        this.cnpjcpf = cnpjcpf;
    }

    public String getMascara() {
        return mascara;
    }

    public void setMascara(String mascara) {
        this.mascara = mascara;
    }

    public ClienteFilter getFilter() {
        return filter;
    }

    public Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }

    public void setClienteSelecionado(Cliente clienteSelecionado) {
        this.clienteSelecionado = clienteSelecionado;
    }

}
