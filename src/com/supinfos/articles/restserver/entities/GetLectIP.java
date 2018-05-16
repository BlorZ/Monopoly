package com.supinfos.articles.restserver.entities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GetLectIP {
	
	static ProcessBuilder builder = new ProcessBuilder("C:\\Users\\croyg\\Desktop\\testip.bat");
	static Process process = null;
	static BufferedReader reader = null;
	
	public static String getIP() {
		String line = "";
		
		if (process == null) {
			
			builder.redirectErrorStream(true);
			try {
				process = builder.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			InputStream is = process.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is));
			System.out.println("getIP Started");
		}
		
		try {
			while ((line = reader.readLine()) != null) {
				if(line.length() > 2) { //ligne pas vide
					if(line.contains("IP") && !line.contains("echo")) {//2eme conditions pour test
						System.out.println("IP trouvé = " + line.substring(5, line.length()));
						return line.substring(5, line.length());
					}
				}	
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERROR";
		}
		
		return "ERROR";
	}

}
