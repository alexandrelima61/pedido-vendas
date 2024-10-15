/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.pedidovenda.repository;

import com.algaworks.pedidovenda.model.Produto;
import com.algaworks.pedidovenda.repository.filter.ProdutoFilter;
import com.algaworks.pedidovenda.util.jpa.Transactional;
import com.algaworks.pedidovenda.util.service.NegocioException;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author FamilaLimaFeitoza
 */
public class Produtos implements Serializable {

    @Inject
    private EntityManager manager;

    @SuppressWarnings("UnusedAssignment")
    public Produto guardar(Produto produto) {
        return manager.merge(produto);
    }

    @Transactional
    @SuppressWarnings("UnusedAssignment")
    public void remover(Produto produto) {
        try {
            produto = porId(produto.getId());
            manager.remove(produto);

            manager.flush();
        } catch (PersistenceException e) {
            throw new NegocioException("Produto não pode ser excluído");
        }
    }

    @SuppressWarnings("JPQLValidation")
    public Produto porSku(String sku) {
        try {
            return manager.createQuery("from Produto where upper(sku) = :sku", Produto.class)
                    .setParameter("sku", sku.toUpperCase())
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Produto> filtrados(ProdutoFilter filtro) {
        Session session = manager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Produto.class);

        if (StringUtils.isNotBlank(filtro.getSku())) {
            criteria.add(Restrictions.eq("sku", filtro.getSku()));
        }
        if (StringUtils.isNotBlank(filtro.getNome())) { //MatchMode.ANYWHERE seria o like desta forma '%exemplo%'       
            criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
        }

        return criteria.addOrder(Order.asc("nome")).list();
    }

    public Produto porId(Long id) {
        return manager.find(Produto.class, id);
    }

    @SuppressWarnings("JPQLValidation")
    public List<Produto> porNome(String nome) {
        return manager.createQuery("from Produto where upper(nome) like :nome", Produto.class)
                .setParameter("nome", nome.toUpperCase() + "%")
                .getResultList();
    }
}
