package airline.security.model;

public class JWT {
	
	private Header header;
	private Payload payload;
	private Signature signature;
	
	public JWT() {
		
	}
	
	
	public JWT(Header header, Payload payload, Signature signature) {
		super();
		this.header = header;
		this.payload = payload;
		this.signature = signature;
	}


	public Header getHeader() {
		return header;
	}


	public void setHeader(Header header) {
		this.header = header;
	}


	public Payload getPayload() {
		return payload;
	}


	public void setPayload(Payload payload) {
		this.payload = payload;
	}


	public Signature getSignature() {
		return signature;
	}


	public void setSignature(Signature signature) {
		this.signature = signature;
	}


	@Override
	public String toString() {
		return "JWT [header=" + header + ", payload=" + payload + ", signature=" + signature + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
