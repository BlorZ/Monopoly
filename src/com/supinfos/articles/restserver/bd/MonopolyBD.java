package com.supinfos.articles.restserver.bd;

import java.util.ArrayList;
import java.util.List;

import com.supinfos.articles.restserver.entities.Carte;
import com.supinfos.articles.restserver.entities.Case;
import com.supinfos.articles.restserver.entities.Joueur;
import com.supinfos.articles.restserver.entities.Meteo;
import com.supinfos.articles.restserver.entities.Pion;
import com.supinfos.articles.restserver.entities.Plateau;
import com.supinfos.articles.restserver.entities.Propriete;
import com.supinfos.articles.restserver.entities.TypeCarte;
import com.supinfos.articles.restserver.entities.TypeCase;

public class MonopolyBD {

	public static List<Joueur> listJoueur = new ArrayList<>();
	public static List<Case> toutesLesCases = new ArrayList<>();
	public static final List<Carte> cartesChance = new ArrayList<>();
	public static final List<Carte> cartesCaisseCommunaute = new ArrayList<>();
	public static Plateau plateau = new Plateau(Meteo.SOLEIL, 0L);

	static {
		//initialisation des joueurs
		listJoueur.add(new Joueur(1, "Micro onde", Pion.MICRO_ONDE, new ArrayList<>(), 60000L,
				0, new ArrayList<>(),"04 EE FF 81 A0 22 80", 0, false));
		listJoueur.add(new Joueur(2, "Grille pain", Pion.GRILLE_PAIN, new ArrayList<>(), 60000L,
				0, new ArrayList<>(), "6B 1F CD 18", 0, false));
		listJoueur.add(new Joueur(3, "Ordinateur", Pion.ORDINATEUR, new ArrayList<>(), 60000L,
				0, new ArrayList<>(), "04 E1 E1 5A 53 28 80", 0, false));
		listJoueur.add(new Joueur(4, "Lave vaisselle", Pion.LAVE_VAISSELLE, new ArrayList<>(), 60000L,
				0, new ArrayList<>(), "04 DD E0 5A 53 28 80", 0, false));

		//init de liste totale des cases
		toutesLesCases.add(new Case(0, TypeCase.DEPART, "Départ", new ArrayList<>()));
		toutesLesCases.get(0).addJoueur(listJoueur.get(0));
		toutesLesCases.get(0).addJoueur(listJoueur.get(1));
		toutesLesCases.get(0).addJoueur(listJoueur.get(2));
		toutesLesCases.get(0).addJoueur(listJoueur.get(3));
		toutesLesCases.add(new Propriete(1, TypeCase.PROPRIETE, "Propriété", "Martigues", 1000L, 1000L, 5000L, 0, new ArrayList<>()));
		toutesLesCases.add(new Case(2, TypeCase.CASE_CAISSE_COMMUNAUTE, "Caisse de Communauté", new ArrayList<>()));
		toutesLesCases.add(new Propriete(3, TypeCase.PROPRIETE, "Propriété", "Bouchain", 1000L, 1000L, 5000L, 0, new ArrayList<>()));
		toutesLesCases.add(new Case(4, TypeCase.TAXE_ENERGIE, "Taxe d'énergie", new ArrayList<>()));
		toutesLesCases.add(new Case(5, TypeCase.SAFE, "Case safe", new ArrayList<>()));
		toutesLesCases.add(new Propriete(6, TypeCase.PROPRIETE, "Propriété", "Canton du Quesnoy", 2000L, 1000L, 5000L, 0, new ArrayList<>()));
		toutesLesCases.add(new Case(7, TypeCase.CASE_CHANCE, "Case Chance", new ArrayList<>()));
		toutesLesCases.add(new Propriete(8, TypeCase.PROPRIETE, "Propriété", "Les Barthes", 2000L, 1000L, 5000L, 0, new ArrayList<>()));
		toutesLesCases.add(new Propriete(9, TypeCase.PROPRIETE, "Propriété", "La Nourais", 2000L, 1000L, 5000L, 0, new ArrayList<>()));
		toutesLesCases.add(new Case(10, TypeCase.VISITE_PRISON, "Visite Prison", new ArrayList<>()));
		toutesLesCases.add(new Propriete(11, TypeCase.PROPRIETE, "Propriété", "La Rance", 5000L, 1000L, 5000L, 0, new ArrayList<>()));
		toutesLesCases.add(new Propriete(12, TypeCase.PROPRIETE, "Propriété", "Paimpol-Bréhat", 5000L, 1000L, 5000L, 0, new ArrayList<>()));
		toutesLesCases.add(new Case(13, TypeCase.BONUS_PRIME_ETAT, "Bonus de prime d'état", new ArrayList<>()));
		toutesLesCases.add(new Propriete(14, TypeCase.PROPRIETE, "Propriété", "Mafate", 5000L, 1000L, 5000L, 0, new ArrayList<>()));
		toutesLesCases.add(new Case(15, TypeCase.SAFE, "Case safe", new ArrayList<>()));
		toutesLesCases.add(new Propriete(16, TypeCase.PROPRIETE, "Propriété", "Metz-Chambière", 10000L, 1000L, 5000L, 0, new ArrayList<>()));
		toutesLesCases.add(new Case(17, TypeCase.CASE_CAISSE_COMMUNAUTE, "Caisse de Communauté", new ArrayList<>()));
		toutesLesCases.add(new Propriete(18, TypeCase.PROPRIETE, "Propriété", "Gardanne", 10000L, 1000L, 5000L, 0, new ArrayList<>()));
		toutesLesCases.add(new Propriete(19, TypeCase.PROPRIETE, "Propriété", "Brignoles", 10000L, 1000L, 5000L, 0, new ArrayList<>()));
		toutesLesCases.add(new Case(20, TypeCase.PARC_GRATUIT, "Parc Gratuit", new ArrayList<>()));
		toutesLesCases.add(new Propriete(21, TypeCase.PROPRIETE, "Propriété", "Gouadeloupe", 10000L, 1000L, 5000L, 0, new ArrayList<>()));
		toutesLesCases.add(new Case(22, TypeCase.CASE_CHANCE, "Case Chance", new ArrayList<>()));
		toutesLesCases.add(new Propriete(23, TypeCase.PROPRIETE, "Propriété", "Soultz-Sous-Forêts", 10000L, 1000L, 5000L, 0, new ArrayList<>()));
		toutesLesCases.add(new Propriete(24, TypeCase.PROPRIETE, "Propriété", "Hatten-Rittorshoffen", 10000L, 1000L, 5000L, 0, new ArrayList<>()));
		toutesLesCases.add(new Case(25, TypeCase.SAFE, "Case safe", new ArrayList<>()));
		toutesLesCases.add(new Propriete(26, TypeCase.PROPRIETE, "Propriété", "Mirande", 10000L, 1000L, 5000L, 0, new ArrayList<>()));
		toutesLesCases.add(new Propriete(27, TypeCase.PROPRIETE, "Propriété", "Bordeaux-Lac", 10000L, 1000L, 5000L, 0, new ArrayList<>()));
		toutesLesCases.add(new Case(28, TypeCase.BONUS_PRIX_NOBEL, "Prix Nobel", new ArrayList<>()));
		toutesLesCases.add(new Propriete(29, TypeCase.PROPRIETE, "Propriété", "Blauvac", 10000L, 1000L, 5000L, 0, new ArrayList<>()));
		toutesLesCases.add(new Case(30, TypeCase.ALLER_PRISON, "Direction Zonzon", new ArrayList<>()));
		toutesLesCases.add(new Propriete(31, TypeCase.PROPRIETE, "Propriété", "Fessenheim", 10000L, 1000L, 5000L, 0, new ArrayList<>()));
		toutesLesCases.add(new Propriete(32, TypeCase.PROPRIETE, "Propriété", "Gravelines", 10000L, 1000L, 5000L, 0, new ArrayList<>()));
		toutesLesCases.add(new Case(33, TypeCase.CASE_CAISSE_COMMUNAUTE, "Caisse de Communauté", new ArrayList<>()));
		toutesLesCases.add(new Propriete(34, TypeCase.PROPRIETE, "Propriété", "Tricastin", 10000L, 1000L, 5000L, 0, new ArrayList<>()));
		toutesLesCases.add(new Case(35, TypeCase.SAFE, "Case safe", new ArrayList<>()));
		toutesLesCases.add(new Case(36, TypeCase.CASE_CHANCE, "Case Chance", new ArrayList<>()));
		toutesLesCases.add(new Propriete(37, TypeCase.PROPRIETE, "Propriété", "GOLFECH", 10000L, 1000L, 5000L, 0, new ArrayList<>()));
		toutesLesCases.add(new Case(38, TypeCase.IMPOT_POLUTION, "Impôt de Polution", new ArrayList<>()));
		toutesLesCases.add(new Propriete(39, TypeCase.PROPRIETE, "Propriété", "Strasbourg", 10000L, 1000L, 5000L, 0, new ArrayList<>()));
		toutesLesCases.add(new Case(40, TypeCase.PRISON, "Prison", new ArrayList<>()));
		
		//contenu des cartes chances
		cartesChance.add(new Carte(1L, "Sortez de prison", TypeCarte.CHANCE));
		cartesChance.add(new Carte(2L, "Votre campagne de publicité rapporte 5000 watts", TypeCarte.CHANCE));
		cartesChance.add(new Carte(3L, "Un incident s'est produit dans l'une de vos propriétés, payez 7500 watts de réparation", TypeCarte.CHANCE));
		
		//contenu des cartes caisse de communauté
		cartesCaisseCommunaute.add(new Carte(1L, "Allez à la case départ!", TypeCarte.CAISSE_COMMUNAUTE));
		cartesCaisseCommunaute.add(new Carte(2L, "Allez à la case prison!", TypeCarte.CAISSE_COMMUNAUTE));
		cartesCaisseCommunaute.add(new Carte(3L, "Sortez de prison", TypeCarte.CAISSE_COMMUNAUTE));
	}

	public static List<Joueur> getJoueurs() {
		return listJoueur;
	}
	
	public static List<Case> getAllCases(){
		return toutesLesCases;
	}
	
	public static Case getCaseById(int id) {
		return toutesLesCases.get(id);
	}
}
