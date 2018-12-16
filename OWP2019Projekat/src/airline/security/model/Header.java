package airline.security.model;

public class Header {
	
	private String algoritm = "SHA-256";
	private String type = "JWT";
	
	public Header() {
	}
	
	public String getAlgoritm() {
		return algoritm;
	}
	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return "Header [algoritm=" + algoritm + ", type=" + type + "]";
	}
	
	
	
	

}
