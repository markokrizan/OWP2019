package airline.security;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import airline.model.User;
import airline.model.User.Role;
import airline.security.model.Header;
import airline.security.model.JWT;
import airline.security.model.Payload;
import airline.security.model.Signature;
import airline.service.UserService;

public class AuthUtil {
	
	private static final String serverSecret = "ASDF46AS5D4F65A4SDF654AS65DF4";
	
	
	public static String generateToken(User user) {
		System.out.println(user);
		
		Header header = new Header();
		Payload payload = new Payload(user.getId(), user.getUserName(), user.getFirstName(), user.getLastName(), user.getRole());
		Signature signature = new Signature(header, payload, serverSecret);
		JWT jwt = new JWT(header, payload, signature);
		
		String signedSignature = sign(jwt);
		
		return createJWTString(header, payload, signedSignature);
		
		
	
	}
	
	public static boolean verifyToken(String string) {
		if(string == null) {
			return false;
		}
		String[] parts = string.split("\\.");
		
		if(parts.length != 3) {
			return false;
		}
		
		
		String headerJSON = base64Decode(parts[0]);
		String payloadJSON = base64Decode(parts[1]);
		String sendersSignatureJSON = base64Decode(parts[2]);
		
		if(headerJSON == null || payloadJSON == null || sendersSignatureJSON == null) {
			return false;
		}
		
		
		String sendersSignature = sendersSignatureJSON.substring(1, sendersSignatureJSON.length()-1); //"sdfsdfsdfsdfsdfsdfsdf" - trim double quotes from JSON
		
		
		ObjectMapper mapper = new ObjectMapper();
		Header header = null;
		Payload payload = null;

		try {
			header = mapper.readValue(headerJSON, Header.class);
			payload = mapper.readValue(payloadJSON, Payload.class);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//construct signature from header and payload:
		Signature newSignature = new Signature(header, payload, serverSecret);
		JWT newJWT = new JWT(header, payload, newSignature);
		String serversSignature = sign(newJWT);
		
//		System.out.println(headerJSON);
//		System.out.println(payloadJSON);
//		System.out.println(sendersSignatureJSON);
//		System.out.println(sendersSignature);
//		System.out.println(newSignature);
//		System.out.println(newJWT);
//
//		
//		System.out.println("Final comparison:");
//		System.out.println(serversSignature);
//		System.out.println(sendersSignature);
		
		
		if(serversSignature.equals(sendersSignature)) {
			return true;
		} else {
			return false;
		}
			
	}
	
	public static User getUserFromToken(String token) {
		ObjectMapper mapper = new ObjectMapper();
		String payloadJSON = base64Decode(token.split("\\.")[1]);
		Payload payload = null;
		try {
			payload = mapper.readValue(payloadJSON, Payload.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Integer userId = payload.getUserId();
		return UserService.getOne(userId);
		
		
		
	}
	
	public enum AuthStatus{
		NOT_VALID,
		UNAUTHENTICATED,
		AUTHORIZED,
		UNAUTHORIZED
	}
	
	
	//authentication controller turned into a method:
	public static AuthStatus authorizeToken(String token, Role role) {
		if(token == null || token.equals("")) {
			//token not present:
			return AuthStatus.UNAUTHENTICATED;
		}else {
			if(!AuthUtil.verifyToken(token)) {
				//present but not valid:
				return AuthStatus.NOT_VALID;
			}else {
				//present and valid:
				if(getRoleFromToken(token) == role) {
					return AuthStatus.AUTHORIZED;
				}else {
					return AuthStatus.UNAUTHENTICATED;
				}
				
			}
		}
	}
	
	public static Role getRoleFromToken(String token) {
		return getUserFromToken(token).getRole();
	}
	
	
	
	public static String createJWTString(Header header, Payload payload, String signedSignature) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonHeader = null;
		String jsonPayload = null;
		String jsonSignedSignature = null;
		
		try {
			jsonHeader = mapper.writeValueAsString(header);
			jsonPayload = mapper.writeValueAsString(payload);
			jsonSignedSignature = mapper.writeValueAsString(signedSignature);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return base64Encode(jsonHeader) + "." + base64Encode(jsonPayload) + "." + base64Encode(jsonSignedSignature);
	}
	
	
	public static String sign(JWT jwt) {
		return hash(jwt.toString(), jwt.getHeader().getAlgoritm());
	}
	
	
	public static User checkCredentials(String username, String password) {
		User user = AuthDAO.getOne(username);
		if(user != null) {
			if(user.getPassword().equals(hash(password, "MD5"))) {
				return user;
			}else {
				return null;
			}
		}else {
			return null;
		}	
	}
	
	
	
	public static String hash(String input, String alg) { 
        try { 
  
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance(alg); 
  
            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }  
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    } 
	
	
	public static String base64Encode(Object object) {
		byte[] encodedBytes = Base64.getEncoder().encode(object.toString().getBytes());
		return new String(encodedBytes, StandardCharsets.UTF_8); 
	}
	
	public static String base64Decode(String string) {
		try {
			byte[] decodedBytes = Base64.getDecoder().decode(string.getBytes());
			return new String(decodedBytes, StandardCharsets.UTF_8);
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
		
//		byte[] decodedBytes = Base64.getDecoder().decode(string.getBytes());
//		return new String(decodedBytes, StandardCharsets.UTF_8);
	}
	
	

}
