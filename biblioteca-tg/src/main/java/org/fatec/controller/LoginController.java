package org.fatec.controller;

import java.io.Serializable;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.fatec.dao.UsuarioDao;
import org.fatec.model.TipoUsuario;
import org.fatec.model.Usuario;

@Model
public class LoginController implements Serializable {

	private static final long serialVersionUID = -4711066483117858009L;

	@Inject
	private UsuarioLogadoController usuarioLogadoController;

	@Inject
	Event<Usuario> eventoLogin;

	private Usuario usuario = new Usuario();

	@Inject
	private UsuarioDao dao;

	public String efetuaLogin() {
		boolean loginValido = dao.existe(this.usuario);

		if (loginValido) {
			eventoLogin.fire(usuario);
			usuarioLogadoController.logar(usuario);
			if (usuario.getTipoUsuario() == TipoUsuario.ADMINISTRADOR) {
				return "aluno?faces-redirect=true";
			} else {
				return "pesquisa?faces-redirect=true";
			}
		} else {
			usuarioLogadoController.deslogar();
			this.usuario = new Usuario();
			return "login?faces-redirect=true";
		}

	}

	public String logout() {
		if (usuarioLogadoController.isLogado()) {
			usuarioLogadoController.deslogar();
		}

		return "login?faces-redirect=true";

	}

	public Usuario getUsuario() {
		return this.usuario;
	}

}
