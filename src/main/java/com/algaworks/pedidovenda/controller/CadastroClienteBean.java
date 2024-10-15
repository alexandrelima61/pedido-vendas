/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.pedidovenda.controller;

import com.algaworks.pedidovenda.enumerador.TipoPessoa;
import com.algaworks.pedidovenda.enumerador.TipoPessoaCliente;
import com.algaworks.pedidovenda.enumerador.masktipoPessoa;
import com.algaworks.pedidovenda.model.Cliente;
import com.algaworks.pedidovenda.model.Endereco;
import com.algaworks.pedidovenda.repository.Clientes;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;
import com.algaworks.pedidovenda.util.service.CadastroClienteService;
import java.io.Serializable;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jalima
 */
@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {

    @Inject
    private CadastroClienteService cadastroClienteService;

    @Inject
    private Clientes clientes;

    private Endereco enderecoSelecionado;

    private int tipo = 1;
    private Cliente cliente;
    private Endereco endereco;

    private String cnpjcpf = TipoPessoaCliente.CPF.toString();
    private String mascara = masktipoPessoa.MaskCpf.getDado();

    public CadastroClienteBean() {
        limpar();
    }

    public void limpar() {
        cliente = new Cliente();
        endereco = new Endereco();
        //cadastroClienteService = new CadastroClienteService();
    }

    public void defineTipoCliente() {
        if (getTipo() == 1) {
            cnpjcpf = TipoPessoaCliente.CPF.toString();
            mascara = masktipoPessoa.MaskCpf.getDado();
        } else if (getTipo() == 2) {
            cnpjcpf = TipoPessoaCliente.CNPJ.toString();
            mascara = masktipoPessoa.MaskCnpj.getDado();
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void salvar() {

        if (getTipo() == 1) {
            cliente.setTipo(TipoPessoa.FISICA);
        } else {
            cliente.setTipo(TipoPessoa.JURIDICA);
        }

        cliente = cadastroClienteService.salvar(cliente);
        limpar();

        FacesUtil.addInfoMessage("Cliente salvo com sucesso");
    }

    public void excluir() {
        cliente.getEnderecos().remove(enderecoSelecionado);

        clientes.removerEnd(enderecoSelecionado);

        FacesUtil.addInfoMessage("Endereço " + enderecoSelecionado.getLogradouro()
                + " " + enderecoSelecionado.getNumero()
                + ", excluído com sucesso.");
    }

    public void carregarEnderecoCliente() {

        if (endereco.isEnderecoNovo()) {
            cliente.getEnderecos().add(endereco);
            endereco.setCliente(cliente);

            //FacesUtil.addInfoMessage("Endereço salvo com sucesso");
        } else {
            FacesUtil.addInfoMessage("Endereço alterado com sucesso");
        }

        this.endereco = new Endereco();

    }

    public int getTipo() {
        return tipo;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
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

    public boolean isEditando() {
        return this.cliente.getId() != null;
    }

    public Endereco getEnderecoSelecionado() {
        return enderecoSelecionado;
    }

    public void setEnderecoSelecionado(Endereco enderecoSelecionado) {
        this.enderecoSelecionado = enderecoSelecionado;
    }

    public void inicializarCliente() {
        if (FacesUtil.isNotPostback()) {
            System.out.println("inicializando o pre-render...");
        }
    }

}
