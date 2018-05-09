package com.supinfos.articles.restserver.services;

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
	@Path("/proprietes/updateplusvalue")
	public Response updatePlusValuePropriete(@QueryParam("nom") String nom, @QueryParam("plusValue") Long plusValue) throws Exception {

		for (Case current : MonopolyBD.getAllCases()) {
			if (nom.equals(((Propriete) current).getName())) {
				((Propriete) current).setPlusValue(plusValue);
				System.out.println("Propriété modifiée");
				return Response.noContent()
						.header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
						.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
			}
		}
		throw new Exception("Cette propriete n'existe pas!");
		
	}

	@GET
	@Path("/proprietes/updateloyer")
	public Response updateLoyerPropriete(@QueryParam("nom") String nom, @QueryParam("loyer") Long loyer) throws Exception {

		for (Case current : MonopolyBD.getAllCases()) {
			if (nom.equals(((Propriete) current).getName())) {
				((Propriete) current).setLoyer(loyer);
				System.out.println("Propriété modifiée");
				return Response.noContent()
						.header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
						.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
			}
		}
		throw new Exception("Cette propriete n'existe pas!");
	}

	@GET
	@Path("/achat")
	/**Permet d'acheter une propriete si on a assez d'argent, que la prop est a vendre et qu'on passe sa carte*/
	public Response achatPropriete(@QueryParam("idCase") Long idCase, @QueryParam("idJoueur") int idJoueur) throws Exception {
		Joueur joueur = new Joueur();
		Propriete prop = new Propriete();
		String carte = "";
		
		for(Case p : MonopolyBD.getAllCases()) {
			if(idCase.equals(p.getIdCase())) {
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
			//System.out.println(carte);
			
			if (carte.equals(joueur.getNfcTag())) {
			
				//traitement coté joueur
				joueur.setSolde(joueur.getSolde()-prop.getPrixAchat());
				joueur.addPropriete(prop);
				//traitement coté propriete
				prop.setJoueur(joueur.getId());
				
				return Response.ok().build();
			}
		}
		
		return Response.notModified().build();
		
	}
	
	@GET
	@Path("/readNfc")
	@Produces(MediaType.TEXT_PLAIN)
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
		
		Thread.sleep(5000);
		
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
		//result = Integer.toUnsignedString(retour); ne pas decommenter
		return Response.ok(result).build();
	}
	
	@GET
	@Path("/deplacement")
	public Response deplacement(@QueryParam("idJoueur") long idJoueur, @QueryParam("resultDes") int resultDes) throws Exception {
		int newPos = 0;
		if(resultDes < 2 || resultDes > 12) {
			throw new Exception("Il est impossible de faire ce résultats avec 2 dés!!");
		}
		Joueur joueur = null;
		for(Joueur j : MonopolyBD.getJoueurs()) {
			if(idJoueur==j.getId()) {
				joueur = j;
			}
		}
		
		//Ajout pour mettre joueur sur case
		Case dest = MonopolyBD.getCaseById((joueur.getPosition() + resultDes) % 40);
		dest.addJoueur(joueur);
		
		
		joueur.setPosition((joueur.getPosition() + resultDes) % 40);
		

		return Response.ok()
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
				.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
	}
	
	@GET
	@Path("case-speciale/{idCase}")
	public Response gestionCaseSpeciale(@PathParam("idCase") Long idCase, @QueryParam("idJoueur") Integer idJoueur) throws Exception {
		Case positionCase = new Case();
		Joueur joueur = new Joueur();
		for(Case current : MonopolyBD.toutesLesCases) {
			if(idCase.equals(current.getIdCase())) {
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
				joueur.setSolde(joueur.getSolde() + 20000L);
				for(Propriete p : joueur.getListePropriete()) {
					joueur.setSolde(joueur.getSolde() - p.getLoyer());
				}
				break;
			case CASE_CHANCE:
				int random = (int) (Math.random() * MonopolyBD.cartesChance.size());
				Carte carte = MonopolyBD.cartesChance.get(random);
				if(carte.getContenu() == "Sortez de prison") {
					joueur.getListeCarte().add(carte);
				}
				else if(carte.getContenu() == "Votre campagne de publicité rapporte 5000 watts") {
					joueur.setSolde(joueur.getSolde() + 5000L);
				}
				else if(carte.getContenu() == "Un incident s'est produit dans l'une de vos propriétés, payez 7500 watts de réparation") {
					joueur.setSolde(joueur.getSolde() - 7500L);
					MonopolyBD.plateau.setPot(MonopolyBD.plateau.getPot() + 7500L);
				}
				break;
			case CASE_CAISSE_COMMUNAUTE:
				random = (int) (Math.random() * MonopolyBD.cartesCaisseCommunaute.size());
				carte = MonopolyBD.cartesCaisseCommunaute.get(random);
				if(carte.getContenu() == "Allez à la case départ!") {
					joueur.setPosition(0);
				}
				else if(carte.getContenu() == "Allez à la case prison!") {
					joueur.setPosition(99);
					MonopolyBD.toutesLesCases.get(99).addJoueur(joueur);
				}
				else if(carte.getContenu() == "Sortez de prison") {
					joueur.getListeCarte().add(carte);
				}
				break;
			case ALLER_PRISON: 
				joueur.setPosition(99);
				MonopolyBD.toutesLesCases.get(99).addJoueur(joueur);
				break;
			case BONUS_PRIME_ETAT:
				joueur.setSolde(joueur.getSolde() + 5000L);
				break;
			case BONUS_PRIX_NOBEL:
				joueur.setSolde(joueur.getSolde() + 10000L);
				break;
			case IMPOT_POLUTION:
				joueur.setSolde(joueur.getSolde() - 7500L);
				MonopolyBD.plateau.setPot(MonopolyBD.plateau.getPot() + 7500L);
				break;
			case PARC_GRATUIT:
				joueur.setSolde(joueur.getSolde() + MonopolyBD.plateau.getPot());
				MonopolyBD.plateau.setPot(0L);
				break;
			case SAFE: 
				break;
			case TAXE_ENERGIE: 
				joueur.setSolde(joueur.getSolde() - 5000L);
				MonopolyBD.plateau.setPot(MonopolyBD.plateau.getPot() + 5000L);
				break;
			case VISITE_PRISON:
				break;
			case PRISON: 
				//reste a faire
				// a voir comment on gere le nombre de tour dans le back
				// comment on gere les dés pour voir si c'est un double
				// Je pense il faut faire un WS a part pour cette case => le nombre de chaque dé en entrée + le nombre de tour deja en prison 
				break;
		default:
			throw new Exception("Cette case n'existe pas");
		}
		return Response.noContent()
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
				.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
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
	public Response UseCartePrison(@PathParam("idJoueur") Long idJoueur) throws Exception {
		Joueur joueur = new Joueur();
		for (Joueur current : MonopolyBD.getJoueurs()) {
			if (idJoueur == current.getId()) {
				joueur = current;
			}
		}
		
		joueur.setNbTourPrison(joueur.getNbTourPrison() + 1);
		
		if(joueur.getNbTourPrison() == 3) {
			throw new Exception("Vous êtes déjà sorti de prison");
		}
		
		if(joueur.getListeCarte().isEmpty()) {
			throw new Exception("Vous n'avez pas de carte \"Sortez de prison\" ");
		}
		else {
			joueur.getListeCarte().remove(0);
			joueur.setPosition(10);
			MonopolyBD.toutesLesCases.get(99).removeJoueur(joueur);
			MonopolyBD.toutesLesCases.get(10).addJoueur(joueur);
			joueur.setNbTourPrison(0);
		}
		
		return Response.ok()
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
				.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
	}
	
	@GET
	@Path("amande-prison/{idJoueur}")
	public Response PayerPrison(@PathParam("idJoueur") Long idJoueur) throws Exception {
		Joueur joueur = new Joueur();
		for (Joueur current : MonopolyBD.getJoueurs()) {
			if (idJoueur == current.getId()) {
				joueur = current;
			}
		}
		
		joueur.setNbTourPrison(joueur.getNbTourPrison() + 1);
		
		if(joueur.getNbTourPrison() == 3) {
			throw new Exception("Vous êtes déjà sorti de prison");
		}
		
		
		if(joueur.getSolde() < 5000) {
			throw new Exception("Vous n'avez pas assez d'argent pour payer l'amande de sortie de prison(5000 Watts)");
		}
		else {
			joueur.setSolde(joueur.getSolde() - 5000L);
			MonopolyBD.plateau.setPot(MonopolyBD.plateau.getPot() + 5000L);
			joueur.setPosition(10);
			MonopolyBD.toutesLesCases.get(99).removeJoueur(joueur);
			MonopolyBD.toutesLesCases.get(10).addJoueur(joueur);
			joueur.setNbTourPrison(0);
		}
		return Response.ok()
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
				.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
	}
	
	@GET
	@Path("double-prison/{idJoueur}")
	public Response doublePrison(@PathParam("idJoueur") Long idJoueur, @QueryParam("des1") int des1, @QueryParam("des2") int des2) throws Exception {
		Joueur joueur = new Joueur();
		for (Joueur current : MonopolyBD.getJoueurs()) {
			if (idJoueur == current.getId()) {
				joueur = current;
			}
		}
		
		joueur.setNbTourPrison(joueur.getNbTourPrison() + 1);
		
		if(joueur.getNbTourPrison() == 3) {
			throw new Exception("Vous êtes déjà sorti de prison");
		}
		
		if(des1 != des2) {
			throw new Exception("Vous n'avez pas fait de double");
		}
		else {
			joueur.setPosition(10);
			MonopolyBD.toutesLesCases.get(99).removeJoueur(joueur);
			MonopolyBD.toutesLesCases.get(10).addJoueur(joueur);
			joueur.setNbTourPrison(0);
		}
		return Response.ok()
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
				.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
	}
}
