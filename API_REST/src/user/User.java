package user;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import command.Command;
import generic.DataBaseEntity;
import generic.Functions;
import review.Review;

import javax.persistence.*;

@Entity
@Table(name="User")
public class User extends DataBaseEntity implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column
	private String username;
	@Column 
	private String surname;
	@Column
	private String firstname;
	@Column
	private String mail;
	@Column
	private String password;
	@Column
	private String role;

	@OneToMany(mappedBy = "id_user")
	private List<Review> reviews;
	
	@OneToMany(mappedBy = "id_user", cascade = CascadeType.ALL)
	private List<Command> commands;
	
	@JsonIgnore
	public List<Command> getCommands() {
		return commands;
	}

	public void setCommands(List<Command> commands) {
		this.commands = commands;
	}

	@JsonIgnore
	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public User() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = Functions.stringToHtmlString(username);
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = Functions.stringToHtmlString(role);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = Functions.stringToHtmlString(surname);
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = Functions.stringToHtmlString(firstname);
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = Functions.stringToHtmlString(mail);
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = Functions.stringToHtmlString(password);
	}	
}
