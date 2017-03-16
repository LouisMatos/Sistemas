package org.fatec.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.PostLoad;
import javax.servlet.http.HttpSessionEvent;

import org.fatec.impl.AlunoDAOImplementation;
import org.fatec.model.Aluno;
import org.fatec.session.SessionContext;
import org.hibernate.jpa.internal.schemagen.GenerationSourceFromScript;

@ManagedBean
@SessionScoped
public class AlunoController {
	
	private Aluno aluno = new Aluno();
	
	private AlunoDAOImplementation dao = new AlunoDAOImplementation();
	
	private FacesContext facesContext;

	private ArrayList<Aluno> alunos = new ArrayList<Aluno>();;

	private int idt;
	
	public void setIdt(int idt){
		this.idt = idt;
	}
	
	public int getIdt(){
		return this.idt;
	}
	   
	public void cadastrarAluno(){
		try{
			aluno.setNome(aluno.getNome().toUpperCase());
			if(!dao.existeAluno(aluno)){
				dao.save(aluno);
				aluno = new Aluno();
			}else{
				facesContext = FacesContext.getCurrentInstance();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um problema, tente novamente!", "Registro já cadastrado! Tente novamente!");
				facesContext.addMessage("form_aluno:cadAluno", message);
			}
			facesContext = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Cadastro concluido com sucesso!");
	        facesContext.addMessage("form_aluno:cadAluno", message);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void excluirAluno(){
		try{
			dao.excluiAluno(getIdt());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void init() {
		alunos = dao.getAllAlunos();
	}

	public ArrayList<Aluno> getAlunos() {
		return alunos;
	}

	public void cancelar(){
		System.out.println("teste");
		aluno = new Aluno();
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
}
