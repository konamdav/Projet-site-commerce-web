package picture;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import generic.DataBaseEntity;
import generic.Functions;

@Entity
public class Picture extends DataBaseEntity{
	
	@Id
	private int id;
	@Column
	private String  img;
	@Column
	private String  name;
	@Column
	private int  id_product;
	
	public int getId_product() {
		return id_product;
	}


	public void setId_product(int id_product) {
		this.id_product = id_product;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = Functions.stringToHtmlString(name);;
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
		this.img = Functions.stringToHtmlString(img);
	}
	
	
	
	
}
