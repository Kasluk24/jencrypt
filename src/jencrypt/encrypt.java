package jencrypt;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class encrypt {
	// Objects
	settings sett = new settings();
	
	private static final Random RANDOM = new SecureRandom();
	private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final int ITERATIONS = 10000;
	private static final int KEYLENGTH = 256;
	private static final int SALTLENGTH = 30;
	
	public void createUser(String username, String password) {
		String salt = getSalt();
		sett.setSalt(salt);
		sett.setPassword(createPassword(password, salt));
		sett.setUsername(username);
	}
	
	// Create salt
	private static String getSalt() {
		StringBuilder strbSalt = new StringBuilder(SALTLENGTH);
		
		for (int i = 0; i < SALTLENGTH; i++) {
			strbSalt.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		
		return strbSalt.toString();
	}
	
	// Create password
	private static String createPassword(String password, String salt) {
		String encpassword = "";
		byte[] bytepassword = hashPassword(password.toCharArray(), salt.getBytes());
		encpassword = Base64.getEncoder().encodeToString(bytepassword);
		
		return encpassword;
	}
	
	// Hash password
	private static byte[] hashPassword(char[] password, byte[] salt) {
		PBEKeySpec keyspec = new PBEKeySpec(password, salt, ITERATIONS, KEYLENGTH);
		Arrays.fill(password, Character.MIN_VALUE);
		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			return skf.generateSecret(keyspec).getEncoded();
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new AssertionError("Error while hashing password: "+ e.getMessage(), e);
		} finally {
			keyspec.clearPassword();
		}
	}
	
	// Check password
	public final boolean verifyPassword(String username, String password) {
		boolean verification = false;
		String[] stored = sett.getAuthentification();
		String storeduser = stored[0];
		String storedpass = stored[1];
		String storedsalt = stored[2];
		
		if (storeduser.equals(username)) {
			String checksecpassword = createPassword(password, storedsalt);
			verification = checksecpassword.equalsIgnoreCase(storedpass);
		}
		
		return verification;
	}
}
