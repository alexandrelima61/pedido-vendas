package com.algaworks.pedidovenda.converter;

import com.algaworks.pedidovenda.model.Pedido;
import com.algaworks.pedidovenda.repository.Pedidos;
import com.algaworks.pedidovenda.util.cdi.CDIServiceLocator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Pedido.class)
public class PedidoConverter implements Converter {

//@Inject
    private Pedidos pedidos;

    public PedidoConverter() {
        pedidos = CDIServiceLocator.getBean(Pedidos.class);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Pedido retorno = null;

        if (value != null) {
            Long id = new Long(value);
            retorno = pedidos.porId(id);
        }

        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Pedido produto = (Pedido) value;
            return produto.getId() == null ? null : produto.getId().toString();
        }

        return "";
    }

}
