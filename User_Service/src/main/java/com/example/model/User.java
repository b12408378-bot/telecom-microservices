package com.example.model;



import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;

import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "users")
public class User {


	@Id
	@GeneratedValue
	private int user_id;

	private String username;

	private String password;   //to be removed

	private String email;

	private String phone_number;
	
	  @Enumerated(EnumType.STRING)
	    private PlanType planType;     

	

	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	
	public String getUsername() {
		return username;
	}

	
	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone_number() {
		return phone_number;
	}


	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	
	
	public PlanType getPlanType() {
        return planType;
    }

    public void setPlanType(PlanType planType) {
        this.planType = planType;
    }

    @Override
    public String toString() {
        return "User [user_id=" + user_id +
                ", username=" + username +
                ", password=" + password +
                ", email=" + email +
                ", phone_number=" + phone_number +
                ", planType=" + planType + "]";
    }

}