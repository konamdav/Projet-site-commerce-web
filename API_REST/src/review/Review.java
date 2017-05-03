package review;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import generic.DataBaseEntity;
import product.Product;
import user.User;

@Entity
public class Review extends DataBaseEntity{
	@Id
	private int id;	
	@Column 
	private int note;

	@ManyToOne(optional = false)
    @JoinColumn(name = "id_user")
	private User user;
	
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "id_product")
	private Product product;

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


	

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
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
