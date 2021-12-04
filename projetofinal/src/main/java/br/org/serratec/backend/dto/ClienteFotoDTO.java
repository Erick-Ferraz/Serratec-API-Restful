package br.org.serratec.backend.dto;

import java.io.Serializable;

public class ClienteFotoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6101660102507486515L;
	
	private Integer id;
	
	public ClienteFotoDTO() {

	}

	public ClienteFotoDTO(Integer id) {

		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		ClienteFotoDTO other = (ClienteFotoDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
