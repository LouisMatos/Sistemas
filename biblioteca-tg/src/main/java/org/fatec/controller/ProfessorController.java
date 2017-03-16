package org.fatec.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.fatec.dao.ProfessorDao;
import org.fatec.model.Professor;
import org.slf4j.Logger;

@ManagedBean
@SessionScoped
public class ProfessorController {

	@Inject
	private FacesContext facesContext;
	@Inject
	private Logger logger;
	@Inject
	private ProfessorDao dao;

	private Professor professor = new Professor();
	private List<Professor> professores;

	public List<Professor> getProfessores() {
		if (this.professores == null) {
			professores = dao.buscaTodos();
		}
		return professores;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	public String getTituloAba1(){
		if(professor.getId() != null){
			return "Edicao";
		}else{
			return "Cadastro";
		}
	}

	public String grava() {
		try {
			if (professor.getId() != null) {
				dao.atualiza(professor);
			} else {
				dao.adiciona(professor);
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

		professores = dao.buscaTodos();

		return "/professor.xhtml?faces-redirect=true";
	}

	public String cancela() {
		return "/professor.xhtml?faces-redirect=true";

	}

	public String remove(Professor professorASerRemovido) {
		dao.remove(professorASerRemovido);

		return "/professor.xhtml?faces-redirect=true";
	}

	private String getRootErrorMessage(Exception e) {
		// Default to general error message that registration failed.
		String errorMessage = "Falha ao registrar um professor. Veja o log do servidor para mais detalhes";
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
