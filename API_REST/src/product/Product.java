package product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import console.Console;
import generic.DataBaseEntity;
import videogame.VideoGame;

@Entity
public class Product extends DataBaseEntity{

	@Id
	private int id;
	
	@Column 
	private int price;
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@OneToOne
	@JoinColumn(name="id_videogame",referencedColumnName="id")
	private VideoGame videogame;
	
	@OneToOne
	@JoinColumn(name="id_console",referencedColumnName="id")
	private Console console;
	
	@Column
	private String date_release;
	
	public Product() {
		super();
	
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
		this.date_release = date_release;
	}
	
	

	
	
}
