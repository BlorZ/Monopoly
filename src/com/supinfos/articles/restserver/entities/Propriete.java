package com.supinfos.articles.restserver.entities;

import java.util.List;

public class Propriete extends Case{
	private String nomProp;
	private Long prixAchat;
	private Long loyer;
	private Long plusValue;
	private int idJoueur;
	
	public Propriete(int idCase, TypeCase typeCase, String _nomCase, String _nomProp, Long _prixAchat, Long _loyer,
			Long _plusValue, int _idJoueur, List<Joueur> _joueursSurCase) {
		super(idCase, typeCase, _nomCase, _joueursSurCase);
		nomProp =  _nomProp;
		prixAchat = _prixAchat;
		loyer = _loyer;
		plusValue = _plusValue;
		idJoueur = _idJoueur;
	}
	
	public Propriete() {
		super();
	}
	
	public String getName() {
		return nomProp;
	}
	
	public void setName(String name) {
		this.nomProp = name;
	}
	
	public Long getPrixAchat() {
		return prixAchat;
	}
	
	public void setPrixAchat(Long prixAchat) {
		this.prixAchat = prixAchat;
	}
	
	public Long getLoyer() {
		return loyer;
	}
	
	public void setLoyer(Long loyer) {
		this.loyer = loyer;
	}
	
	public Long getPlusValue() {
		return plusValue;
	}
	
	public void setPlusValue(Long plusValue) {
		this.plusValue = plusValue;
	}
	
	public int getIdJoueur() {
		return idJoueur;
	}
	
	public void setJoueur(int idJoueur) {
		this.idJoueur = idJoueur;
	}
	
	public String toString() {
		return "Propriete : " + this.getIdCase() + " avec le nom : " + this.getName();
	}
}
