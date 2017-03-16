package org.fatec.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.fatec.model.Aluno;
import org.slf4j.Logger;

@Stateless
public class AlunoDao {

	@Inject
	private Logger logger;
	@Inject
	private EntityManager em;

	public void adiciona(Aluno aluno) {
		logger.info("Adicionado um Aluno: " + aluno);
		em.persist(aluno);
	}

	public void atualiza(Aluno aluno) {
		logger.info("Atualizando um Aluno: " + aluno);
		this.em.merge(aluno);
	}

	public void remove(Aluno aluno) {
		logger.info("Removido um Aluno: " + aluno);
		em.remove(em.merge(aluno));
	}

	public Aluno busca(Long id) {
		logger.info("Busca um Aluno pelo Id: " + id);
		return em.find(Aluno.class, id);
	}

	public List<Aluno> buscaTodos() {
		logger.info("Busca todos os alunos");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Aluno> criteria = cb.createQuery(Aluno.class);
		Root<Aluno> aluno = criteria.from(Aluno.class);

		criteria.select(aluno);
		return em.createQuery(criteria).getResultList();
	}
}
