package airline.security;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

import airline.model.User;

public class AuthenticationUtil {
	
	private static final String salt = "ASDFJKDSFJ28394820989082093840HKSJDHF";
	
	
	
	public static String generateUserSecret() {
		String timestamp = new Timestamp(System.currentTimeMillis()).toString();
		return hash(timestamp + salt);
		
	}
	
	
	public static boolean checkCredentials(String username, String password) {
		User user = AuthDAO.getOne(username);
		if(user != null) {
			if(user.getPassword().equals(hash(password))) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}	
	}
	
	public static String hash(String plain) {
		
		byte[] bytesOfMessage = null;
		try {
			bytesOfMessage = plain.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] thedigest = md.digest(bytesOfMessage);
		String digestString = new String(thedigest, StandardCharsets.UTF_8);
		return digestString;
		
	}
	
	

}
