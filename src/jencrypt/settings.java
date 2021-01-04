package jencrypt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class settings {
	private static String username = "";
	private static String password = "";
	private static String salt = "";
	
	
	Properties mainprop = new Properties();
	
	// Load properties
	public void loadProperties() {
		try {
			FileInputStream finput = new FileInputStream("pw.config");
			mainprop.load(finput);
			finput.close();
			
			username = mainprop.getProperty("pusername");
			password = mainprop.getProperty("ppassword");
			salt = mainprop.getProperty("psalt");
			
			System.out.println("Load propetries ok");
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	// Save properties
	public void saveProperties() {
		mainprop.put("pusername", username);
		mainprop.put("ppassword", password);
		mainprop.put("psalt", salt);
		
		try {
			FileOutputStream foutput = new FileOutputStream("pw.config");
			mainprop.store(foutput, "Authentification file");
			foutput.close();
			
			System.out.println("Save properties ok");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	// Getters
	public final String getAuthentification() {
		return username + "$" + password + "$" + salt;
	}
	
	// Setters
	public void setPassword(String pw) {
		password = pw;
	}
	
	public void setUsername(String user) {
		username = user;
	}
	
	public void setSalt(String slt) {
		salt = slt;
	}
	
}
