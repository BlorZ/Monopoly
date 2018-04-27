package com.supinfos.articles.restserver.entities;

public enum Meteo {
	PLUIE(1),
	SOLEIL(2),
	NEIGE(3),
	VENTEUX(4);
	
	private int typeMeteo;
	
	private Meteo(int _typeMeteo) {
		typeMeteo = _typeMeteo;
	}
	
	public int getTypeMeteo() {
		return typeMeteo;
	}
}
