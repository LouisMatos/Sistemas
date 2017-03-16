package org.fatec.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

import org.fatec.controller.UsuarioLogadoController;

public class Autorizador implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3432031879361978053L;

	@Inject
	private UsuarioLogadoController usuarioLogado;

	@Inject
	private FacesContext context;

	@Inject
	private NavigationHandler handler;

	@Override
	public void afterPhase(PhaseEvent event) {
		if ("/login.xhtml".equals(context.getViewRoot().getViewId())) {
			return;
		}
		if (!usuarioLogado.isLogado()) {
			handler.handleNavigation(context, null, "login?faces-redirect=true");
			context.renderResponse();
			return;
		}
		if (!usuarioLogado.isAdministrador()) {
			if(!"/pesquisa.xhtml".equals(context.getViewRoot().getViewId()))
			
			handler.handleNavigation(context, null, "pesquisa?faces-redirect=true");
			context.renderResponse();
			return;
		}
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
