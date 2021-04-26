package ar.edu.utn.frsf.isi.dan.pedido.model;

/**
 * @author Leandro Heraldo Tricarique
 *
 */
public class Cliente {
	private Integer id;
	private String razonSocial;
	private String cuit;
	private String mail;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Cliente)
			return ((Cliente) obj).getId().equals(id);
		else
			return false;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", razonSocial=" + razonSocial + ", cuit=" + cuit + ", mail=" + mail + "]";
	}

}
