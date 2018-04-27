package com.supinfos.articles.restserver.entities;

public class Case {
	private long idCase;
	private TypeCase typeCase;

	public Case(long _idCase, TypeCase _typeCase) {
		super();
		idCase = _idCase;
		typeCase = _typeCase;
	}
	
	/**
	 * @return the idCase
	 */
	public long getIdCase() {
		return idCase;
	}

	/**
	 * @param idCase the idCase to set
	 */
	public void setIdCase(long idCase) {
		this.idCase = idCase;
	}

	/**
	 * @return the typeCase
	 */
	public TypeCase getTypeCase() {
		return typeCase;
	}

	/**
	 * @param typeCase the typeCase to set
	 */
	public void setTypeCase(TypeCase typeCase) {
		this.typeCase = typeCase;
	}
}
