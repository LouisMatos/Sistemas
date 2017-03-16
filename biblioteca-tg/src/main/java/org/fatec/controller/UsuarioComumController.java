package org.fatec.controller;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.fatec.dao.UsuarioDao;
import org.fatec.model.TipoUsuario;
import org.fatec.model.Usuario;
import org.slf4j.Logger;

@Model
public class UsuarioComumController {

	@Inject
	private FacesContext facesContext;
	@Inject
	private Logger logger;
	@Inject
	private UsuarioDao dao;

	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String grava() {
		try {
			if (usuario.getSenha().equals(usuario.getConfirmacaoSenha())) {

				usuario.setTipoUsuario(TipoUsuario.COMUM);
				if (!dao.existeEmail(usuario)) {
					dao.adiciona(usuario);
				} else {
					String errorMessage = "E-mail já cadastrado...";
					FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage,
							"Registration unsuccessful");
					facesContext.addMessage(null, m);
					logger.error(errorMessage);

					return "/login.xhtml?faces-redirect=false";
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

				return "/login.xhtml?faces-redirect=false";

			}

		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
			facesContext.addMessage(null, m);
			logger.error(errorMessage);
		}

		return "/login.xhtml?faces-redirect=true";
	}

	public String cancela() {
		return "/login.xhtml?faces-redirect=true";

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

}
