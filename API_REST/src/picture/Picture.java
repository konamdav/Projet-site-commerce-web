package picture;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import generic.DataBaseEntity;

@Entity
public class Picture extends DataBaseEntity{

	@Id
	private int id;
	@Column
	private String  img;
	@Column
	private String  name;
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Picture() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}
	
	
	
	
}
