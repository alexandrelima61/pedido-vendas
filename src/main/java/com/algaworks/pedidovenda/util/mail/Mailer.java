/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.algaworks.pedidovenda.util.mail;

import com.outjected.email.api.MailMessage;
import com.outjected.email.api.SessionConfig;
import com.outjected.email.impl.MailMessageImpl;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class Mailer implements Serializable{
    
    private static final long  serialVersionUID = 1L;
    
    @Inject
    private SessionConfig config;
    
    public MailMessage novaMessage(){
      return new MailMessageImpl(this.config);
    } 
}
