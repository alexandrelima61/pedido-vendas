/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.pedidovenda.controller;

import com.algaworks.pedidovenda.model.Grupo;
import com.algaworks.pedidovenda.model.Usuario;
import com.algaworks.pedidovenda.repository.Grupos;
import com.algaworks.pedidovenda.repository.Usuarios;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;
import com.algaworks.pedidovenda.util.service.CadastroUsuarioService;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jalima
 */
@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

    private Usuario usuario;

    @Inject
    private Usuarios usuarios;

    @Inject
    private CadastroUsuarioService cadastroUsuarioService;

    private List<Grupo> gruposDisponiveis;

    @Inject
    private Grupos grupos;

    private Grupo novoGrupo;

    public CadastroUsuarioBean() {
        limpar();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    private void limpar() {
        setUsuario(new Usuario());
    }

    /**
     * @return the novoGrupo
     */
    public Grupo getNovoGrupo() {
        return novoGrupo;
    }

    /**
     * @param novoGrupo the novoGrupo to set
     */
    public void setNovoGrupo(Grupo novoGrupo) {
        this.novoGrupo = novoGrupo;
    }

    public List<Grupo> getGruposDisponiveis() {
        return gruposDisponiveis;
    }

    public void setGruposDisponiveis(List<Grupo> gruposDisponiveis) {
        this.gruposDisponiveis = gruposDisponiveis;
    }

    public void salvar() {
        this.usuario.setSenha(CadastroUsuarioService.md5(this.usuario.getSenha()));
        this.usuario = cadastroUsuarioService.salvar(usuario);
        limpar();
        FacesUtil.addInfoMessage("Usuário cadastrado com sucesso!");
    }

    public void adicionarGrupo() {
        this.usuario.getGrupos().add(novoGrupo);
    }

    public boolean isEditando() {
        return this.usuario.getId() != null;
    }

    public void inicializar() {
        if (FacesUtil.isNotPostback()) {
            gruposDisponiveis = grupos.todos();
        }
    }

}
