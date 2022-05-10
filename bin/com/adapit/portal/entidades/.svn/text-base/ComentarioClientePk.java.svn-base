package com.adapit.portal.entidades;

import java.io.Serializable;

public class ComentarioClientePk  implements Serializable{

	private static final long serialVersionUID = 23325742674822482L;	
	
/*	@Id
	@Column(nullable = false,name="person_id")*/
	private long idPessoa;
	
/*	@Id
	@Column(nullable = false,name="commercial_solution_id")*/
	private int idComSol;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idComSol;
		result = prime * result + (int) (idPessoa ^ (idPessoa >>> 32));
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
		final ComentarioClientePk other = (ComentarioClientePk) obj;
		if (idComSol != other.idComSol)
			return false;
		if (idPessoa != other.idPessoa)
			return false;
		return true;
	}
	
	
	
}