/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.pedidovenda.util.service;

/**
 *
 * @author jalima
 */
public class NegocioException extends RuntimeException {

    public NegocioException(String msg) {
        super(msg);
    }

    NegocioException() {
        //To change body of generated methods, choose Tools | Templates.
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
