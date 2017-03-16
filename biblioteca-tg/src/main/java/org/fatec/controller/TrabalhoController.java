package org.fatec.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.fatec.dao.AlunoDao;
import org.fatec.dao.CursoDao;
import org.fatec.dao.ProfessorDao;
import org.fatec.dao.TrabalhoDao;
import org.fatec.model.Aluno;
import org.fatec.model.Curso;
import org.fatec.model.Professor;
import org.fatec.model.Trabalho;
import org.fatec.util.DateUtil;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;

@ManagedBean
@SessionScoped
public class TrabalhoController {
	@Inject
	private FacesContext facesContext;
	@Inject
	private Logger logger;
	@Inject
	private TrabalhoDao trabalhoDao;
	@Inject
	private CursoDao cursoDao;
	@Inject
	private ProfessorDao professorDao;
	@Inject
	private AlunoDao alunoDao;

	private Trabalho trabalho = new Trabalho();
	private List<Trabalho> trabalhos;

	private String selectedAno;
	private Map<String, Integer> anos;

	private String selectedCurso;
	private Map<String, Curso> cursos;

	private String selectedOrientador;
	private Map<String, Professor> professores;

	private Map<String, Aluno> alunos;

	private String selectedAluno_1;
	private String selectedAluno_2;
	private String selectedAluno_3;
	private String selectedAluno_4;
	private String selectedAluno_5;

	private UploadedFile file;

	@PostConstruct
	public void init() {
		anos = new TreeMap<>();
		cursos = new TreeMap<>();
		professores = new TreeMap<>();
		alunos = new TreeMap<>();

		DateUtil.populaMapaAnos(anos);
		populaMapaCursos(cursos);
		populaMapaOrientadores(professores);
		populaMapaAlunos(alunos);
	}

	public String grava() {
		try {
			trabalho.setAutores(getAlunosSelecionados());

			trabalho.setOrientador(professores.get(selectedOrientador));
			trabalho.setCurso(cursos.get(selectedCurso));
			trabalho.setAno(anos.get(selectedAno));
			trabalho.setArquivo(file.getContents());

			if (trabalho.getId() != null) {
				trabalhoDao.atualiza(trabalho);
			} else {
				trabalhoDao.adiciona(trabalho);
			}

			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful");
			facesContext.addMessage(null, m);
			logger.info(m.getDetail());
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
			facesContext.addMessage(null, m);
			logger.error(errorMessage);
		}

		trabalhos = trabalhoDao.buscaTodos();

		return "/trabalho.xhtml?faces-redirect=true";
	}

	public String getTituloAba1() {
		if (trabalho.getId() != null) {
			return "Edicao";
		} else {
			return "Cadastro";
		}
	}

	private List<Aluno> getAlunosSelecionados() {
		List<Aluno> alunosSelecionados = new ArrayList<>();
		if (selectedAluno_1 != null) {
			alunosSelecionados.add(alunos.get(selectedAluno_1));
		}
		if (selectedAluno_2 != null) {
			alunosSelecionados.add(alunos.get(selectedAluno_2));
		}
		if (selectedAluno_3 != null) {
			alunosSelecionados.add(alunos.get(selectedAluno_3));
		}
		if (selectedAluno_4 != null) {
			alunosSelecionados.add(alunos.get(selectedAluno_4));
		}
		if (selectedAluno_5 != null) {
			alunosSelecionados.add(alunos.get(selectedAluno_5));
		}

		return alunosSelecionados;
	}

	public String cancela() {
		return "/trabalho.xhtml?faces-redirect=true";

	}

	public String remove(Trabalho trabalhoASerRemovido) {
		trabalhoDao.remove(trabalhoASerRemovido);

		return "/trabalho.xhtml?faces-redirect=true";
	}

	public StreamedContent downloadFile(Trabalho selectedTrabalho) {
		InputStream stream = new ByteArrayInputStream(selectedTrabalho.getArquivo());
		return new DefaultStreamedContent(stream, "application/pdf", selectedTrabalho.getTitulo() + ".pdf");
	}

