package com.supinfos.articles.restserver.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.supinfos.articles.restserver.bd.MonopolyBD;
import com.supinfos.articles.restserver.entities.Joueur;
import com.supinfos.articles.restserver.entities.Propriete;

@Path("/monopoly")
@Produces(MediaType.APPLICATION_JSON)
public class MonopolyResource {
	@GET
	@Path("/proprietes")
	public List<Propriete> getProprietes(){
		return MonopolyBD.getProprietes();
	}

	@GET
	@Path("/joueurs")
	public List<Joueur> getJoueurs(){
		return MonopolyBD.getJoueurs();
	}
	
	@GET
    @Path("/proprietes/{nom}")
    public Propriete getPropriete(@PathParam("nom") final String nom) {
    	  for (Propriete current : MonopolyBD.getProprietes()) {
              if (nom.equals(current.getName())) {
                  return current;
              }
          }
          return null;
    }

	@GET
	@Path("/proprietes/updateplusvalue")
	public void updatePlusValuePropriete(@QueryParam("nom") String nom, @QueryParam("plusValue") Long plusValue) {
		
		for (Propriete current : MonopolyBD.getProprietes()) {
            if (nom.equals(current.getName())) {
            	current.setPlusValue(plusValue);      	
            }
        }
		System.out.println("Propriété modifiée");
	}

	@GET
	@Path("/proprietes/updateloyer")
	public void updateLoyerPropriete(@QueryParam("nom") String nom, @QueryParam("loyer") Long loyer) {
		
		for (Propriete current : MonopolyBD.getProprietes()) {
			if (nom.equals(current.getName())) {
				current.setLoyer(loyer);
			}
		}
		System.out.println("Propriété modifiée");
	}
}
