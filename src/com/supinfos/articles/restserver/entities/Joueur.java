package com.supinfos.articles.restserver.entities;

import java.util.ArrayList;
import java.util.List;

public class Joueur {

	private int id;
    private String nom;
    private Pion pion;
    private ArrayList<Integer> listePropriete;
    private Long solde;
    private int position;
    private List<Carte> listeCarte;
    private String nfcTag;
    private int nbTourPrison;
    private boolean estElimine;
    
    public Joueur(int _id, String _nom, Pion _pion, ArrayList<Integer> _listePropriete,
    		Long _solde, int _position, List<Carte> _listeCarte, String _nfcTag, int _nbTourPrison, boolean _estElimine) {
    	id = _id;
    	nom = _nom;
    	pion = _pion; 
    	listePropriete = _listePropriete;
    	solde = _solde;
    	position = _position;
    	listeCarte = _listeCarte;
    	nfcTag = _nfcTag;
    	nbTourPrison = _nbTourPrison;
    	estElimine = _estElimine;
    }
    
    public Joueur() {
    	super();
    }
    
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
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
	public ArrayList<Integer> getListePropriete() {
		return listePropriete;
	}
	
	/**
	 * @param listePropriete the listePropriete to set
	 */
	public void setListePropriete(ArrayList<Integer> listePropriete) {
		this.listePropriete = listePropriete;
	}
	
	public void addPropriete(int prop) {
		this.listePropriete.add(prop);
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
	
	public String getNfcTag() {
		return this.nfcTag;
	}

	public int getNbTourPrison() {
		return nbTourPrison;
	}

	public void setNbTourPrison(int nbTourPrison) {
		this.nbTourPrison = nbTourPrison;
	}

	public boolean isEstElimine() {
		return estElimine;
	}

	public void setEstElimine(boolean estElimine) {
		this.estElimine = estElimine;
	}
	
}
