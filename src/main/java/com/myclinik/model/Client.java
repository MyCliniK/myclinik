package com.myclinik.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "clients")
public class Client {

	@GeneratedValue(strategy = GenerationType.AUTO, generator = "clients_id_seq")
	private @Id Long id;
	private String firstName;
	private String lastName;
	private String dni;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthdate;
	private String sex;
	private String phone;
	private String email;
	private Boolean promos;
	private String medicalObservations;
	private String observations;

	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Appointment> appointments;

	public Client() {
	}

	public Client(String firstName, String lastName, String dni, Date birthdate, String sex, String phone, String email,
			Boolean promos, String medicalObservations, String observations, List<Appointment> appointments) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dni = dni;
		this.birthdate = birthdate;
		this.sex = sex;
		this.phone = phone;
		this.email = email;
		this.promos = promos;
		this.medicalObservations = medicalObservations;
		this.observations = observations;
		this.appointments = appointments;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Client client = (Client) o;
		return Objects.equals(id, client.id) &&
				Objects.equals(firstName, client.firstName) &&
				Objects.equals(lastName, client.lastName) &&
				Objects.equals(dni, client.dni) &&
				Objects.equals(birthdate, client.birthdate) &&
				Objects.equals(sex, client.sex) &&
				Objects.equals(email, client.phone) &&
				Objects.equals(phone, client.email) &&
				Objects.equals(promos, client.promos) &&
				Objects.equals(medicalObservations, client.medicalObservations) &&
				Objects.equals(observations, client.observations) &&
				Objects.equals(appointments, client.appointments);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, firstName, lastName, email, phone, medicalObservations, observations, appointments);
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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getPromos() {
		return promos;
	}

	public void setPromos(Boolean promos) {
		this.promos = promos;
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

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	@Override
	public String toString() {
		return "Client{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", dni='" + dni + '\'' +
				", birthdate=" + birthdate +
				", sex=" + sex +
				", phone='" + phone + '\'' +
				", email='" + email + '\'' +
				", sex='" + sex + '\'' +
				", medicalObservations='" + medicalObservations + '\'' +
				", observations='" + observations + '\'' +
				'}';
	}
}
