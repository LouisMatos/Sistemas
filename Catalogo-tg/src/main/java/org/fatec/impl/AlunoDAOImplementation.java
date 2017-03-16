package org.fatec.impl;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.fatec.dao.AlunoDAO;
import org.fatec.genericdao.GenericDAOImplementation;
import org.fatec.model.Aluno;
import org.fatec.model.Usuario;


public class AlunoDAOImplementation extends GenericDAOImplementation<Aluno> implements AlunoDAO{
	
	public AlunoDAOImplementation() {
		super(Aluno.class);
	}
	
	public boolean existeAluno(Aluno aluno){
		boolean encontrado = false;
		
		if(!entityManager.getTransaction().isActive()){
			entityManager.getTransaction().begin();
		}
		
		Query query = entityManager.createQuery("select u from Aluno u where u.registro = :pRegistro ").setParameter("pRegistro", aluno.getRegistro());
		
		encontrado = !query.getResultList().isEmpty();
		System.out.println("Existe aluno " + aluno.getRegistro() + "? " + encontrado);
		entityManager.flush();
		entityManager.getTransaction().commit();
		
		return encontrado;
	}

	@Override
	public ArrayList<Aluno> getAllAlunos() {
		if(!entityManager.getTransaction().isActive()){
			entityManager.getTransaction().begin();
		}
		TypedQuery<Aluno> query = entityManager.createQuery("from Aluno", Aluno.class);
		entityManager.flush();
		entityManager.getTransaction().commit();
		return (ArrayList<Aluno>) query.getResultList();
	}
	
	public void excluiAluno(int id){
		if(!entityManager.getTransaction().isActive()){
			entityManager.getTransaction().begin();
		}
		
		Query query = entityManager.createQuery("delete from Aluno u where u.id = :pID ").setParameter("pID", id);
		
		int result = query.executeUpdate();
		
		entityManager.flush();
		entityManager.getTransaction().commit();
	}

}
