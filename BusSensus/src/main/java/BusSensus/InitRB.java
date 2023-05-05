package BusSensus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class InitRB {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long Id ;
	private String routeBus ;

	public InitRB() { }
	
	public InitRB(String routeBus) {
		super();
		this.routeBus = routeBus;
	}

	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}

	public String getRouteBus() {
		return routeBus;
	}
	public void setRouteBus(String routeBus) {
		this.routeBus = routeBus;
	}

	@Override
	public String toString() {
		return "InitRB [Id=" + Id + ", routeBus=" + routeBus + "]";
	}

}
