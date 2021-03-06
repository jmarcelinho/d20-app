package com.example.d20.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
 
@Entity
@Table(name = "tb_ownership")
public class Ownership {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	private User owner;
	
	@ManyToOne
	private Game game;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "info")
	private String info;
	
	@Column(name = "availability")
	private boolean availability;
	
	public Ownership() {}
	public Ownership(User owner, Game game, double price, String info, boolean availability) {
		this.owner = owner;
		this.game = game;
		this.price = price;
		this.info = info;
		this.availability = availability;
	}
	
	public Integer getId() {
		return this.id;
	}
	public User getOwner() {
		return this.owner;
	}
	
	public Game getGame() {
		return this.game;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public boolean getAvailability() {
		return this.availability;
	}
	
	public void setAvailability(boolean newAvailability) {
		this.availability = newAvailability;
	}
	
	public void setPrice(double newPrice) {
		this.price = newPrice;
	}
	
	public String getInfo() {
		return this.info;
	}
	
	public void setInfo(String newInfo) {
		this.info = newInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((game == null) ? 0 : game.hashCode());
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ownership other = (Ownership) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (game == null) {
			if (other.game != null)
				return false;
		} else if (!game.equals(other.game))
			return false;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		return true;
	}
}
