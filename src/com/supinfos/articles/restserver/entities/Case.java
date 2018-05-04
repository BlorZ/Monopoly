package com.supinfos.articles.restserver.entities;

import java.util.List;

public class Case {
	private long idCase;
	private TypeCase typeCase;
	private String nom;
	private List<Joueur> joueursSurCase;

	public Case(long _idCase, TypeCase _typeCase, String _nom, List<Joueur> _joueursSurCase) {
		super();
		idCase = _idCase;
		typeCase = _typeCase;
		nom = _nom;
		joueursSurCase = _joueursSurCase;
	}
	
	public Case() {
		super();
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

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void addJoueur(Joueur j) {
		joueursSurCase.add(j);
	}
	
	public void removeJoueur(Joueur j) {
		joueursSurCase.remove(j);
	}
	
	public List<Joueur> getJoueursSurCase() {
		return joueursSurCase;
	}
	
}
