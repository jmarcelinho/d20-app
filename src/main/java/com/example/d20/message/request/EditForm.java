package com.example.d20.message.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EditForm {
	
	@NotBlank
    @Size(min = 4, max = 40)
    private String password;
	
	@NotBlank
	private String item;
	
	public String getPassword() {
		return this.password;
	}
	
	public String getItem() {
		return this.item;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setItem(String item) {
		this.item = item;
	}
}
