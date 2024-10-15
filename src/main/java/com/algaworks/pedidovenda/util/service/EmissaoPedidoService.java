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
public class EmissaoPedidoService implements Serializable {

    @Inject
    private CadastroPedidoService cadastroPedidoService;

    @Inject
    private EstoqueService estoqueService;

    @Inject
    private Pedidos pedidos;

    @Transactional
    @SuppressWarnings("UnusedAssignment")
    public Pedido emitir(Pedido pedido) {
        pedido = this.cadastroPedidoService.salvar(pedido);

        if (pedido.isNaoEmissivel()) {
            throw new NegocioException("Pedido não ser emitido com o status "
                    + pedido.getStatus().getDescricao() + ".");
        }

        this.estoqueService.baixarItensEstoque(pedido);

        pedido.setStatus(StatusPedido.EMITIDO);

        pedido = this.pedidos.guardar(pedido);

        return pedido;
    }

}
