/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.pedidovenda.model.Categoria;

public class Categorias implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    public Categoria porId(Long id) {
        return manager.find(Categoria.class, id);
    }

    @SuppressWarnings("JPQLValidation")
    public List<Categoria> raizes() {
        return manager.createQuery("from Categoria where categoriaPai is null",
                Categoria.class).getResultList();
    }

    @SuppressWarnings("JPQLValidation")
    public List<Categoria> subcategoriasDe(Categoria categoriaPai) {
        return manager.createQuery("from Categoria where categoriaPai = :raiz",
                Categoria.class).setParameter("raiz", categoriaPai).getResultList();
    }

}
