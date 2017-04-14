package user;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import command.Command;
import generic.DataBaseEntity;
import user.role.UserRole;

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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id_user")
	private Set<UserRole> userRoles;
	
	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "id_user")
	private Set<Command> commands;
	
	public Set<Command> getCommands() {
		return commands;
	}
*/
	public User() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRole(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
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
		this.surname = surname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	
}
