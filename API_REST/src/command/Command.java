package command;

import java.util.Set;

import javax.persistence.*;

import generic.DataBaseEntity;

@Entity
public class Command extends DataBaseEntity{
	@Id
	private int id;
	
	@Column
	private String date_command;
	
	@Column
	private int id_user;

	public void setLinecommands(Set<LineCommand> linecommands) {
		this.linecommands = linecommands;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id_command")
	private Set<LineCommand> linecommands;
	
	
	public Command() {
		super();
	}

	public Set<LineCommand> getLinecommands() {
		return linecommands;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDate_command(String date_command) {
		this.date_command = date_command;
	}

	/*public void setLinecommands(Set<LineCommand> linecommands) {
		this.linecommands = linecommands;
	}*/

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	
	public int getId() {
		return id;
	}

	public String getDate_command() {
		return date_command;
	}

	public int getId_user() {
		return id_user;
	}

	public double getTotal()
	{
		double d = 0;
		for(LineCommand lc : this.linecommands)
		{
			d+= lc.getQuantity()*lc.getProduct().getPrice();
		}
		return d;
	}
	
}
