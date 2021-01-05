package jencrypt;

import javax.swing.JOptionPane;

public class applet {
	// Objects
	encrypt enc = new encrypt();
	settings set = new settings();

	public static void main(String[] args) {
		applet app = new applet();
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				// On exit
				app.exit();
			}
		}));
		app.init();
	}
	
	private void init() {
		set.loadProperties();
		
		int res = JOptionPane.showConfirmDialog(null, "Create new user?", "Applet start", JOptionPane.YES_NO_CANCEL_OPTION);
		switch (res) {
			case JOptionPane.YES_OPTION:
				newUser();
				break;
			case JOptionPane.NO_OPTION:
				login();
				break;
			default:
				break;
		}
	}
	
	private void newUser() {
		System.out.println("new user");
		
		String username = JOptionPane.showInputDialog("Benutzername:");
		
		enc.createUser(username, JOptionPane.showInputDialog("Passwort:"));
		
	}
	
	private void login() {
		System.out.println("login");
		
		String username = JOptionPane.showInputDialog("Benutzername:");
		
		if (enc.verifyPassword(username, JOptionPane.showInputDialog("Passwort:"))) {
			System.out.println("Login erfolgreich");
		} else {
			System.out.println("Falsches Passwort oder falscher Benutzername");
		}
	}
	
	private void exit() {
		set.saveProperties();
	}
	
}