	private String getRootErrorMessage(Exception e) {
		// Default to general error message that registration failed.
		String errorMessage = "Falha ao registrar um trabalho. Veja o log do servidor para mais detalhes";
		if (e == null) {
			// This shouldn't happen, but return the default messages
			return errorMessage;
		}

		// Start with the exception and recurse to find the root cause
		Throwable t = e;
		while (t != null) {
			// Get the message from the Throwable class instance
			errorMessage = t.getLocalizedMessage();
			t = t.getCause();
		}
		// This is the root cause message
		return errorMessage;
	}

	private void populaMapaCursos(Map<String, Curso> cursos) {
		cursoDao.buscaTodos().stream().forEach(c -> {
			cursos.put(keyCurso(c), c);
		});
	}

	private String keyCurso(Curso c) {
		return c.getSigla() + " - " + c.getNome();
	}

	private void populaMapaOrientadores(Map<String, Professor> orientadores) {
		professorDao.buscaTodos().stream().forEach(p -> {
			orientadores.put(keyOrientador(p), p);
		});

	}

	private String keyOrientador(Professor p) {
		return p.getRegistro() + " - " + p.getNome();
	}

	private void populaMapaAlunos(Map<String, Aluno> alunos) {
		alunoDao.buscaTodos().stream().forEach(a -> {
			alunos.put(keyAluno(a), a);
		});

	}

	private String keyAluno(Aluno a) {
		return a.getRegistro() + " - " + a.getNome();
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public List<Trabalho> getTrabalhos() {
		if (this.trabalhos == null) {
			trabalhos = trabalhoDao.buscaTodos();
		}
		return trabalhos;
	}

	public Trabalho getTrabalho() {
		return trabalho;
	}

	public void setTrabalho(Trabalho trabalho) {
		this.trabalho = trabalho;
	}

	public String getSelectedAno() {
		return selectedAno;
	}

	public void setSelectedAno(String selectedAno) {
		this.selectedAno = selectedAno;
	}

	public Map<String, Integer> getAnos() {
		return anos;
	}

	public void setAnos(Map<String, Integer> anos) {
		this.anos = anos;
	}

	public Map<String, Curso> getCursos() {
		return cursos;
	}

	public void setCursos(Map<String, Curso> cursos) {
		this.cursos = cursos;
	}

	public String getSelectedCurso() {
		return selectedCurso;
	}

	public void setSelectedCurso(String selectedCurso) {
		this.selectedCurso = selectedCurso;
	}

	public Map<String, Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(Map<String, Professor> professores) {
		this.professores = professores;
	}

	public String getSelectedOrientador() {
		return selectedOrientador;
	}

	public void setSelectedOrientador(String selectedOrientador) {
		this.selectedOrientador = selectedOrientador;
	}

	public Map<String, Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(Map<String, Aluno> alunos) {
		this.alunos = alunos;
	}

	public String getSelectedAluno_1() {
		return selectedAluno_1;
	}

	public void setSelectedAluno_1(String selectedAluno_1) {
		this.selectedAluno_1 = selectedAluno_1;
	}

	public String getSelectedAluno_2() {
		return selectedAluno_2;
	}

	public void setSelectedAluno_2(String selectedAluno_2) {
		this.selectedAluno_2 = selectedAluno_2;
	}

	public String getSelectedAluno_3() {
		return selectedAluno_3;
	}

	public void setSelectedAluno_3(String selectedAluno_3) {
		this.selectedAluno_3 = selectedAluno_3;
	}

	public String getSelectedAluno_4() {
		return selectedAluno_4;
	}

	public void setSelectedAluno_4(String selectedAluno_4) {
		this.selectedAluno_4 = selectedAluno_4;
	}

	public String getSelectedAluno_5() {
		return selectedAluno_5;
	}

	public void setSelectedAluno_5(String selectedAluno_5) {
		this.selectedAluno_5 = selectedAluno_5;
	}

	public void setTrabalhos(List<Trabalho> trabalhos) {
		this.trabalhos = trabalhos;
	}

}
