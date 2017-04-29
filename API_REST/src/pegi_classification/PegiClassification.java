package pegi_classification;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import generic.DataBaseEntity;
import generic.Functions;

@Table(name="pegi_classification")
@Entity
public class PegiClassification extends DataBaseEntity{
	@Id
	private int id;
	
	@Column
	private String name;
	
	@Column 
	private String img;

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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = Functions.stringToHtmlString(img);
	}
}
