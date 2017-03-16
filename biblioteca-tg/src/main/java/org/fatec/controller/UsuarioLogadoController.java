package org.fatec.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.fatec.model.TipoUsuario;
import org.fatec.model.Usuario;

@Named
@SessionScoped
public class UsuarioLogadoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7624882497701448506L;

	private Usuario usuario;

	public void logar(Usuario usuario) {
		this.usuario = usuario;
	}

	public void deslogar() {
		this.usuario = null;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public boolean isLogado() {
		return usuario != null;
	}

	public boolean isAdministrador() {
		return usuario != null && usuario.getTipoUsuario() == TipoUsuario.ADMINISTRADOR;
	}

}
