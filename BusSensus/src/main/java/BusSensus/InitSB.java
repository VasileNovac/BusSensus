package BusSensus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class InitSB {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long Id ;
	private String routeBus ;
	private String stationBus ;
	
	public InitSB() { }

	public InitSB(String routeBus, String stationBus) {
		super();
		this.routeBus = routeBus;
		this.stationBus = stationBus;
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

	public String getStationBus() {
		return stationBus;
	}
	public void setStationBus(String stationBus) {
		this.stationBus = stationBus;
	}

	@Override
	public String toString() {
		return "InitSB [Id=" + Id + ", routeBus=" + routeBus + ", stationBus=" + stationBus + "]";
	}

}
