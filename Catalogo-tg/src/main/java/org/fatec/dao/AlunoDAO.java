package org.fatec.dao;

import java.util.ArrayList;


import org.fatec.genericdao.GenericDAOInterface;
import org.fatec.model.Aluno;



public interface AlunoDAO extends GenericDAOInterface<Aluno>{
	public ArrayList<Aluno> getAllAlunos();
}
