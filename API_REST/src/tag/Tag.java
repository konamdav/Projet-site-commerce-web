package tag;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import generic.DataBaseEntity;

@Entity
public class Tag extends DataBaseEntity{
	@Id
	private int id;
	@Column
	private String name;
}
