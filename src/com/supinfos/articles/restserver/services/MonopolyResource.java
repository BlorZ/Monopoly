package com.supinfos.articles.restserver.services;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
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
				System.out.println("Propriété modifiée");
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
				System.out.println("Propriété modifiée");
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
	
	@PUT
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
			
			
				//traitement coté joueur
				joueur.setSolde(joueur.getSolde()-prop.getPrixAchat());
				joueur.addPropriete(prop);
				//traitement coté propriete
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
		
		result = Integer.toUnsignedString(retour);
		return result;
	}
	
	@GET
	@Path("deplacement/{idJoueur}")
	public void deplacement(@PathParam("idJoueur") Long idJoueur, @QueryParam("resultDes") Integer resultDes) {
		Joueur joueur = null;
		for(Joueur j : MonopolyBD.getJoueurs()) {
			if(idJoueur==j.getId()) {
				joueur = j;
			}
		}
		joueur.setPosition(joueur.getPosition() + resultDes);
	}
	
	@GET
	@Path("case-speciale/{idCase}")
	public void gestionCaseSpeciale(@PathParam("idCase") Long idCase, @QueryParam("idJoueur") Integer idJoueur) throws Exception {
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
					joueur.setPosition(10);
				}
				else if(carte.getContenu() == "Sortez de prison") {
					joueur.getListeCarte().add(carte);
				}
				break;
		default:
			throw new Exception("Cette case n'existe pas");
		}
	}
	
}
