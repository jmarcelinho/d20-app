package com.example.d20.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tb_user")
public class User {
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "IdUser")
	private Integer Id;
	
	@Column(name = "fname")
	private String fname;
	
	@Column(name = "lname")
	private String lname;
	
	@Column(name = "cpf")
	private String cpf;
	
	@Column(name = "telephone")
	@Size(min=8, max=9)
	private String telephone;
	
	@Column(name = "email")
	private String email;
	
	public User() {}
	
	public User(String fname, String lname, String cpf, String telephone, String email) {
		this.fname = fname;
		this.lname = lname;
		this.cpf = cpf;
		this.telephone = telephone;
		this.email = email;
	}
	
	public User(String fname, String lname, String telephone, String email) {
		this.fname = fname;
		this.lname = lname;
		this.telephone = telephone;
		this.email = email;
	}
	
	public Integer getId() {
		return this.Id;
	}

	public void setId(Integer id) {
		this.Id = id;
	}

	public String getFname() {
		return this.fname;
	}

	public void setFname(String name) {
		this.fname = name;
	}
	
	public String getLname() {
		return this.lname;
	}

	public void setLname(String name) {
		this.lname = name;
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
		result = prime * result + ((fname == null) ? 0 : fname.hashCode());
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
		if (fname == null) {
			if (other.fname != null)
				return false;
		} else if (!fname.equals(other.fname))
			return false;
		if (lname == null) {
			if (other.lname != null)
				return false;
		} else if (!lname.equals(other.lname))
			return false;
		return true;
	}
}