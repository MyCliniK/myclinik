package com.myclinik.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "clients")
public class Client {

	private @Id @GeneratedValue Long clientId;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String medicalObservations;
	private String observations;

	private Client() {}

	public Client(String firstName, String lastName, String email, String phone, String medicalObservations, String observations) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.medicalObservations = medicalObservations;
		this.observations = observations;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Client client = (Client) o;
		return Objects.equals(clientId, client.clientId) &&
			Objects.equals(firstName, client.firstName) &&
			Objects.equals(lastName, client.lastName) &&
			Objects.equals(email, client.email) &&
			Objects.equals(phone, client.phone) &&
			Objects.equals(medicalObservations, client.medicalObservations) &&
			Objects.equals(observations, client.observations);
	}

	@Override
	public int hashCode() {

		return Objects.hash(clientId, firstName, lastName, email, phone, medicalObservations, observations);
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMedicalObservations() {
		return medicalObservations;
	}

	public void setMedicalObservations(String medicalObservations) {
		this.medicalObservations = medicalObservations;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	@Override
	public String toString() {
		return "Client{" +
		"clientId=" + clientId +
		", firstName='" + firstName + '\'' +
		", lastName='" + lastName + '\'' +
		", email='" + email + '\'' +
		", phone='" + phone + '\'' +
		", medicalObservations='" + medicalObservations + '\'' +
		", observations='" + observations + '\'' +
		'}';
	}
}
