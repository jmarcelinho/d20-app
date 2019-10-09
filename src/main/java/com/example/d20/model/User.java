package com.example.d20.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tb_user")
public class User {
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "IdUser")
	private Integer Id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "bank_info")
	private String bankInfo;
	
	@Column(name = "credCard")
	private String credCard;
	
	@Column(name = "cpf")
	private String cpf;
	
	@Column(name = "telephone")
	private String telephone;
	
	@Column(name = "email")
	private String email;
	
	public User() {}
	
	public User(String name, String bankInfo, String credCard, String cpf, String telephone, String email) {
		this.name = name;
		this.bankInfo = bankInfo;
		this.credCard = credCard;
		this.cpf = cpf;
		this.telephone = telephone;
		this.email = email;
	}
	
	
	public Integer getId() {
		return this.Id;
	}

	public void setId(Integer id) {
		this.Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBankInfo() {
		return bankInfo;
	}

	public void setBankInfo(String bank_info) {
		this.bankInfo = bank_info;
	}

	public String getCredCard() {
		return credCard;
	}

	public void setCredCard(String cred_card) {
		this.credCard = cred_card;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		User other = (User) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}