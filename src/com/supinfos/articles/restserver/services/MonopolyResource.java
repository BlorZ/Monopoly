package com.supinfos.articles.restserver.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.supinfos.articles.restserver.bd.MonopolyBD;
import com.supinfos.articles.restserver.entities.Joueur;
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
	public Response getJoueur(@PathParam("id") Long idJoueur) {
		for (Joueur current : MonopolyBD.getJoueurs()) {
			if (idJoueur.equals(current.getId())) {
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
}
