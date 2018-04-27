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
		listProp.add(new Propriete(1, TypeCase.PROPRIETE, "Propriété", "Martigues", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(3, TypeCase.PROPRIETE, "Propriété", "Bouchain", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(6, TypeCase.PROPRIETE, "Propriété", "Canton du Quesnoy", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(8, TypeCase.PROPRIETE, "Propriété", "Les Barthes", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(9, TypeCase.PROPRIETE, "Propriété", "La Nourais", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(11, TypeCase.PROPRIETE, "Propriété", "La Rance", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(12, TypeCase.PROPRIETE, "Propriété", "Paimpol-Bréhat", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(14, TypeCase.PROPRIETE, "Propriété", "Mafate", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(16, TypeCase.PROPRIETE, "Propriété", "Metz-Chambière", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(18, TypeCase.PROPRIETE, "Propriété", "Gardanne", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(19, TypeCase.PROPRIETE, "Propriété", "Brignoles", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(21, TypeCase.PROPRIETE, "Propriété", "Gouadeloupe", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(23, TypeCase.PROPRIETE, "Propriété", "Soultz-Sous-Forêts", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(24, TypeCase.PROPRIETE, "Propriété", "Hatten-Rittorshoffen", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(26, TypeCase.PROPRIETE, "Propriété", "Mirande", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(27, TypeCase.PROPRIETE, "Propriété", "Bordeaux-Lac", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(29, TypeCase.PROPRIETE, "Propriété", "Blauvac", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(31, TypeCase.PROPRIETE, "Propriété", "Fessenheim", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(32, TypeCase.PROPRIETE, "Propriété", "Gravelines", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(34, TypeCase.PROPRIETE, "Propriété", "Tricastin", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(37, TypeCase.PROPRIETE, "Propriété", "GOLFECH", 10000L, 1000L, 5000L, null));
		listProp.add(new Propriete(39, TypeCase.PROPRIETE, "Propriété", "Strasbourg", 10000L, 1000L, 5000L, null));
		
		//initialisation des cases spéciales
		listCase.add(new Case(0, TypeCase.DEPART, "Départ"));
		listCase.add(new Case(2, TypeCase.CASE_CAISSE_COMMUNAUTE, "Caisse de Communauté"));
		listCase.add(new Case(4, TypeCase.TAXE_ENERGIE, "Taxe d'énergie"));
		listCase.add(new Case(5, TypeCase.SAFE, "Case safe"));
		listCase.add(new Case(7, TypeCase.CASE_CHANCE, "Case Chance"));
		listCase.add(new Case(10, TypeCase.PRISON, "Prison"));
		listCase.add(new Case(12, TypeCase.BONUS_PRIME_ETAT, "Bonus de prime d'état"));
		listCase.add(new Case(15, TypeCase.SAFE, "Case safe"));
		listCase.add(new Case(17, TypeCase.CASE_CAISSE_COMMUNAUTE, "Caisse de Communauté"));
		listCase.add(new Case(20, TypeCase.PARC_GRATUIT, "Parc Gratuit"));
		listCase.add(new Case(22, TypeCase.CASE_CHANCE, "Case Chance"));
		listCase.add(new Case(25, TypeCase.SAFE, "Case safe"));
		listCase.add(new Case(28, TypeCase.BONUS_PRIX_NOBEL, "Prix Nobel"));
		listCase.add(new Case(30, TypeCase.ALLER_PRISON, "Direction Zonzon"));
		listCase.add(new Case(33, TypeCase.CASE_CAISSE_COMMUNAUTE, "Caisse de Communauté"));
		listCase.add(new Case(35, TypeCase.SAFE, "Case safe"));
		listCase.add(new Case(36, TypeCase.CASE_CHANCE, "Case Chance"));
		listCase.add(new Case(38, TypeCase.IMPOT_POLUTION, "Impôt de Polution"));
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
