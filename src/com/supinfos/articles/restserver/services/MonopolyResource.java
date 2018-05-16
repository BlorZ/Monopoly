package com.supinfos.articles.restserver.services;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.supinfos.articles.restserver.bd.MonopolyBD;
import com.supinfos.articles.restserver.entities.Carte;
import com.supinfos.articles.restserver.entities.Case;
import com.supinfos.articles.restserver.entities.Joueur;
import com.supinfos.articles.restserver.entities.Nfc;
import com.supinfos.articles.restserver.entities.Propriete;

@Path("/monopoly")
@Produces(MediaType.APPLICATION_JSON)
public class MonopolyResource {
	@GET
	@Path("/joueurs")
	public Response getJoueurs() {
		return Response.ok(MonopolyBD.getJoueurs())
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
				.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
	}

	@GET
	@Path("/joueurs/{id}")
	public Response getJoueur(@PathParam("id") int idJoueur) throws Exception {
		for (Joueur current : MonopolyBD.getJoueurs()) {
			if (idJoueur == current.getId()) {
				return Response.ok(current)
						.header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
						.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
			}
		}
		throw new Exception("Ce joueur n'existe pas!");
	}

	@GET
	@Path("/cases")
	public Response getCase(){
		return Response.ok(MonopolyBD.getAllCases())
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
				.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
	}

	@GET
	@Path("/cases/{id}")
	public Response getCase(@PathParam("id") int idCase) throws Exception{
		return Response.ok(MonopolyBD.getCaseById(idCase))
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
				.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
	}

	@GET
	@Path("/achat")
	@Produces(MediaType.TEXT_PLAIN + ";charset=UTF-8")
	/**Permet d'acheter une propriete si on a assez d'argent, que la prop est a vendre et qu'on passe sa carte*/
	public Response achatPropriete(@QueryParam("idCase") Long idCase, @QueryParam("idJoueur") int idJoueur) throws Exception {
		Joueur joueur = new Joueur();
		Propriete prop = new Propriete();
		String carte = "";
		
		for(Case p : MonopolyBD.getAllCases()) {
			if(idCase == p.getIdCase()) {
				prop = (Propriete) p;
			}
		}
		for(Joueur j : MonopolyBD.getJoueurs()) {
			if(idJoueur==j.getId()) {
				joueur = j;
			}
		}
		
		if(prop.getIdJoueur() == 0 && joueur.getSolde() >= prop.getPrixAchat()) {
			
			//Lecture carte nfc
			carte = readNfc().getEntity().toString();
			
			if (carte.equals(joueur.getNfcTag())) {
			
				//traitement coté joueur
				joueur.setSolde(joueur.getSolde()-prop.getPrixAchat());
				joueur.addPropriete(prop.getIdCase());
				//traitement coté propriete
				prop.setJoueur(joueur.getId());
				
				return Response.ok("Vous avez acheté la propriété : " + prop.getName())
						.header("Access-Control-Allow-Origin", "*")
						.build();
			}
		}
		if(prop.getIdJoueur() != 0 ) {
			throw new Exception("La propriété appartient déjà à quelqu'un d'autre");
		}
		if(joueur.getSolde() >= prop.getPrixAchat() ) {
			throw new Exception("Vous n'avez pas assez d'argent pour acheter cette propiété");
		}
		
		throw new Exception("Une erreur est survenue");
	}
	
	@GET
	@Path("/readNfc")
	@Produces(MediaType.TEXT_PLAIN + ";charset=UTF-8")
	public Response readNfc() throws Exception {
		String result = "";
		int retour = 0;
		
		try {
			retour = Nfc.start();
		} catch (Exception e) {
			retour = 0;
			e.printStackTrace();
			throw new Exception("Le lecteur NFC n'a pas démarré correctement.");
		}
		
		try {
			result = Nfc.read();
		} catch (Exception e) {
			retour = 0;
			e.printStackTrace();
			throw new Exception("Erreur lors de la lecture de la carte RFID");
		}
		
		try {
			retour = Nfc.stop();
		} catch (Exception e) {
			retour = 0;
			e.printStackTrace();
			throw new Exception("Erreur lors de la fermeture du lecteur NFC");
		}
		System.out.println(retour);
		Thread.sleep(1000);
		//result = Integer.toUnsignedString(retour); ne pas decommenter
		
		if (result =="ERROR") {
			return Response.notModified()
					.header("Access-Control-Allow-Origin", "*")
					.build();
		}
		return Response.ok(result)
				.header("Access-Control-Allow-Origin", "*")
				.build();
	}
	
