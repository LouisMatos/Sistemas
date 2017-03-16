package org.fatec.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.fatec.impl.CursoDAOImplementation;
import org.fatec.model.Curso;

@ManagedBean
@SessionScoped
public class CursoController {
	
	private Curso curso = new Curso();
	
	private CursoDAOImplementation dao = new CursoDAOImplementation();
	
	
	public void cadastrarCurso(){
		try{
			System.out.println(curso.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	

}
