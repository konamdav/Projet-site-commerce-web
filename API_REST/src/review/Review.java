package review;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.Session;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnore;

import generic.DataBaseEntity;
import generic.Database;
import product.Product;
import product.ProductDatabase;
import user.User;
import user.UserDatabase;

@Entity
public class Review extends DataBaseEntity{
	@Id
	private int id;	
	@Column 
	private int note;

	@Column
	private int id_user;
	
	@Column
	private int  id_product;

	@Column
	private String title;
	

	@Column
	private String content;

	public Review() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public String getUser()
	{
		Session s = Database.init();
		User userTmp = UserDatabase.findUserByID(this.id_user,s );
		Database.close(s);
		JSONObject object = new JSONObject();
		try {
			object.put("id", userTmp.getId());
			object.put("username", userTmp.getUsername());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return object.toString();
		
	}
	
	public int getId_product() {
		return id_product;
	}

	public void setId_product(int id_product) {
		this.id_product = id_product;
	}

	public String getProductName()
	{
		Session s = Database.init();
		Product p = ProductDatabase.findProductByID(id_product, s);
		Database.close(s);
		return p.getVideogame().getName()+"/"+p.getConsole().getName();
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
