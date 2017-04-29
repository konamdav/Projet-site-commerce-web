package videogame;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import console.Console;
import generic.Functions;
import genre.Genre;
import pegi_classification.PegiClassification;
import publisher.Publisher;
import tag.Tag;

@Entity
public class VideoGame {

	@Id
	private int id;

	@OneToOne
	@JoinColumn(name="id_publisher",referencedColumnName="id")
	private Publisher publisher;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "videogame_genre", joinColumns = {
			@JoinColumn(name = "id_videogame") }
			, inverseJoinColumns = { @JoinColumn(name = "id_genre")})
	private List<Genre> genres;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "tag_videogame", joinColumns = {
			@JoinColumn(name = "id_videogame") }
			, inverseJoinColumns = { @JoinColumn(name = "id_tag")})
	private List<Tag> tags;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "videogame_pegi_classification", joinColumns = {
			@JoinColumn(name = "id_videogame") }
			, inverseJoinColumns = { @JoinColumn(name = "id_pegi_classification")})
	private List<PegiClassification> classifications;
	
	@Column
	private String name;

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public List<Tag> getTags() {
		return tags;
	}


	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}


	public Publisher getPublisher() {
		return publisher;
	}


	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public List<Genre> getGenres() {
		return genres;
	}


	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = Functions.stringToHtmlString(name);
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


	public List<PegiClassification> getClassifications() {
		return classifications;
	}


	public void setClassifications(List<PegiClassification> classifications) {
		this.classifications = classifications;
	}

}
