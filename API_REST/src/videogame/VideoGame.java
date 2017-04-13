package videogame;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import console.Console;
import genre.Genre;
import publisher.Publisher;

@Entity
public class VideoGame {

	@Id
	private int id;

	@OneToOne
	@JoinColumn(name="id_publisher",referencedColumnName="id")
	private Publisher publisher;

	@OneToOne
	@JoinColumn(name="id_genre",referencedColumnName="id")
	private Genre genre;
	@Column
	private String name;

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Publisher getPublisher() {
		return publisher;
	}


	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}


	

	public Genre getGenre() {
		return genre;
	}


	public void setGenre(Genre genre) {
		this.genre = genre;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@JsonIgnore
	public String getProperties() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "";
		}

	}

}
