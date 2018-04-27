package com.supinfos.articles.restserver.entities;

import java.util.List;

public class Joueur {

	private long id;
    private String nom;
    private Pion pion;
    private List<Propriete> listePropriete;
    private Long solde;
    private int position;
    private List<Carte> listeCarte;
    
    public Joueur(long _id, String _nom, Pion _pion, List<Propriete> _listePropriete,
    		Long _solde, int _position, List<Carte> _listeCarte) {
    	id = _id;
    	nom = _nom;
    	pion = _pion; 
    	listePropriete = _listePropriete;
    	solde = _solde;
    	position = _position;
    	listeCarte = _listeCarte;
    }
    
    public Joueur() {
    	super();
    }
    
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * @return the nom
	 */
	public String getNom() {
		
		return nom;
	}
	
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * @return the pion
	 */
	public Pion getPion() {
		return pion;
	}
	
	/**
	 * @param pion the pion to set
	 */
	public void setPion(Pion pion) {
		this.pion = pion;
	}
	
	/**
	 * @return the listePropriete
	 */
	public List<Propriete> getListePropriete() {
		return listePropriete;
	}
	
	/**
	 * @param listePropriete the listePropriete to set
	 */
	public void setListePropriete(List<Propriete> listePropriete) {
		this.listePropriete = listePropriete;
	}
	
	/**
	 * @return the solde
	 */
	public Long getSolde() {
		return solde;
	}
	
	/**
	 * @param solde the solde to set
	 */
	public void setSolde(Long solde) {
		this.solde = solde;
	}
	
	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}
	
	/**
	 * @param position the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}
	
	/**
	 * @return the listeCarte
	 */
	public List<Carte> getListeCarte() {
		return listeCarte;
	}
	
	/**
	 * @param listeCarte the listeCarte to set
	 */
	public void setListeCarte(List<Carte> listeCarte) {
		this.listeCarte = listeCarte;
	}
}
