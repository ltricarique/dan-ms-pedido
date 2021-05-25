package ar.edu.utn.frsf.isi.dan.pedido.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Immutable;

/**
 * @author Leandro Heraldo Tricarique
 *
 */
@Entity
@Immutable
@Table(name = "ESTADO_PEDIDO", schema = "MS_PEDIDO")
public class EstadoPedido
{
	@Id
	@Column(name = "ID")
	private Long id;
	@Column(name = "ESTADO")
	private String estado;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getEstado()
	{
		return estado;
	}

	public void setEstado(String estado)
	{
		this.estado = estado;
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
		EstadoPedido other = (EstadoPedido) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString()
	{
		return "EstadoPedido [id=" + id + ", estado=" + estado + "]";
	}
}
