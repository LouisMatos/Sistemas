package org.fatec.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.fatec.model.Usuario;
import org.slf4j.Logger;

@Stateless
public class UsuarioDao {

	@Inject
	private Logger logger;
	@Inject
	private EntityManager em;

	public void adiciona(Usuario usuario) {
		logger.info("Adicionado um Usuario: " + usuario);
		this.em.persist(usuario);
	}

	public void atualiza(Usuario usuario) {
		logger.info("Atualizando um Usuario: " + usuario);
		this.em.merge(usuario);
	}

	public void remove(Usuario usuario) {
		logger.info("Removido um Usuario: " + usuario);
		this.em.remove(em.merge(usuario));
	}

	public Usuario busca(Long id) {
		logger.info("Busca um Usuario pelo Id: " + id);
		return this.em.find(Usuario.class, id);
	}

	public List<Usuario> buscaTodos() {
		logger.info("Busca todos os usuarios");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);
		Root<Usuario> usuario = criteria.from(Usuario.class);

		criteria.select(usuario);
		return em.createQuery(criteria).getResultList();
	}

	public boolean existe(Usuario usuario) {
		Query query = em.createQuery("select u from Usuario u where u.email = :pEmail and u.senha = :pSenha")
				.setParameter("pEmail", usuario.getEmail()).setParameter("pSenha", usuario.getSenha());

		boolean encontrado = !query.getResultList().isEmpty();
		logger.info("Existe usuario " + encontrado + "? " + encontrado);
		logger.info(usuario.getSenha());

		return encontrado;
	}

	public boolean existeEmail(Usuario usuario) {
		Query query = em.createQuery("select u from Usuario u where u.email = :pEmail ").setParameter("pEmail",
				usuario.getEmail());

		boolean encontrado = !query.getResultList().isEmpty();
		logger.info("Existe usuario " + usuario + "? " + encontrado);

		return encontrado;
	}
}
