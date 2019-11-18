package com.example.d20.model;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
 
@Entity
@Table(name = "tb_loan")
public class Loan {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	
	@ManyToOne
	private Ownership item;
	
	@ManyToOne
	private User loanee;
	
	@Column(name = "price")
	private double price;
	
	// we'll need the date to know for how long the
	// game was kept by the user
	@Column(name = "startDate")
	private Date startDate;
	
	@Column(name = "finishDate")
	private Date finishDate;
	
	@Column(name = "finished")
	private boolean finished;
	
	public Loan() {}
	public Loan(Ownership item, User loanee, double price) {	
		// TODO: if loan.item.availability is false, throw an error
		this.item = item;
		
		this.price = price;
		this.loanee = loanee;
		this.finished = false;
		this.startDate = new Date();
	}
	
	public Loan(Ownership item, User loanee) {
		// TODO: if loan.item.availability is false, throw an error		
		this.item = item;
		
		this.loanee = loanee;
		this.finished = false;
		this.startDate = new Date();
		this.price = item.getPrice();
	}
	
	public Integer getId() {
		return this.Id;
	}
	public Ownership getItem() {
		return this.item;
	}
	
	public Date getStartDate() {
		return this.startDate;
	}
	
	public Date getFinishDate() {
		if(this.finished) return this.finishDate;
		else {
			// TODO: handle this error in a better way
			return this.startDate;
		}
	}
	
	public User getLoanee() {
		return this.loanee;
	}

	public void setItem(Ownership item) {
		this.item = item;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public boolean getFinished() {
		return this.finished;
	}
	
	public void finishLoan() {
		this.finished = true;
		this.finishDate = new Date();
	}
	
	// returns how much needs to be paid for the game
	// by the end of the loan.
	public double getTotal() {
		if(this.finished) {
			long diffInMs = Math.abs(finishDate.getTime() - startDate.getTime());
			
			long diffInDays = TimeUnit.DAYS.convert(diffInMs, TimeUnit.MILLISECONDS);
			diffInDays += 1;
			
			return ((double) diffInDays) * this.price;
		}
		
		// loan is yet to be finished. 
		// TODO: handle this error in a better way
		return -1.0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
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
		Loan other = (Loan) obj;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (price != other.price)
			return false;
		return true;
	}
}
