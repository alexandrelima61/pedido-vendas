/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.pedidovenda.repository;

import java.io.Serializable;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import com.algaworks.pedidovenda.model.Cliente;
import com.algaworks.pedidovenda.model.Endereco;
import com.algaworks.pedidovenda.repository.filter.ClienteFilter;
import com.algaworks.pedidovenda.util.jpa.Transactional;
import com.algaworks.pedidovenda.util.service.NegocioException;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class Clientes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    public Cliente guardar(Cliente cliente) {
        return manager.merge(cliente);
    }

    public Endereco guardarEndereco(Endereco endereco){
        return manager.merge(endereco);
    }
    
    @Transactional
    public void remover(Cliente cliente) {
        try {
            cliente = porId(cliente.getId());
            manager.remove(cliente);
            manager.flush();
        } catch (PersistenceException e) {
            throw new NegocioException("Cliente não pode ser excluído.");
        }
    }

    @Transactional
    public void removerEnd(Endereco endereco) {
        try {
            endereco = porIdEnd(endereco.getId());
            manager.remove(endereco);
            manager.flush();
        } catch (PersistenceException e) {
            throw new NegocioException("Cliente não pode ser excluído.");
        }
    }

    public List<Cliente> clientesFiltrados(ClienteFilter filtro) {
        Session session = manager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Cliente.class);

        if (StringUtils.isNotBlank(filtro.getNome())) {
//        criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
            criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
        }

        if (StringUtils.isNotBlank(filtro.getDocumentoReceitaFederal())) {

            criteria.add(Restrictions.ilike("documentoReceitaFederal",
                    filtro.getDocumentoReceitaFederal(), MatchMode.ANYWHERE));
        }
        return criteria.addOrder(Order.asc("nome")).list();
    }

    @SuppressWarnings("JPQLValidation")
    public Cliente porNomePesq(String nome) {
        try {
            return manager.createQuery("from Cliente where upper(nome) = :nome", Cliente.class)
                    .setParameter("nome", nome.toUpperCase())
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @SuppressWarnings("JPQLValidation")
    public List<Cliente> porNome(String nome) {
        return this.manager.createQuery("from Cliente "
                + "where upper(nome) like :nome", Cliente.class)
                .setParameter("nome", nome.toUpperCase() + "%")
                .getResultList();
    }

    public Cliente porId(Long id) {
        return manager.find(Cliente.class, id);
    }

    public Endereco porIdEnd(Long id) {
        return manager.find(Endereco.class, id);
    }
}
