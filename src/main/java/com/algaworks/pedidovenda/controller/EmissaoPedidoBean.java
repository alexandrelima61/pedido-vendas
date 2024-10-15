/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.pedidovenda.controller;

import com.algaworks.pedidovenda.model.Pedido;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;
import com.algaworks.pedidovenda.util.service.EmissaoPedidoService;
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
public class EmissaoPedidoBean implements Serializable {


	private static final long serialVersionUID = 1L;

	@Inject
    private EmissaoPedidoService emissaoPedidoService;
    
    @Inject
    @PedidoEdicao
    private Pedido pedido;
    
    @Inject
    private Event<PedidoAlteradoEvent> pedidoAlteradoEvent;

    public void emitirPedido() {
        this.pedido.removerItemVazio();
        try{
            this.pedido = emissaoPedidoService.emitir(this.pedido);
            //lançar evento CDI            
            this.pedidoAlteradoEvent.fire(new PedidoAlteradoEvent(pedido));
            
            FacesUtil.addInfoMessage("Pedido emitido com sucesso!");
        }finally{
            this.pedido.adicionarItemVazio();
        }
            
                
    }
}
