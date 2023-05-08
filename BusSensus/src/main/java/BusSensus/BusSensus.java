package BusSensus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BusSensus {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long Id ;
	private String numberBus, routeBus, stationBus, userId ;
	private int traveler, travelerUp, travelerDown ;
	private String data, ora ;

	public BusSensus() { }

	public BusSensus(String numberBus, String routeBus, String stationBus, String userId, int traveler,
			int travelerUp, int travelerDown, String data, String ora) {
		super();
		this.numberBus = numberBus;
		this.routeBus = routeBus;
		this.stationBus = stationBus;
		this.userId = userId;
		this.traveler = traveler;
		this.travelerUp = travelerUp;
		this.travelerDown = travelerDown;
		this.data = data;
		this.ora = ora;
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

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getTraveler() {
		return traveler;
	}
	public void setTraveler(int traveler) {
		this.traveler = traveler;
	}

	public int getTravelerUp() {
		return travelerUp;
	}
	public void setTravelerUp(int travelerUp) {
		this.travelerUp = travelerUp;
	}

	public int getTravelerDown() {
		return travelerDown;
	}
	public void setTravelerDown(int travelerDown) {
		this.travelerDown = travelerDown;
	}

	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

	public String getOra() {
		return ora;
	}
	public void setOra(String ora) {
		this.ora = ora;
	}

	@Override
	public String toString() {
		return "BusSensus [Id=" + Id + ", numberBus=" + numberBus + ", routeBus=" + routeBus + ", stationBus="
				+ stationBus + ", userId=" + userId + ", traveler=" + traveler + ", travelerUp=" + travelerUp
				+ ", travelerDown=" + travelerDown + ", data=" + data + ", ora=" + ora + "]";
	}
	
}
