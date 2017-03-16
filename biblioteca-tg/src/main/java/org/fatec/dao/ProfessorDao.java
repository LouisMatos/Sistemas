package org.fatec.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.fatec.model.Professor;
import org.slf4j.Logger;

@Stateless
public class ProfessorDao {

	@Inject
	private Logger logger;
	@Inject
	private EntityManager em;

	public void adiciona(Professor professor) {
		logger.info("Adicionado um Professor: " + professor);
		this.em.persist(professor);
	}

	public void atualiza(Professor professor) {
		logger.info("Atualizando um Professor: " + professor);
		this.em.merge(professor);
	}

	public void remove(Professor professor) {
		logger.info("Removido um Professor: " + professor);
		this.em.remove(em.merge(professor));
	}

	public Professor busca(Long id) {
		logger.info("Busca um Professor pelo Id: " + id);
		return this.em.find(Professor.class, id);
	}

	public List<Professor> buscaTodos() {
		logger.info("Busca todos os professors");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Professor> criteria = cb.createQuery(Professor.class);
		Root<Professor> professor = criteria.from(Professor.class);

		criteria.select(professor);
		return em.createQuery(criteria).getResultList();
	}
}
