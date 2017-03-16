package org.fatec.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.fatec.model.Trabalho;
import org.slf4j.Logger;

@Stateless
public class TrabalhoDao {

	@Inject
	private Logger logger;
	@Inject
	private EntityManager em;

	public void adiciona(Trabalho trabalho) {
		logger.info("Adicionado um Trabalho: " + trabalho);
		this.em.persist(trabalho);
	}

	public void atualiza(Trabalho trabalho) {
		logger.info("Atualizando um Trabalho: " + trabalho);
		this.em.merge(trabalho);
	}

	public void remove(Trabalho trabalho) {
		logger.info("Removido um Trabalho: " + trabalho);
		this.em.remove(em.merge(trabalho));
	}

	public Trabalho busca(Long id) {
		logger.info("Busca um Trabalho pelo Id: " + id);
		return this.em.find(Trabalho.class, id);
	}

	public List<Trabalho> buscaTodos() {
		logger.info("Busca todos os trabalhos");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Trabalho> criteria = cb.createQuery(Trabalho.class);
		Root<Trabalho> trabalho = criteria.from(Trabalho.class);

		criteria.select(trabalho);
		return em.createQuery(criteria).getResultList();
	}
}
