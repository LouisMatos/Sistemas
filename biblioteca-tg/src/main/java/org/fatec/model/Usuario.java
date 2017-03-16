package org.fatec.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.fatec.util.SecurityUtil;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@XmlRootElement
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "email") })
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3095650535464911506L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotEmpty
	@Email
	private String email;

	@NotNull
	@Size(min = 6, max = 8)
	private String senha;

	@Enumerated(EnumType.STRING)
	private TipoUsuario tipoUsuario;

	@Transient
	private String confirmacaoSenha;

	public Usuario() {
		super();
	}

	public Usuario(Long id, String email, String senha, TipoUsuario tipoUsuario) {
		super();
		this.id = id;
		this.email = email;
		this.senha = SecurityUtil.getHashPassword(senha);
		this.tipoUsuario = tipoUsuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = SecurityUtil.getHashPassword(senha);
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.senha = SecurityUtil.getHashPassword(senha);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + ((tipoUsuario == null) ? 0 : tipoUsuario.hashCode());
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
		Usuario other = (Usuario) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (tipoUsuario != other.tipoUsuario)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", email=" + email + ", tipoUsuario=" + tipoUsuario + "]";
	}

}
