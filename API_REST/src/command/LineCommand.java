package command;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import generic.DataBaseEntity;
import product.Product;

@Entity
public class LineCommand extends DataBaseEntity{
	
	@Id
	private int id_command;
	
	@Id
	@OneToOne
	@JoinColumn(name="id_product",referencedColumnName="id")
	private Product product;
	
	@Column
	private int quantity = 0;
	
	@Column
	private double price;

	public LineCommand() {
		super();
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getId_command() {
		return id_command;
	}

	public void setId_command(int id_command) {
		this.id_command = id_command;
	}

	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
