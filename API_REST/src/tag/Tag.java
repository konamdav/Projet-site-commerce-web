package tag;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import generic.DataBaseEntity;
import generic.Functions;

@Entity
public class Tag extends DataBaseEntity{
	@Id
	private int id;
	@Column
	private String name;
	
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
		this.name = Functions.stringToHtmlString(name);
	}
	
	
}
