/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.algaworks.pedidovenda.util.service;

import com.algaworks.pedidovenda.model.Usuario;
import com.algaworks.pedidovenda.repository.Usuarios;
import com.algaworks.pedidovenda.util.jpa.Transactional;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author FamilaLimaFeitoza
 */
public class CadastroUsuarioService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Usuarios usuarios;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        Usuario usuarioExistente = usuarios.porEmail(usuario.getEmail());

        if (usuarioExistente != null && !usuarioExistente.equals(usuario)) {
            throw new NegocioException("Já existe um usuário com o E-mail informado.");
        }

        return usuarios.guardar(usuario);
    }

    @SuppressWarnings("null")
    public static String md5(String input) {
        String md5 = null;
        if (!StringUtils.isNotBlank(input)) {
            return null;
        }
        try {
            //Create MessageDigest object for MD5           
            MessageDigest digest = MessageDigest.getInstance("MD5");
            //Update input string in message digest           
            digest.update(input.getBytes(), 0, input.length());
            //Converts message digest value in base 16 (hex)            
            md5 = new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
        }
        return md5.trim();
    }

}
