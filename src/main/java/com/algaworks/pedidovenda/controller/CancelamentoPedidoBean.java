/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.pedidovenda.controller;

import com.algaworks.pedidovenda.model.Pedido;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;
import com.algaworks.pedidovenda.util.service.CancelamentoPedidoService;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jalima
 */
@Named
@RequestScoped
public class CancelamentoPedidoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private CancelamentoPedidoService cancelamentoPedidoService;

    @Inject
    private Event<PedidoAlteradoEvent> pedidoAlteradoEvent;

    @Inject
    @PedidoEdicao
    private Pedido pedido;

    public void cancelarPedido() {
        this.pedido = this.cancelamentoPedidoService.cancelar(this.pedido);
        this.pedidoAlteradoEvent.fire(new PedidoAlteradoEvent(this.pedido));

        FacesUtil.addInfoMessage("Pedido cancelado com sucesso!");
    }
}