	@GET
	@Path("/deplacement")
	@Produces(MediaType.TEXT_PLAIN + ";charset=UTF-8")
	public Response deplacement(@QueryParam("idJoueur") long idJoueur, @QueryParam("des1") int des1, @QueryParam("des2") int des2) throws Exception {
		String message = "";
		if(des1 < 1 || des1 > 6) {
			throw new Exception("Il est impossible de faire ce résultat avec le 1er dé");
		}
		if(des2 < 1 || des2 > 6) {
			throw new Exception("Il est impossible de faire ce résultat avec le 2e dé");
		}
		if(des1 + des2 < 2 || des1 + des2 > 12) {
			throw new Exception("Il est impossible de faire ce résultat avec 2 dés");
		}
		
		int resultDes = des1 + des2;
		
		Joueur joueur = new Joueur();
		for(Joueur j : MonopolyBD.getJoueurs()) {
			if(idJoueur==j.getId()) {
				joueur = j;
			}
		}
		
		if(joueur.getPosition() == 30) {
			if(joueur.getNbTourPrison() >= 3) {
				joueur.setPosition(10);
				MonopolyBD.toutesLesCases.get(30).removeJoueur(joueur);
				MonopolyBD.toutesLesCases.get(10).addJoueur(joueur);
				joueur.setNbTourPrison(0);
				//on fait avancer le joueur
				MonopolyBD.getCaseById(joueur.getPosition()).removeJoueur(joueur);
				joueur.setPosition((joueur.getPosition() + resultDes) % 40);
				//Ajout pour mettre joueur sur case
				Case dest = MonopolyBD.getCaseById((joueur.getPosition()));
				dest.addJoueur(joueur);
				message ="Votre moment en prison est fini, vous passez par la sortie visiteur et avancez de " + resultDes + " cases";
				return Response.ok(message)
						.header("Access-Control-Allow-Origin", "*").build();
			}
			else {
				//si double, on sort de prison
				if(des1 == des2) {
					//on met le joueur a la case visite prison
					joueur.setPosition(10);
					MonopolyBD.toutesLesCases.get(30).removeJoueur(joueur);
					MonopolyBD.toutesLesCases.get(10).addJoueur(joueur);
					joueur.setNbTourPrison(0);
					//on fait avancer le joueur
					MonopolyBD.getCaseById(joueur.getPosition()).removeJoueur(joueur);
					joueur.setPosition((joueur.getPosition() + resultDes) % 40);
					//Ajout pour mettre joueur sur case
					Case dest = MonopolyBD.getCaseById((joueur.getPosition()));
					dest.addJoueur(joueur);
					message = "Vous avez fait un double, vous sortez de prison et avancez de " + resultDes + " cases";
					return Response.ok(message)
							.header("Access-Control-Allow-Origin", "*").build();
				}
				//si non double, on reste en zonzon
				else {
					joueur.setNbTourPrison(joueur.getNbTourPrison() +1);
					message = "Vous restez en prison!";
					return Response.ok(message)
							.header("Access-Control-Allow-Origin", "*").build();
				}
			}
		}
		else {
			MonopolyBD.getCaseById(joueur.getPosition()).removeJoueur(joueur);
			
			joueur.setPosition((joueur.getPosition() + resultDes) % 40);
			//Ajout pour mettre joueur sur case
			Case dest = MonopolyBD.getCaseById((joueur.getPosition()));
			dest.addJoueur(joueur);
		}	
		message = "Vous avez avancé de " + resultDes + " cases";
		return Response.ok(message)
				.header("Access-Control-Allow-Origin", "*").build();
	}
	
