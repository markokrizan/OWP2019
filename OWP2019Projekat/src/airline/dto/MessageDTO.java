package airline.dto;

import java.io.Serializable;

public class MessageDTO implements Serializable {
	
	private String status;
	private String trace;
	
	

	public MessageDTO(String status, String trace) {
		super();
		this.status = status;
		this.trace = trace;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTrace() {
		return trace;
	}
	public void setTrace(String trace) {
		this.trace = trace;
	}
	
	
	

}
