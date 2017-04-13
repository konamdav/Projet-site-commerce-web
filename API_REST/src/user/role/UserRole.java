package user.role;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import generic.DataBaseEntity;

import javax.persistence.*;


@Entity
public class UserRole extends DataBaseEntity implements Serializable{
	@Id
	private int id;
	
	@Column
	private int id_user;
	
	@Column
	private String role;


	public UserRole() {
		super();
	}

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}





}
