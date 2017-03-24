package org.fatec.controller;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.fatec.impl.CursoDAOImplementation;
import org.fatec.model.Aluno;
import org.fatec.model.Curso;

@ManagedBean
@SessionScoped
public class CursoController {
	
	private Curso curso = new Curso();
	
	private CursoDAOImplementation dao = new CursoDAOImplementation();
	
	private ArrayList<Curso> cursos = new ArrayList<Curso>();
	
	private int idt;
	
	
	public void cadastrarCurso(){
		try{
			System.out.println(curso.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void excluirCurso(){
		try{
			dao.excluiCuros(getIdt());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void init() {
		cursos = dao.getAllCurso();
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
