package org.fatec.dao;

import java.util.ArrayList;

import org.fatec.genericdao.GenericDAOInterface;
import org.fatec.model.Curso;

public interface CursoDAO extends GenericDAOInterface<Curso>{
	public ArrayList<Curso> getAllCurso();
}
