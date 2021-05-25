package ar.edu.utn.frsf.isi.dan.pedido.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Leandro Heraldo Tricarique
 *
 */
@Entity
@Table(name = "CLIENTE", schema = "MS_USUARIO")
public class Cliente
{
	@Id
	@Column(name = "ID")
	private Long id;
	@Column(name = "RAZON_SOCIAL")
	private String razonSocial;
	@Column(name = "CUIT")
	private String cuit;
	@Column(name = "EMAIL")
	private String email;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getRazonSocial()
	{
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial)
	{
		this.razonSocial = razonSocial;
	}

	public String getCuit()
	{
		return cuit;
	}

	public void setCuit(String cuit)
	{
		this.cuit = cuit;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString()
	{
		return "Cliente [id=" + id + ", razonSocial=" + razonSocial + ", cuit=" + cuit + ", email=" + email + "]";
	}

}
