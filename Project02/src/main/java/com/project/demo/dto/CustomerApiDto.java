package com.project.demo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CustomerApiDto {

	// fields

	private int Id;

	@NotBlank
	@NotNull(message = "firstName cannot be null")
	private String firstName;

	private String lastName;

	@Email
	@NotBlank
	@NotNull(message = "email cannot be null")
	private String email;

	@NotBlank
	@NotNull(message = "city cannot be null")
	private String city;

	@NotNull(message = "pinCode cannot be null")
	@Min(6)
	private int pinCode;

	@NotBlank
	@NotNull(message = "role cannot be null")
	private String role;

	// constructors

	public CustomerApiDto() {
	};

	public CustomerApiDto(int id, String firstName, String lastName, String email, String city, int pinCode,
			String role) {
		super();
		Id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.city = city;
		this.pinCode = pinCode;
		this.role = role;
	}

	// getters & setters

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPinCode() {
		return pinCode;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "CustomerApiDto{" + "Id=" + Id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\''
				+ ", email='" + email + '\'' + ", city='" + city + '\'' + ", pinCode='" + pinCode + '\'' + ", role='"
				+ role + '\'' + '}';
	}
}
