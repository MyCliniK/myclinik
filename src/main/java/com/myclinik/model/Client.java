package com.myclinik.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "clients")
public class Client {

	private @Id @GeneratedValue Long id;
	private String firstName;
	private String lastName;

	private Client() {}

	public Client(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Client client = (Client) o;
		return Objects.equals(id, client.id) &&
			Objects.equals(firstName, client.firstName) &&
			Objects.equals(lastName, client.lastName);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, firstName, lastName);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	@Override
	public String toString() {
		return "Client{" +
		"id=" + id +
		", firstName='" + firstName + '\'' +
		", lastName='" + lastName + '\'' +
		'}';
	}
}
