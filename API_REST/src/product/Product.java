package product;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import console.Console;
import generic.DataBaseEntity;
import generic.Functions;
import picture.Picture;
import review.Review;
import videogame.VideoGame;

@Entity
public class Product extends DataBaseEntity{
	@Id
	private int id;
	
	@Column 
	private double price;


	@OneToOne
	@JoinColumn(name="id_videogame",referencedColumnName="id")
	private VideoGame videogame;
	

	@OneToOne
	@JoinColumn(name="id_console",referencedColumnName="id")
	private Console console;
	
	@Column
	private String date_release;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id_product")
	private List<Picture> pictures = new ArrayList<Picture>();
	
	@OneToMany(mappedBy = "id_product")
	private List<Review> reviews  = new ArrayList<Review>();
	
	public List<Review> getReviews() {
		return reviews;
	}


	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}


	public Product() {
		super();
	
	}
	

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public VideoGame getVideogame() {
		return videogame;
	}

	public void setVideogame(VideoGame videogame) {
		this.videogame = videogame;
	}

	public Console getConsole() {
		return console;
	}

	public void setConsole(Console console) {
		this.console = console;
	}

	public String getDate_release() {
		return date_release;
	}

	public void setDate_release(String date_release) {
		this.date_release = Functions.stringToHtmlString(date_release);
	}
	
	
	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}
	
	
}
