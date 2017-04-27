package org.fatec.impl;

import java.util.ArrayList;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.fatec.genericdao.GenericDAOImplementation;
import org.fatec.model.Curso;

public class CursoDAOImplementation extends GenericDAOImplementation<Curso>{
	
	public CursoDAOImplementation(){
		super(Curso.class);
	}
	
	public ArrayList<Curso> getAllCurso(){
		if(!entityManager.getTransaction().isActive()){
			entityManager.getTransaction().begin();
		}
		TypedQuery<Curso> query = entityManager.createQuery("from Curso", Curso.class);
		entityManager.flush();
		entityManager.getTransaction().commit();
		return (ArrayList<Curso>) query.getResultList();
		
	}

	public void excluiCuro(int id) {
		if(!entityManager.getTransaction().isActive()){
			entityManager.getTransaction().begin();
		}
		
		Query query = entityManager.createQuery("delete from Curso u where u.id = :pID ").setParameter("pID", id);
		
		int result = query.executeUpdate();
		
		entityManager.flush();
		entityManager.getTransaction().commit();
	}

	public boolean existeCurso(Curso curso) {
		boolean encontrado = false;
		
		if(!entityManager.getTransaction().isActive()){
			entityManager.getTransaction().begin();
		}
		
		Query query = entityManager.createQuery("select u from Curso u where u.sigla = :pSigla ").setParameter("pSigla", curso.getSigla());
		
		encontrado = !query.getResultList().isEmpty();
		System.out.println("Existe curso " + curso.getSigla() + "? " + encontrado);
		entityManager.flush();
		entityManager.getTransaction().commit();
		
		return encontrado;
	}

}
