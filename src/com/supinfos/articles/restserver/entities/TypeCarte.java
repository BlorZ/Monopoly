package com.supinfos.articles.restserver.entities;

public enum TypeCarte {
	CHANCE(1),
	CAISSE_COMMUNAUTE(2);
	
	private int typeCarte;
	
	private TypeCarte(int _typeCarte) {
		typeCarte = _typeCarte;
	}
	
	public int getTypeCarte() {
		return typeCarte;
	}
}
