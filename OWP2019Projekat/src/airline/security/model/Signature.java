package airline.security.model;

import airline.security.AuthUtil;

public class Signature {
	
	
	private String encodedHeader;
	private String encodedPayload;
	private String encodedSecret;
	
	
	public Signature(Header header, Payload payload, String secret) {
		super();
		this.encodedHeader = AuthUtil.base64Encode(header);
		this.encodedPayload = AuthUtil.base64Encode(payload);
		this.encodedSecret = AuthUtil.base64Encode(secret);
	}
	
	public Signature() {
		
	}
	


	public String getEncodedHeader() {
		return encodedHeader;
	}


	public String getEncodedPayload() {
		return encodedPayload;
	}

	public String getEncodedSecret() {
		return encodedSecret;
	}
	
	



	
	
	
	
	
	
	
	
	

		
	
	

}
