package com.supinfos.articles.restserver.entities;

public class Carte {
	private long idCarte;
	private String contenu;
	private TypeCarte typeCarte;
	
	public Carte(long idCarte, String contenu, TypeCarte typeCarte) {
		super();
		this.idCarte = idCarte;
		this.contenu = contenu;
		this.typeCarte = typeCarte;
	}

	public Carte() {
		super();
	}
	
	/**
	 * @return the idCarte
	 */
	public long getIdCarte() {
		return idCarte;
	}
	
	/**
	 * @param idCarte the idCarte to set
	 */
	public void setIdCarte(long idCarte) {
		this.idCarte = idCarte;
	}

	/**
	 * @return the contenu
	 */
	public String getContenu() {
		return contenu;
	}

	/**
	 * @param contenu the contenu to set
	 */
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	/**
	 * @return the typeCarte
	 */
	public TypeCarte getTypeCarte() {
		return typeCarte;
	}

	/**
	 * @param typeCarte the typeCarte to set
	 */
	public void setTypeCarte(TypeCarte typeCarte) {
		this.typeCarte = typeCarte;
	}
	
}
