package com.supinfos.articles.restserver.entities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Nfc {
	
	public static String address[] = {"http://localhost:8081/nfcApp4/api","http://localhost:8081/nfcApp4/api"}; //mettre les bonne adresses
	
	public static int start() throws IOException {
		URL url1;
			url1 = new URL(address[0] + "/nfc/start");
		
		HttpURLConnection con = (HttpURLConnection) url1.openConnection();
			con.setRequestMethod("GET");
		
		//Faire requete
		int status = con.getResponseCode();
		
		//Lecture reponse
		BufferedReader in = new BufferedReader(
				 new InputStreamReader(con.getInputStream()));
			
		String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
			    content.append(inputLine);
			}
		in.close();
		
		con.disconnect();
		
		System.out.println(status);
		return 1;
	}
	
	public static int stop() throws IOException {
		URL url1;
			url1 = new URL(address[0] + "/nfc/stop");
		
		HttpURLConnection con = (HttpURLConnection) url1.openConnection();
			con.setRequestMethod("GET");
		
		//Faire requete
		int status = con.getResponseCode();
		
		//Lecture reponse
		BufferedReader in = new BufferedReader(
				 new InputStreamReader(con.getInputStream()));
			
		String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
			    content.append(inputLine);
			}
		in.close();
		
		con.disconnect();
		
		System.out.println(status);
		return 1;
	}
	
	public static String read() throws IOException {
		String id;
		URL url1;
			url1 = new URL(address[0] + "/nfc/read");
		
		HttpURLConnection con = (HttpURLConnection) url1.openConnection();
			con.setRequestMethod("GET");
		
		//Faire requete
		int status = con.getResponseCode();
		
		//Lecture reponse
		BufferedReader in = new BufferedReader(
				 new InputStreamReader(con.getInputStream()));
			
		String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
			    content.append(inputLine);
			}
		in.close();
		
		con.disconnect();
		
		id = content.toString();
		System.out.println(status + " " + id);
		
		return id;
	}
	
	
	
	


}