	@GET
	@Path("case-speciale/")
	@Produces(MediaType.TEXT_PLAIN + ";charset=UTF-8")
	public Response gestionCaseSpeciale(@QueryParam("idCase") Long idCase, @QueryParam("idJoueur") Integer idJoueur) throws Exception {
		String message = "";
		Case positionCase = new Case();
		Joueur joueur = new Joueur();
		for(Case current : MonopolyBD.toutesLesCases) {
			if(idCase == current.getIdCase()) {
				positionCase = current;
			}
		}

		for(Joueur current : MonopolyBD.listJoueur) {
			if(idJoueur.equals(current.getId())) {
				joueur = current;
			}
		}
		switch(positionCase.getTypeCase()) {
			case DEPART: 
				joueur.setSolde(joueur.getSolde() + 2000L);
				break;
			case CASE_CHANCE:
				int random = (int) (Math.random() * MonopolyBD.cartesChance.size());
				Carte carte = MonopolyBD.cartesChance.get(random);
				if(carte.getContenu() == "Sortez de prison") {
					joueur.getListeCarte().add(carte);
				}
				else if(carte.getContenu() == "Votre campagne de publicité rapporte 2000 watts") {
					joueur.setSolde(joueur.getSolde() + 2000L);
				}
				else if(carte.getContenu() == "Un incident s'est produit dans l'une de vos propriétés, payez 2500 watts de réparation") {
					joueur.setSolde(joueur.getSolde() - 2500L);
					MonopolyBD.plateau.setPot(MonopolyBD.plateau.getPot() + 2500L);
				}
				message = carte.getContenu();
				break;
			case CASE_CAISSE_COMMUNAUTE:
				random = (int) (Math.random() * MonopolyBD.cartesCaisseCommunaute.size());
				carte = MonopolyBD.cartesCaisseCommunaute.get(random);
				if(carte.getContenu() == "Allez à la case départ!") {
					MonopolyBD.toutesLesCases.get(joueur.getPosition()).removeJoueur(joueur);
					joueur.setPosition(0);
					MonopolyBD.toutesLesCases.get(0).addJoueur(joueur);
				}
				else if(carte.getContenu() == "Allez à la case prison!") {
					MonopolyBD.toutesLesCases.get(joueur.getPosition()).removeJoueur(joueur);
					joueur.setPosition(30);
					MonopolyBD.toutesLesCases.get(30).addJoueur(joueur);
				}
				else if(carte.getContenu() == "Sortez de prison") {
					joueur.getListeCarte().add(carte);
				}
				message = carte.getContenu();
				break;
			case PRISON:
				break;
			case BONUS_PRIME_ETAT:
				joueur.setSolde(joueur.getSolde() + 2000L);
				message = "Vous recevez une prime de l'état de 2000W";
				break;
			case BONUS_PRIX_NOBEL:
				joueur.setSolde(joueur.getSolde() + 5000L);
				message = "Vous recevez le prix Nobel pour vos recherches et une prime de 5000W";
				break;
			case IMPOT_POLUTION:
				Long impot = 0L;
				for (int idProp : joueur.getListePropriete()) {
					Propriete p = (Propriete) MonopolyBD.getCaseById(idProp);
					impot += p.getCoefPollution() * 500L;
				}
				
				if(joueur.getSolde() < impot) {
					joueur.setEstElimine(true);
					joueur.setListePropriete(new ArrayList<>());
					joueur.setListeCarte(new ArrayList<>());
					MonopolyBD.plateau.setPot(MonopolyBD.plateau.getPot() + joueur.getSolde());
					joueur.setSolde(0L);
					
					//mettre toutes les idJoueurs des proprietes du joueur à 0
					for (int idProp : joueur.getListePropriete()) {
						Propriete p = (Propriete) MonopolyBD.getCaseById(idProp);
						p.setJoueur(0);
					}
				}
				else {
					joueur.setSolde(joueur.getSolde() - impot);
					MonopolyBD.plateau.setPot(MonopolyBD.plateau.getPot() + impot);
				}
				
				message = "Impots de pollution : payez " + impot + "W";
				break;
			case PARC_GRATUIT:
				joueur.setSolde(joueur.getSolde() + MonopolyBD.plateau.getPot());
				message = "Vous remportez le pot de " + MonopolyBD.plateau.getPot() + "W !";
				MonopolyBD.plateau.setPot(0L);
				break;
			case SAFE: 
				break;
			case TAXE_ENERGIE: 
				Long taxe = 0L;
				int nbProp = joueur.getListePropriete().size();
				taxe = 500L * nbProp;
				if(joueur.getSolde() < taxe) {
					joueur.setEstElimine(true);
					joueur.setListePropriete(new ArrayList<>());
					joueur.setListeCarte(new ArrayList<>());
					MonopolyBD.plateau.setPot(MonopolyBD.plateau.getPot() + joueur.getSolde());
					joueur.setSolde(0L);
					
					//mettre toutes les idJoueurs des proprietes du joueur à 0
					for (int idProp : joueur.getListePropriete()) {
						Propriete p = (Propriete) MonopolyBD.getCaseById(idProp);
						p.setJoueur(0);
					}
				}
				else {
					joueur.setSolde(joueur.getSolde() - taxe);
					MonopolyBD.plateau.setPot(MonopolyBD.plateau.getPot() + taxe);
					message = "Payez la taxe énergie de " + taxe + "W";
				}
				break;
			case VISITE_PRISON:
				break;
		default:
			throw new Exception("Cette case n'existe pas");
		}
		return Response.ok(message)
				.header("Access-Control-Allow-Origin", "*").build();
	}
	
