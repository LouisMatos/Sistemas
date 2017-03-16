package org.fatec.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@XmlRootElement
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "resgistro"))
public class Aluno implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6432841186322605761L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min = 1, max = 25)
	@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
	private String nome;

	@NotNull
	@NotEmpty
	private String resgistro;

	public Aluno() {
		super();
	}

	public Aluno(String nome, String resgistro) {
		super();
		this.nome = nome;
		this.resgistro = resgistro;
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

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRegistro() {
		return resgistro;
	}

	public void setRegistro(String resgistro) {
		this.resgistro = resgistro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((resgistro == null) ? 0 : resgistro.hashCode());
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
		Aluno other = (Aluno) obj;
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
		if (resgistro == null) {
			if (other.resgistro != null)
				return false;
		} else if (!resgistro.equals(other.resgistro))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Aluno [id=" + id + ", nome=" + nome + ", resgistro=" + resgistro + "]";
	}

}
