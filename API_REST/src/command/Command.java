package command;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import generic.DataBaseEntity;
import generic.Functions;
import product.Product;

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
		LineCommand line = this.findLineCommand(product);
		if(line == null)
		{
			line = new LineCommand();
			this.linecommands.add(line);	
		}
		
		line.setProduct(product);
		line.setPrice(product.getPrice());
		line.setQuantity(line.getQuantity() + quantity);
		line.setId_command(this.id);
		
		return line;
	}
	
	
	public LineCommand findLineCommand(Product product)
	{
		LineCommand lineCommand = null;
		
		for(LineCommand lc : this.linecommands)
		{
			if(lc.getProduct().getId()== product.getId())
			{
				lineCommand = lc;
			}
		}
		
		return lineCommand;
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