	@GET
	@Path("plateau")
	public Response getPlateau() {
		return Response.ok(MonopolyBD.plateau)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
				.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
	}
	
	@GET
	@Path("carte-prison/{idJoueur}")
	@Produces(MediaType.TEXT_PLAIN + ";charset=UTF-8")
	public Response UseCartePrison(@PathParam("idJoueur") Long idJoueur) throws Exception {
		Joueur joueur = new Joueur();
		for (Joueur current : MonopolyBD.getJoueurs()) {
			if (idJoueur == current.getId()) {
				joueur = current;
			}
		}
		
		if(joueur.getNbTourPrison() == 3) {
			joueur.setPosition(10);
			MonopolyBD.toutesLesCases.get(30).removeJoueur(joueur);
			MonopolyBD.toutesLesCases.get(10).addJoueur(joueur);
			joueur.setNbTourPrison(0);
			
			return Response.ok("Pas besoin d'utiliser votre carte \"Sortie de prison\", vous sortez de prison")
					.header("Access-Control-Allow-Origin", "*")
					.build();
		}
		
		if(joueur.getNbTourPrison() > 3) {
			throw new Exception("Vous êtes déjà sorti de prison");
		}
		
		if(joueur.getListeCarte().isEmpty()) {
			throw new Exception("Vous n'avez pas de carte \"Sortez de prison\" ");
		}
		else {
			joueur.getListeCarte().remove(0);
			joueur.setPosition(10);
			MonopolyBD.toutesLesCases.get(30).removeJoueur(joueur);
			MonopolyBD.toutesLesCases.get(10).addJoueur(joueur);
			joueur.setNbTourPrison(0);
		}
		
		return Response.ok("Vous avez utilisé votre carte \"Sortie de prison\" ")
				.header("Access-Control-Allow-Origin", "*")
				.build();
	}
	
