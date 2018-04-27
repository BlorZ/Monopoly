package com.supinfos.articles.restserver.entities;

public class Plateau {
	private Meteo meteo;
	private Long pot;
	
	public Plateau(){
		super();
	}

	/**
	 * @return the meteo
	 */
	public Meteo getMeteo() {
		return meteo;
	}

	/**
	 * @param meteo the meteo to set
	 */
	public void setMeteo(Meteo meteo) {
		this.meteo = meteo;
	}

	/**
	 * @return the pot
	 */
	public Long getPot() {
		return pot;
	}

	/**
	 * @param pot the pot to set
	 */
	public void setPot(Long pot) {
		this.pot = pot;
	}
}
