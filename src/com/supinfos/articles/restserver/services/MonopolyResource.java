package com.supinfos.articles.restserver.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.supinfos.articles.restserver.bd.MonopolyBD;
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
	//Permet d'acheter une propriete si on a assez d'argent, que la prop est a vendre et qu'on passe sa carte
	public Response achatPropriete(@QueryParam("idCase") Long idCase, @QueryParam("idJoueur") int idJoueur) {
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
		System.out.println("echech achat : "+prop.getIdJoueur()+" "+joueur.getSolde() + " " +readNfc());
		return Response.notModified().build();
		
		
	}
	
	@GET
	@Path("/readNfc")
	@Produces(MediaType.TEXT_PLAIN)
	public String readNfc() {
		String result = "";
		int retour = 0;
		
		try {
			retour = Nfc.start();
		} catch (IOException e) {
			retour = 0;
			e.printStackTrace();
		}
		
		try {
			result = Nfc.read();
		} catch (IOException e) {
			retour = 0;
			e.printStackTrace();
		}
		
		try {
			retour = Nfc.stop();
		} catch (IOException e) {
			retour = 0;
			e.printStackTrace();
		}
		
		//result = Integer.toUnsignedString(retour);
		return result;
	}
	
}
