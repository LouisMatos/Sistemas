package org.fatec.controller;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.fatec.dao.CursoDao;
import org.fatec.model.Curso;
import org.fatec.model.TipoPeriodo;
import org.slf4j.Logger;

@Model
@ManagedBean
public class CursoController {

	@Inject
	private FacesContext facesContext;
	@Inject
	private Logger logger;
	@Inject
	private CursoDao dao;

	private Curso curso = new Curso();
	private List<Curso> cursos;

	public List<Curso> getCursos() {
		if (this.cursos == null) {
			cursos = dao.buscaTodos();
		}
		return cursos;
	}

	public String getTituloAba1() {
		if (curso.getId() != null) {
			return "Edicao";
		} else {
			return "Cadastro";
		}
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public String grava() {
		try {
			if (curso.getId() != null) {
				dao.atualiza(curso);
			} else {
				dao.adiciona(curso);
			}
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful");
			facesContext.addMessage(null, m);
			logger.info(m.getDetail());
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
			facesContext.addMessage(null, m);
			logger.error(errorMessage);
		}

		cursos = dao.buscaTodos();

		return "/curso.xhtml?faces-redirect=true";
	}

	public String cancela() {
		return "/curso.xhtml?faces-redirect=true";

	}

	public String remove(Curso cursoASerRemovido) {
		dao.remove(cursoASerRemovido);

		return "/curso.xhtml?faces-redirect=true";
	}

	private String getRootErrorMessage(Exception e) {
		// Default to general error message that registration failed.
		String errorMessage = "Falha ao registrar um curso. Veja o log do servidor para mais detalhes";
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

	public SelectItem[] getPeriodoValues() {
		SelectItem[] items = new SelectItem[TipoPeriodo.values().length];
		int i = 0;
		for (TipoPeriodo p : TipoPeriodo.values()) {
			items[i++] = new SelectItem(p, p.name());
		}
		return items;
	}

}
