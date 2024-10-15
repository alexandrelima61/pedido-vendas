/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.algaworks.pedidovenda.util.service;

import com.algaworks.pedidovenda.model.ItemPedido;
import com.algaworks.pedidovenda.model.Pedido;
import com.algaworks.pedidovenda.repository.Pedidos;
import com.algaworks.pedidovenda.util.jpa.Transactional;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author jalima
 */
public class EstoqueService implements Serializable{
    
    @Inject
    private Pedidos pedidos;
    
    @Transactional
    public void baixarItensEstoque(Pedido pedido){
        pedido = this.pedidos.porId(pedido.getId());
        
        for(ItemPedido item : pedido.getItens()){
            item.getProduto().baixarEstoque(item.getQuantidade());
        }
    }

    public void retornarItensEstoque(Pedido pedido) {
        pedido = this.pedidos.porId(pedido.getId());
        
        for(ItemPedido item : pedido.getItens()){
            item.getProduto().adicionarEstoque(item.getQuantidade());
        }
        
    }
}
