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

	public void excluiCuros(int id) {
		if(!entityManager.getTransaction().isActive()){
			entityManager.getTransaction().begin();
		}
		
		Query query = entityManager.createQuery("delete from Curso u where u.id = :pID ").setParameter("pID", id);
		
		int result = query.executeUpdate();
		
		entityManager.flush();
		entityManager.getTransaction().commit();
	}

}
