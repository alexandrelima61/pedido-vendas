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
import java.util.Date;
import javax.inject.Inject;

/**
 *
 * @author FamilaLimaFeitoza
 */
public class CadastroPedidoService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Pedidos pedidos;

    @Transactional
    public Pedido salvar(Pedido pedido) {
        if (pedido.isNovo()) {
            pedido.setDataCriacao(new Date());
            pedido.setStatus(StatusPedido.ORCAMENTO);
        }

        pedido.recalcularValorTotal();

        if (pedido.isNaoAlteravel()) {
            throw new NegocioException("Pedido não pode ser alterado no status "
                    + pedido.getStatus().getDescricao() + ".");
        }

        if (pedido.getItens().isEmpty()) {
            throw new NegocioException("O pedido deve possuir pelo menos um item.");
        }

        if (pedido.isValorTotalNegativo()) {
            throw new NegocioException("Valor total do pedido não pode ser negativo.");
        }

        pedido = this.pedidos.guardar(pedido);
        return pedido;
    }

}
