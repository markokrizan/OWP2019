package airline.dto;

public class RoleDTO {
	
	private Integer id;
	private String name;
	
	public RoleDTO() {
		
	}

	public RoleDTO(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "RoleDTO [id=" + id + ", name=" + name + "]";
	}
	
	

}