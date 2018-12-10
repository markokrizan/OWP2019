package airline.model;

public class Airport {
	
	private int id;
	private String name;
	private Boolean deleted;
	
	
	public Airport() {
		
	}


	public Airport(int id, String name, Boolean deleted) {
		super();
		this.id = id;
		this.name = name;
		this.deleted = deleted;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	public Boolean getDeleted() {
		return deleted;
	}


	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	
	
	
	
}
