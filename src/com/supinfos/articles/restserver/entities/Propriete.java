package com.supinfos.articles.restserver.entities;

public class Propriete extends Case{
	private String name;
	private Long prixAchat;
	private Long loyer;
	private Long plusValue;
	private Joueur idJoueur;
	
	public Propriete(long idCase, TypeCase typeCase, String _nomCase, String _name, Long _prixAchat, Long _loyer,
			Long _plusValue, Joueur _idJoueur) {
		super(idCase, typeCase, _nomCase);
		name =  _name;
		prixAchat = _prixAchat;
		loyer = _loyer;
		plusValue = _plusValue;
		idJoueur = _idJoueur;
	}
	
	public Propriete() {
		super();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
	
	public Joueur getIdJoueur() {
		return idJoueur;
	}
	
	public void setJoueur(Joueur idJoueur) {
		this.idJoueur = idJoueur;
	}
	
	public String toString() {
		return "Propriete : " + this.getIdCase() + " avec le nom : " + this.getName();
	}
}
