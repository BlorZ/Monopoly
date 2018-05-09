package com.supinfos.articles.restserver.entities;

public enum TypeCase {
	/** Case Chance*/
	CASE_CHANCE(1),
	/** Caisse de communaut� */
	CASE_CAISSE_COMMUNAUTE(2),
	/** Case prison */
	PRISON(3),
	/** Parc Gratuit */
	PARC_GRATUIT(4),
	/** Case D�part=> $$$ bitch */
	DEPART(5),
	/** Case "Aller en prison" */
	ALLER_PRISON(6),
	/** Premi�re case d'imp�t */
	IMPOT_POLUTION(7),
	/** Derni�re case d'imp�t */
	TAXE_ENERGIE(8),
	/** Les bonnes propri�t�s sa m�re */
	PROPRIETE(9),
	/** Case qui remplacent les gares, voir si on a la temps pour les remplacer par des mini-jeux */
	SAFE(10),
	/** 1ere case qui rapporte de l'argent */
	BONUS_PRIME_ETAT(11),
	/** 2e case qui rapporte de l'argent */
	BONUS_PRIX_NOBEL(12),
	/** case prison mais juste la visite */
	VISITE_PRISON(13);
	
	private int typeCase;
	
	private TypeCase(int _typeCase) {
		typeCase = _typeCase;
	}
	
	public int getTypeCase() {
		return typeCase;
	}
	
}
