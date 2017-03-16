package org.fatec.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.fatec.dao.UsuarioDAO;
import org.fatec.genericdao.GenericDAOImplementation;
import org.fatec.model.Usuario;
import org.jboss.logging.Logger;


public class UsuarioDAOImplementation extends GenericDAOImplementation<Usuario> implements UsuarioDAO{
	
	Logger logger;
	
	public UsuarioDAOImplementation() {
		super(Usuario.class);
	}
	
	
	public boolean existeEmail(Usuario usuario){
			
		if(!entityManager.getTransaction().isActive()){
			entityManager.getTransaction().begin();
		}
		
		Query query = entityManager.createQuery("select u from Usuario u where u.email = :pEmail ").setParameter("pEmail", usuario.getEmail());

		boolean encontrado = !query.getResultList().isEmpty();
		System.out.println("Existe usuario " + usuario.getEmail() + "? " + encontrado);
		entityManager.flush();
		entityManager.getTransaction().commit();
		return encontrado;

	}
	
	
	public Usuario existeUsuario(Usuario usuario){
		Usuario usu = null;
		
		if(!entityManager.getTransaction().isActive()){
			entityManager.getTransaction().begin();
		}
		
		
		Query query = entityManager.createQuery("select u from Usuario u where u.email = :pEmail and u.senha = :pSenha").setParameter("pEmail", usuario.getEmail()).setParameter("pSenha", usuario.getSenha());
		boolean encontrado = !query.getResultList().isEmpty();
		
		if(encontrado ==  true){
			usu = (Usuario) entityManager.createQuery("select u from Usuario u where u.email = :pEmail and u.senha = :pSenha").setParameter("pEmail", usuario.getEmail()).setParameter("pSenha", usuario.getSenha()).getSingleResult();
		}
		
		
		
		
		
		
		//System.out.println("Existe Usu�rio " + usuario.getEmail() + "? " + encontrado);
		entityManager.flush();
		entityManager.getTransaction().commit();
		return usu;
	}
}
