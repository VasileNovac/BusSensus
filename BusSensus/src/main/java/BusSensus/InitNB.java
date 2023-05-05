package BusSensus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class InitNB {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long Id ;
	private String numberBus ;
	
	public InitNB() { }
	
	public InitNB(String numberBus) {
		super();
		this.numberBus = numberBus;
	}

	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}

	public String getNumberBus() {
		return numberBus;
	}
	public void setNumberBus(String numberBus) {
		this.numberBus = numberBus;
	}

	@Override
	public String toString() {
		return "InitNB [Id=" + Id + ", numberBus=" + numberBus + "]";
	}

}
