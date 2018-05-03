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
	public Response getJoueur(@PathParam("id") int idJoueur) {
		for (Joueur current : MonopolyBD.getJoueurs()) {
			if (idJoueur == current.getId()) {
				return Response.ok(current)
						.header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
						.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
			}
		}
		return null;
	}

	@GET
	@Path("/proprietes")
	public Response getProprietes(){
		return Response.ok(MonopolyBD.getProprietes())
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
				.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
	}

	@GET
	@Path("/cases")
	public Response getCase(){
		return Response.ok(MonopolyBD.getCases())
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
				.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
	}

	@GET
	@Path("/cases/{id}")
	public Response getCase(@PathParam("id") Long idCase){
		for (Case current : MonopolyBD.getCases()) {
			if (idCase.equals(current.getIdCase())) {
				return Response.ok(current)
						.header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
						.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
			}
		}
		return null;
	}

	@GET
	@Path("/proprietes/{nom}")
	public Response getPropriete(@PathParam("nom") final String nom) {
		for (Propriete current : MonopolyBD.getProprietes()) {
			if (nom.equals(current.getName())) {
				return Response.ok(current)
						.header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
						.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
			}
		}
		return null;
	}

	@GET
	@Path("/proprietes/updateplusvalue")
	public Response updatePlusValuePropriete(@QueryParam("nom") String nom, @QueryParam("plusValue") Long plusValue) {

		for (Propriete current : MonopolyBD.getProprietes()) {
			if (nom.equals(current.getName())) {
				current.setPlusValue(plusValue);
				System.out.println("Propri�t� modifi�e");
				return Response.noContent()
						.header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
						.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
			}
		}
		return null;
	}

	@GET
	@Path("/proprietes/updateloyer")
	public Response updateLoyerPropriete(@QueryParam("nom") String nom, @QueryParam("loyer") Long loyer) {

		for (Propriete current : MonopolyBD.getProprietes()) {
			if (nom.equals(current.getName())) {
				current.setLoyer(loyer);
				System.out.println("Propri�t� modifi�e");
				return Response.noContent()
						.header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
						.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
			}
		}
		return null;
	}

	/*public String readNFC() throws IOException {
		String output = null;
		String uri = "http://localhost:8080/CustomerService/rest/customers/1";
		URL url = new URL(uri);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept", "text/plain");
		if(connection.getResponseCode() != 200) {
			InputStreamReader in = new InputStreamReader(connection.getInputStream());
			BufferedReader br = new BufferedReader(in);
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            connection.disconnect();
		}
		return output;
	}*/
	
	@GET
	@Path("/achat")
	/**Permet d'acheter une propriete si on a assez d'argent, que la prop est a vendre et qu'on passe sa carte*/
	public Response achatPropriete(@QueryParam("idCase") Long idCase, @QueryParam("idJoueur") int idJoueur) throws Exception {
		Joueur joueur = new Joueur();
		Propriete prop = new Propriete();
		for(Propriete p : MonopolyBD.getProprietes()) {
			if(idCase.equals(p.getIdCase())) {
				prop = p;
			}
		}
		for(Joueur j : MonopolyBD.getJoueurs()) {
			if(idJoueur==j.getId()) {
				joueur = j;
			}
		}
		
		if(prop.getIdJoueur() == 0 && joueur.getSolde() >= prop.getPrixAchat()) {
			
			//Lecture carte nfc
			if (readNfc().equals(joueur.getNfcTag())) {
			
			
				//traitement cot� joueur
				joueur.setSolde(joueur.getSolde()-prop.getPrixAchat());
				joueur.addPropriete(prop);
				//traitement cot� propriete
				prop.setJoueur(joueur.getId());
				
				return Response.ok().build();
			}
		}
		System.out.println("Echec achat : "+prop.getIdJoueur()+" "+joueur.getSolde() + " " +readNfc());
		return Response.notModified().build();
	}
	
	@GET
	@Path("/readNfc")
	@Produces(MediaType.TEXT_PLAIN)
	public String readNfc() throws Exception {
		String result = "";
		int retour = 0;
		
		try {
			retour = Nfc.start();
		} catch (Exception e) {
			retour = 0;
			e.printStackTrace();
			throw new Exception("Le lecteur NFC n'a pas d�marr� correctement.");
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
		
		result = Integer.toUnsignedString(retour);
		return result;
	}
	
	@GET
	@Path("deplacement/{idJoueur}")
	public Response deplacement(@PathParam("idJoueur") Long idJoueur, @QueryParam("resultDes") Integer resultDes) throws Exception {
		if(resultDes < 2 || resultDes > 12) {
			throw new Exception("Il est impossible de faire ce r�sultats avec 2 d�s!!");
		}
		Joueur joueur = null;
		for(Joueur j : MonopolyBD.getJoueurs()) {
			if(idJoueur==j.getId()) {
				joueur = j;
			}
		}
		joueur.setPosition((joueur.getPosition() + resultDes) % 40);
		return Response.noContent()
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
				.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
	}
	
	@GET
	@Path("case-speciale/{idCase}")
	public Response gestionCaseSpeciale(@PathParam("idCase") Long idCase, @QueryParam("idJoueur") Integer idJoueur) throws Exception {
		Case positionCase = new Case();
		Joueur joueur = new Joueur();
		for(Case current : MonopolyBD.listCase) {
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
				else if(carte.getContenu() == "Votre campagne de publicit� rapporte 5000 watts") {
					joueur.setSolde(joueur.getSolde() + 5000L);
				}
				else if(carte.getContenu() == "Un incident s'est produit dans l'une de vos propri�t�s, payez 7500 watts de r�paration") {
					joueur.setSolde(joueur.getSolde() - 7500L);
					MonopolyBD.plateau.setPot(MonopolyBD.plateau.getPot() + 7500L);
				}
				break;
			case CASE_CAISSE_COMMUNAUTE:
				random = (int) (Math.random() * MonopolyBD.cartesCaisseCommunaute.size());
				carte = MonopolyBD.cartesCaisseCommunaute.get(random);
				if(carte.getContenu() == "Allez � la case d�part!") {
					joueur.setPosition(0);
				}
				else if(carte.getContenu() == "Allez � la case prison!") {
					joueur.setPosition(10);
				}
				else if(carte.getContenu() == "Sortez de prison") {
					joueur.getListeCarte().add(carte);
				}
				break;
			case ALLER_PRISON: 
				joueur.setPosition(10);
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
			case PRISON: 
				//reste a faire
				// a voir comment on gere le nombre de tour dans le back
				// comment on gere les d�s pour voir si c'est un double
				// Je pense il faut faire un WS a part pour cette case => le nombre de chaque d� en entr�e + le nombre de tour deja en prison 
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
	
}
