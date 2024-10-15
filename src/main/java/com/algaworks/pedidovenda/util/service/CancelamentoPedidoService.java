/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.pedidovenda.util.service;

import com.algaworks.pedidovenda.enumerador.StatusPedido;
import com.algaworks.pedidovenda.model.Pedido;
import com.algaworks.pedidovenda.repository.Pedidos;
import com.algaworks.pedidovenda.util.jpa.Transactional;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author jalima
 */
public class CancelamentoPedidoService implements Serializable {

    @Inject
    private Pedidos pedidos;

    @Inject
    private EstoqueService estoqueService;

    @Transactional
    @SuppressWarnings("UnusedAssignment")
    public Pedido cancelar(Pedido pedido) {
        pedido = this.pedidos.porId(pedido.getId());

        if (pedido.isNaoCancelavel()) {
            throw new NegocioException("Pedido não pode ser cancelado no status "
                    + pedido.getStatus().getDescricao()+ ".");
        }

        if(pedido.isEmitido()){
            this.estoqueService.retornarItensEstoque(pedido);
        }
            
        pedido.setStatus(StatusPedido.CANCELADO);
        
        pedido = this.pedidos.guardar(pedido);
        
        return pedido;
    }

}
