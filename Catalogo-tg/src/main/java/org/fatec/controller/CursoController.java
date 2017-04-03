package org.fatec.controller;

import java.util.ArrayList;
import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.fatec.impl.CursoDAOImplementation;
import org.fatec.model.Aluno;
import org.fatec.model.Curso;

@ManagedBean
@SessionScoped
public class CursoController {
	
	private Curso curso = new Curso();
	
	private CursoDAOImplementation dao = new CursoDAOImplementation();
	
	private ArrayList<Curso> cursos = new ArrayList<Curso>();
	
	private ArrayList<Curso> aux = new ArrayList<Curso>();
	
	private int idt;
	
	private FacesContext facesContext;
	
	public void cadastrarCurso(){
		try{
			if(!dao.existeCurso(curso)){
				dao.save(curso);
				curso = new Curso();
				facesContext = FacesContext.getCurrentInstance();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Cadastro concluido com sucesso!");
				facesContext.addMessage("form_curso:cadCurso", message);
			}else{
				facesContext = FacesContext.getCurrentInstance();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocorreu um problema, tente novamente!", "Curso já cadastrado! Tente novamente!");
				facesContext.addMessage("form_curso:cadCurso", message);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void excluirCurso(){
		try{
			System.out.println(getIdt());
			//dao.excluiCuros(getIdt());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void init() {
		
		cursos =  dao.getAllCurso();
		
		for (Iterator<Curso> iterator = cursos.iterator(); iterator.hasNext(); ) {
			   Curso c = iterator.next();
			  
			   if(c.getPeriodo().equals("1")){
				   c.setPeriodo("Manhã");
			   }else if(c.getPeriodo().equals("2")){
				   c.setPeriodo("Tarde");
			   }else if(c.getPeriodo().equals("3")){
				   c.setPeriodo("Noite");
			   }
			   
			   aux.add(c);
		}
		
		cursos = aux;
		aux = new ArrayList<Curso>();
		
		//cursos = dao.getAllCurso();
	}
	
	public ArrayList<Curso> getCursos() {
		return cursos;
	}
	

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public int getIdt() {
		return idt;
	}

	public void setIdt(int idt) {
		this.idt = idt;
	}
	
	

}
