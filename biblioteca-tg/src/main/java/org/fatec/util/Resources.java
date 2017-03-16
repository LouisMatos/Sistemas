package org.fatec.util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Resources {

	@Produces
	@PersistenceContext
	private EntityManager em;

	@Produces
	public Logger produceLog(InjectionPoint injectionPoint) {
		return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
	}

	@Produces
	@RequestScoped
	public FacesContext produceFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	@Produces
	@RequestScoped
	public NavigationHandler getNavigationHandler() {
		return produceFacesContext().getApplication().getNavigationHandler();
	}
}
