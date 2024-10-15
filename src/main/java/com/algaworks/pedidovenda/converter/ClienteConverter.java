/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.pedidovenda.converter;

import com.algaworks.pedidovenda.model.Cliente;
import com.algaworks.pedidovenda.repository.Clientes;
import com.algaworks.pedidovenda.util.cdi.CDIServiceLocator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author jalima
 */
@FacesConverter(forClass = Cliente.class)
public class ClienteConverter implements Converter {

    private Clientes clientes;

    public ClienteConverter() {
      //usuarios = CDIServiceLocator.getBean(Usuarios.class);
        clientes = CDIServiceLocator.getBean(Clientes.class);
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Cliente retorno = null;

        if (string != null) {
            Long id = new Long(string);
            retorno = clientes.porId(id);

        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if(o != null){
            Cliente cliente =(Cliente) o;
            //return usuario.getId() == null ? null : usuario.getId().toString();
            return cliente.getId() == null ? null: cliente.getId().toString();
        }
        return "";
    }

}
