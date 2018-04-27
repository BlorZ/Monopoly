package com.supinfos.articles.restserver.entities;

/** Liste des différents pions */ 
public enum Pion {
	
	MICRO_ONDE(1),
	ORDINATEUR(2),
	GRILLE_PAIN(3),
	LAVE_VAISSELLE(4);
	
	private int idPion;
	
	Pion(int _idPion){
		idPion = _idPion;
	}
	
	public int getIdPion() {
		return idPion;
	}
}
