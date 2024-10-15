/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class PesquisaGrupo implements Serializable {

    private List<Integer> grupoFiltrados;

    public PesquisaGrupo() {
        grupoFiltrados = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            grupoFiltrados.add(i);
        }
    }

    public List<Integer> getGrupoFiltrados() {
        return grupoFiltrados;
    }

}
