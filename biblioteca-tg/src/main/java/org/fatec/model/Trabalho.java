package org.fatec.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.fatec.util.DateUtil;

@Entity
@XmlRootElement
public class Trabalho implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min = 1, max = 50)
	@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
	private String titulo;

	@NotNull
	@Min(DateUtil.MIN_YEAR)
	@Max(DateUtil.MAX_YEAR_PERMITTED)
	private Integer ano;

	@OneToOne
	@JoinColumn(name = "id")
	private Curso curso;

	@OneToOne
	@JoinColumn(name = "id")
	private Professor orientador;

	// @OneToMany
	// @Size(max = 5)
	// @JoinTable(name = "TRABALHO_PROFESSOR")
	// private List<Professor> banca;

	@NotNull
	@Size(min = 1, max = 255)
	private String resumo;

	@OneToMany
	@Size(max = 5)
	@JoinTable(name = "TRABALHO_ALUNO")
	private List<Aluno> autores;

	@NotNull
	private byte[] arquivo;

	/**
	 * 
	 */
	public Trabalho() {
		super();
	}

	/**
	 * @param id
	 * @param titulo
	 * @param ano
	 * @param curso
	 * @param orientador
	 * @param resumo
	 * @param autores
	 * @param arquivo
	 */
	public Trabalho(Long id, String titulo, Integer ano, Curso curso, Professor orientador, String resumo,
			List<Aluno> autores, byte[] arquivo) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.ano = ano;
		this.curso = curso;
		this.orientador = orientador;
		this.resumo = resumo;
		this.autores = autores;
		this.arquivo = arquivo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Professor getOrientador() {
		return orientador;
	}

	public void setOrientador(Professor orientador) {
		this.orientador = orientador;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public List<Aluno> getAutores() {
		return autores;
	}

	public void setAutores(List<Aluno> autores) {
		this.autores = autores;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

	@Override
	public String toString() {
		return "Trabalho [id=" + id + ", titulo=" + titulo + ", ano=" + ano + ", curso=" + curso + ", orientador="
				+ orientador + ", resumo=" + resumo + ", autores=" + autores + ", arquivo=" + Arrays.toString(arquivo)
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ano == null) ? 0 : ano.hashCode());
		result = prime * result + Arrays.hashCode(arquivo);
		result = prime * result + ((autores == null) ? 0 : autores.hashCode());
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((orientador == null) ? 0 : orientador.hashCode());
		result = prime * result + ((resumo == null) ? 0 : resumo.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trabalho other = (Trabalho) obj;
		if (ano == null) {
			if (other.ano != null)
				return false;
		} else if (!ano.equals(other.ano))
			return false;
		if (!Arrays.equals(arquivo, other.arquivo))
			return false;
		if (autores == null) {
			if (other.autores != null)
				return false;
		} else if (!autores.equals(other.autores))
			return false;
		if (curso == null) {
			if (other.curso != null)
				return false;
		} else if (!curso.equals(other.curso))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (orientador == null) {
			if (other.orientador != null)
				return false;
		} else if (!orientador.equals(other.orientador))
			return false;
		if (resumo == null) {
			if (other.resumo != null)
				return false;
		} else if (!resumo.equals(other.resumo))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

}
