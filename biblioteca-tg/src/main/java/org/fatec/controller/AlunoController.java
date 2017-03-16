package org.fatec.controller;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.fatec.dao.AlunoDao;
import org.fatec.model.Aluno;
import org.slf4j.Logger;

@Model
public class AlunoController {

	@Inject
	private FacesContext facesContext;
	@Inject
	private Logger logger;
	@Inject
	private AlunoDao dao;

	private Aluno aluno = new Aluno();
	private List<Aluno> alunos;

	public List<Aluno> getAlunos() {
		if (this.alunos == null) {
			alunos = dao.buscaTodos();
		}
		return alunos;
	}

	public String getTituloAba1() {
		if (aluno.getId() != null) {
			return "Edicao";
		} else {
			return "Cadastro";
		}
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public String grava() {
		try {
			if (aluno.getId() != null) {
				dao.atualiza(aluno);
			} else {
				dao.adiciona(aluno);
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

		alunos = dao.buscaTodos();

		return "/aluno.xhtml?faces-redirect=true";
	}

	public String cancela() {
		return "/aluno.xhtml?faces-redirect=true";

	}

	public String remove(Aluno alunoASerRemovido) {
		dao.remove(alunoASerRemovido);

		return "/aluno.xhtml?faces-redirect=true";
	}

	private String getRootErrorMessage(Exception e) {
		// Default to general error message that registration failed.
		String errorMessage = "Falha ao registrar um aluno. Veja o log do servidor para mais detalhes";
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
