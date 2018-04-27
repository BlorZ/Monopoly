package com.supinfos.articles.restserver.bd;

import java.util.ArrayList;
import java.util.List;

import com.supinfos.articles.restserver.entities.Case;
import com.supinfos.articles.restserver.entities.Joueur;
import com.supinfos.articles.restserver.entities.Pion;
import com.supinfos.articles.restserver.entities.Propriete;
import com.supinfos.articles.restserver.entities.TypeCase;

public class MonopolyBD {

	public static List<Joueur> listJoueur = new ArrayList<>();
	public static List<Propriete> listProp = new ArrayList<>();
	public static List<Case> listCase = new ArrayList<>();

	static {
		//initialisation des joueurs
		listJoueur.add(new Joueur(1, "Micro onde", Pion.MICRO_ONDE, new ArrayList<>(), 0L,
				0, new ArrayList<>()));
		listJoueur.add(new Joueur(2, "Grille pain", Pion.GRILLE_PAIN, new ArrayList<>(), 0L,
				0, new ArrayList<>()));
		listJoueur.add(new Joueur(3, "Ordinateur", Pion.ORDINATEUR, new ArrayList<>(), 0L,
				0, new ArrayList<>()));
		listJoueur.add(new Joueur(4, "Lave vaisselle", Pion.LAVE_VAISSELLE, new ArrayList<>(), 0L,
				0, new ArrayList<>()));

		//initialisation des propriétés
		listProp.add(new Propriete(1, TypeCase.PROPRIETE, "Martigues", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(3, TypeCase.PROPRIETE, "Bouchain", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(6, TypeCase.PROPRIETE, "Canton du Quesnoy", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(8, TypeCase.PROPRIETE, "Les Barthes", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(9, TypeCase.PROPRIETE, "La Nourais", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(11, TypeCase.PROPRIETE, "La Rance", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(12, TypeCase.PROPRIETE, "Paimpol-Bréhat", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(14, TypeCase.PROPRIETE, "Mafate", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(16, TypeCase.PROPRIETE, "Metz-Chambière", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(18, TypeCase.PROPRIETE, "Gardanne", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(19, TypeCase.PROPRIETE, "Brignoles", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(21, TypeCase.PROPRIETE, "Gouadeloupe", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(23, TypeCase.PROPRIETE, "Soultz-Sous-Forêts", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(24, TypeCase.PROPRIETE, "Hatten-Rittorshoffen", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(26, TypeCase.PROPRIETE, "Mirande", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(27, TypeCase.PROPRIETE, "Bordeaux-Lac", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(29, TypeCase.PROPRIETE, "Blauvac", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(31, TypeCase.PROPRIETE, "Fessenheim", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(32, TypeCase.PROPRIETE, "Gravelines", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(34, TypeCase.PROPRIETE, "Tricastin", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(37, TypeCase.PROPRIETE, "GOLFECH", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(39, TypeCase.PROPRIETE, "Strasbourg", 10000L, 1000L, 5000L, null));
		
		//initialisation des cases spéciales
		listCase.add(new Case(0, TypeCase.DEPART));
		listCase.add(new Case(2, TypeCase.CASE_CAISSE_COMMUNAUTE));
		listCase.add(new Case(4, TypeCase.TAXE_ENERGIE));
		listCase.add(new Case(5, TypeCase.SAFE));
		listCase.add(new Case(7, TypeCase.CASE_CHANCE));
		listCase.add(new Case(10, TypeCase.PRISON));
		listCase.add(new Case(12, TypeCase.BONUS_PRIME_ETAT));
		listCase.add(new Case(15, TypeCase.SAFE));
		listCase.add(new Case(17, TypeCase.CASE_CAISSE_COMMUNAUTE));
		listCase.add(new Case(20, TypeCase.PARC_GRATUIT));
		listCase.add(new Case(22, TypeCase.CASE_CHANCE));
		listCase.add(new Case(25, TypeCase.SAFE));
		listCase.add(new Case(28, TypeCase.BONUS_PRIX_NOBEL));
		listCase.add(new Case(30, TypeCase.ALLER_PRISON));
		listCase.add(new Case(33, TypeCase.CASE_CAISSE_COMMUNAUTE));
		listCase.add(new Case(35, TypeCase.SAFE));
		listCase.add(new Case(36, TypeCase.CASE_CHANCE));
		listCase.add(new Case(38, TypeCase.IMPOT_POLUTION));
	}

	public static List<Joueur> getJoueurs() {
		return listJoueur;
	}

	public static List<Propriete> getProprietes() {
		return listProp;
	}
	
	public static List<Case> getCases(){
		return listCase;
	}
}
