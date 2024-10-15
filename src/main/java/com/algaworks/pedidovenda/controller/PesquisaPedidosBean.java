/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.pedidovenda.controller;

import com.algaworks.pedidovenda.enumerador.StatusPedido;
import com.algaworks.pedidovenda.model.Pedido;
import com.algaworks.pedidovenda.repository.Pedidos;
import com.algaworks.pedidovenda.repository.filter.PedidoFilter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class PesquisaPedidosBean implements Serializable {

    @Inject
    private Pedidos pedidos;

    private PedidoFilter filtro;
    private List<Pedido> pedidosFiltrados;

    public PesquisaPedidosBean() {
        filtro = new PedidoFilter();
        pedidosFiltrados = new ArrayList<>();
    }

    public void pesquisar() {
        pedidosFiltrados = pedidos.filtrados(filtro);
    }

    public List<Pedido> getPedidosFiltrados() {
        return pedidosFiltrados;
    }

    public StatusPedido[] getStatuses() {
        return StatusPedido.values();
    }

    public PedidoFilter getFiltro() {
        return filtro;
    }

}
