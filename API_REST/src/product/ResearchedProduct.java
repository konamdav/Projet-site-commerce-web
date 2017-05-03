package product;

import java.util.List;


public class ResearchedProduct {

	public String getVideogame() {
		return videogame;
	}

	public void setVideogame(String videogame) {
		this.videogame = videogame;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getConsole() {
		return console;
	}

	public void setConsole(String console) {
		this.console = console;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public List<String> getPegis() {
		return pegis;
	}

	public void setPegis(List<String> pegis) {
		this.pegis = pegis;
	}

	String videogame;
	
	String publisher;
	
	String console;
	
	List<String> genres;
	
	List<String> tags;
	
	List<String> pegis;
}
