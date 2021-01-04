package jencrypt;

import java.security.SecureRandom;
import java.util.Random;

public class encrypt {
	private static final Random RANDOM = new SecureRandom();
	private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final int ITERATIONS = 10000;
	private static final int KEYLENGTH = 256;
	private static final int SALTLENGTH = 10;
	
	public void createUser(String username, String password) {
		
	}
}
