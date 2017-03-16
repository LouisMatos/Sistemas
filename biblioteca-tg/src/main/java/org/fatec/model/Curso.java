package org.fatec.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Curso implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5434299445939673433L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min = 1, max = 50)
	@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
	private String nome;

	@NotNull
	@Size(min = 1, max = 3)
	@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
	private String sigla;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoPeriodo periodo;

	public Curso() {
		super();
	}

	public Curso(String name, String sigla, TipoPeriodo periodo) {
		super();
		this.nome = name;
		this.sigla = sigla.toUpperCase();
		this.periodo = periodo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String name) {
		this.nome = name;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla.toUpperCase();
	}

	public TipoPeriodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(TipoPeriodo periodo) {
		this.periodo = periodo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((periodo == null) ? 0 : periodo.hashCode());
		result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
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
		Curso other = (Curso) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (periodo != other.periodo)
			return false;
		if (sigla != other.sigla)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Curso [id=" + id + ", nome=" + nome + ", sigla=" + sigla + ", periodo=" + periodo + "]";
	}

}
