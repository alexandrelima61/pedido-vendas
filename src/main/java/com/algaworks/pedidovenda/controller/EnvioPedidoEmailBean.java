/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.pedidovenda.controller;

import com.algaworks.pedidovenda.model.Pedido;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;
import com.algaworks.pedidovenda.util.mail.Mailer;
import com.outjected.email.api.MailMessage;
import com.outjected.email.impl.templating.velocity.VelocityTemplate;

import java.io.Serializable;
import java.util.Locale;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.velocity.tools.generic.NumberTool;

/**
 *
 * @author jalima
 */
@Named
@RequestScoped
public class EnvioPedidoEmailBean implements Serializable {

    @Inject
    private Mailer mailer;

    @Inject
    @PedidoEdicao
    private Pedido pedido;

    public void enviarPedido() {
        MailMessage message = mailer.novaMessage();

        message.to(this.pedido.getCliente().getEmail())
                .subject("Pedido " + this.pedido.getId())
                .bodyHtml(new VelocityTemplate(getClass().getResourceAsStream("/email/pedido.template")))
                .put("pedido", this.pedido)
                .put("numberTool", new NumberTool())
                .put("locale", new Locale("pt", "BR"))
                .charset("iso-8859-1")
                .send();

        FacesUtil.addInfoMessage("Pedido enviado por e-mail com sucesso!");
    }

}
