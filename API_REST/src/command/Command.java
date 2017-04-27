package command;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import generic.DataBaseEntity;
import product.Product;

@Entity
public class Command extends DataBaseEntity{
	@Id
	private int id;

	@Column
	private String date_command;

	@Column
	private int id_user;

	@Column
	private String status;

	public void setLinecommands(Set<LineCommand> linecommands) {
		this.linecommands = linecommands;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id_command")
	private Set<LineCommand> linecommands = new LinkedHashSet<LineCommand>();


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

	@JsonIgnore
	public int getId_user() {
		return id_user;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTotal()
	{
		double d = 0;
		if(this.linecommands != null){
			for(LineCommand lc : this.linecommands)
			{
				d+= lc.getQuantity()*lc.getPrice();
			}
		}
		return d;
	}

	public LineCommand addLineCommand(Product product, int quantity)
	{
		LineCommand line = new LineCommand();
		line.setProduct(product);
		line.setPrice(product.getPrice());
		line.setQuantity(quantity);
		line.setId_command(this.id);
		this.linecommands.add(line);
		
		return line;
	}
	
	public LineCommand removeLineCommand(LineCommand linecommand)
	{
		LineCommand tmp = null;
		for(LineCommand lc : this.linecommands)
		{
			if(lc.getProduct().getId() == linecommand.getProduct().getId())
			{
				tmp = lc;
			}
		}
		
		this.linecommands.remove(tmp);
		return tmp;
	}
}
