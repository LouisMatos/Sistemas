package org.fatec.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.fatec.model.Curso;
import org.slf4j.Logger;

@Stateless
public class CursoDao {

	@Inject
	private Logger logger;
	@Inject
	private EntityManager em;

	public void adiciona(Curso curso) {
		logger.info("Adicionado um Curso: " + curso);
		this.em.persist(curso);
	}

	public void atualiza(Curso curso) {
		logger.info("Atualizando um Curso: " + curso);
		this.em.merge(curso);
	}
	
	public void remove(Curso curso) {
		logger.info("Removido um Curso: " + curso);
		this.em.remove(em.merge(curso));
	}

	public Curso busca(Long id) {
		logger.info("Busca um Curso pelo Id: " + id);
		return this.em.find(Curso.class, id);
	}

	public List<Curso> buscaTodos() {
		logger.info("Busca todos os cursos");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Curso> criteria = cb.createQuery(Curso.class);
		Root<Curso> curso = criteria.from(Curso.class);

		criteria.select(curso);
		return em.createQuery(criteria).getResultList();
	}
}
