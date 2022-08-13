package com.suppliers;

public class Suppliers {
	
	private int id;
	private String firstName;
	private String lastName;
	private String phone;
	private String location;
	
	public Suppliers(int id, String firstName, String lastName, String phone, String location) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.location = location;
	}

	public Suppliers(String firstName, String lastName, String phone, String location) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Suppliers [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone
				+ ", location=" + location + "]";
	}

	
}