	@GET
	@Path("amande-prison/{idJoueur}")
	@Produces(MediaType.TEXT_PLAIN + ";charset=UTF-8")
	public Response payerPrison(@PathParam("idJoueur") Long idJoueur) throws Exception {
		Joueur joueur = new Joueur();
		for (Joueur current : MonopolyBD.getJoueurs()) {
			if (idJoueur == current.getId()) {
				joueur = current;
			}
		}
		
		joueur.setNbTourPrison(joueur.getNbTourPrison() + 1);
		
		if(joueur.getNbTourPrison() == 3) {
			joueur.setPosition(10);
			MonopolyBD.toutesLesCases.get(30).removeJoueur(joueur);
			MonopolyBD.toutesLesCases.get(10).addJoueur(joueur);
			joueur.setNbTourPrison(0);
			
			return Response.ok("Pas besoin de payer l'amende, vous sortez de prison")
					.header("Access-Control-Allow-Origin", "*")
					.build();
		}
		
		if(joueur.getSolde() < 5000) {
			throw new Exception("Vous n'avez pas assez d'argent pour payer l'amende de sortie de prison(5000 Watts)");
		}
		else {
			joueur.setSolde(joueur.getSolde() - 5000L);
			MonopolyBD.plateau.setPot(MonopolyBD.plateau.getPot() + 5000L);
			MonopolyBD.toutesLesCases.get(30).removeJoueur(joueur);
			joueur.setPosition(10);
			MonopolyBD.toutesLesCases.get(10).addJoueur(joueur);
			joueur.setNbTourPrison(0);
		}
		return Response.ok("Vous avez payé l'amende de 5000W, vous sortez de prison")
				.header("Access-Control-Allow-Origin", "*")
				.build();
	}
	
	/**
	 * la transaction se fera a partir du joueur 1 vers le joueur 2
	 * @param idJoueur1
	 * @param idJoueur2
	 * @param montant
	 * @throws Exception
	 */
	@GET
	@Path("/transaction")
	public Response transaction(@QueryParam("idJoueur1") Integer idJoueur1, @QueryParam("idJoueur2") Integer idJoueur2,
			@QueryParam("montant") Long montant) throws Exception {
		
		String message= "";
		Joueur j1 = new Joueur();
		Joueur j2 = new Joueur();
		//on recupere le premier joueur
		for (Joueur current : MonopolyBD.getJoueurs()) {
			if (idJoueur1 == current.getId()) {
				j1 = current;
			}
		}
		//on recupere le deuxieme joueur
		for (Joueur current : MonopolyBD.getJoueurs()) {
			if (idJoueur2 == current.getId()) {
				j2 = current;
			}
		}
		//on verifie la carte du joueur 1
		String carteNfc1 = readNfc().getEntity().toString();
		while(!carteNfc1.equals(j1.getNfcTag())) {
			//boucle vide juste pour vérifier  que la carte nfc est bonne 
		}
		//on verifie la carte du joueur 2
		String carteNfc2 = readNfc().getEntity().toString();
		while(!carteNfc2.equals(j2.getNfcTag())) {
			//boucle vide juste pour vérifier  que la carte nfc est bonne 
		}
		//si tout est bon on fait la transaction
		if(j1.getSolde() < montant) {
			j1.setEstElimine(true);
			j1.setListePropriete(new ArrayList<>());
			j1.setListeCarte(new ArrayList<>());
			j2.setSolde(j2.getSolde() + j1.getSolde());
			j1.setSolde(0L);
			
			//mettre toutes les idJoueurs des proprietes du joueur à 0
			for (int idProp : j1.getListePropriete()) {
				Propriete p = (Propriete) MonopolyBD.getCaseById(idProp);
				p.setJoueur(0);
			}
			message = "Le joueur " + j1.getNom() + " n'a pas assez d'argent pour payer le loyer au joueur " + j2.getNom() + ". Il est donc éliminé de la partie";
			return Response.ok(message)
					.header("Access-Control-Allow-Origin", "*")
					.build();
		}
		else{
			j1.setSolde(j1.getSolde() - montant);
			j2.setSolde(j2.getSolde() + montant);
		}
		message = "Le joueur " + j1.getNom() + " a versé " + montant + "W au joueur " + j2.getNom();
		return Response.ok(message)
				.header("Access-Control-Allow-Origin", "*")
				.build();
	}
}
