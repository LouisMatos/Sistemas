package org.fatec.controller;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.fatec.dao.UsuarioDao;
import org.fatec.model.TipoUsuario;
import org.fatec.model.Usuario;
import org.slf4j.Logger;

@Model
public class UsuarioController {

	@Inject
	private FacesContext facesContext;
	@Inject
	private Logger logger;
	@Inject
	private UsuarioDao dao;

	private Usuario usuario = new Usuario();
	private List<Usuario> usuarios;

	public List<Usuario> getUsuarios() {
		if (this.usuarios == null) {
			usuarios = dao.buscaTodos();
		}
		return usuarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getTituloAba1() {
		if (usuario.getId() != null) {
			return "Edicao";
		} else {
			return "Cadastro";
		}
	}

	public String grava() {
		try {
			if (usuario.getSenha().equals(usuario.getConfirmacaoSenha())) {
				if (usuario.getTipoUsuario() == null) {
					usuario.setTipoUsuario(TipoUsuario.COMUM);
				}

				if (usuario.getId() != null) {
					dao.atualiza(usuario);
				} else {
					dao.adiciona(usuario);
				}
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful");
				facesContext.addMessage(null, m);
				logger.info(m.getDetail());

			} else {
				String errorMessage = "Senhas não batem...";
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage,
						"Registration unsuccessful");
				facesContext.addMessage(null, m);
				logger.error(errorMessage);

				return "/usuario.xhtml?faces-redirect=false";
			}
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
			facesContext.addMessage(null, m);
			logger.error(errorMessage);
		}

		usuarios = dao.buscaTodos();

		return "/usuario.xhtml?faces-redirect=true";
	}

	public String cancela() {
		return "/usuario.xhtml?faces-redirect=true";

	}

	public String remove(Usuario usuarioASerRemovido) {
		dao.remove(usuarioASerRemovido);

		return "/usuario.xhtml?faces-redirect=true";
	}

	private String getRootErrorMessage(Exception e) {
		// Default to general error message that registration failed.
		String errorMessage = "Falha ao registrar um usuario. Veja o log do servidor para mais detalhes";
		if (e == null) {
			// This shouldn't happen, but return the default messages
			return errorMessage;
		}

		// Start with the exception and recurse to find the root cause
		Throwable t = e;
		while (t != null) {
			// Get the message from the Throwable class instance
			errorMessage = t.getLocalizedMessage();
			t = t.getCause();
		}
		// This is the root cause message
		return errorMessage;
	}

	public SelectItem[] getTipoUsuarioValues() {
		SelectItem[] items = new SelectItem[TipoUsuario.values().length];
		int i = 0;
		for (TipoUsuario u : TipoUsuario.values()) {
			items[i++] = new SelectItem(u, u.name());
		}
		return items;
	}

}
