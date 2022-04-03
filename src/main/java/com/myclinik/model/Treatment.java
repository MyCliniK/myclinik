package com.myclinik.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.Table;

import java.util.Objects;
import java.util.List;

@Entity
@Table(name = "treatments")
public class Treatment {

	@GeneratedValue(strategy=GenerationType.AUTO, generator="treatments_id_seq")
	private @Id  Long id;
	private String name;
	private String subservice;
	private Float price;
	private Integer duration;
	private String consents;

	@OneToMany(mappedBy = "treatment", cascade=CascadeType.ALL)
	private List<Appointment> appointments;

	public Treatment() {}

	public Treatment(String name, String subservice, Float price, Integer duration, String consents, List<Appointment> appointments) {
		this.name =name;
		this.subservice =subservice;
		this.price = price;
		this.duration = duration;
		this.consents = consents;
		this.appointments = appointments;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Treatment treatment = (Treatment) o;
		return Objects.equals(id,treatment.id) &&
		Objects.equals(name, treatment.name) &&
		Objects.equals(subservice, treatment.subservice) &&
		Objects.equals(price, treatment.price) &&
		Objects.equals(duration, treatment.duration) &&
		Objects.equals(consents, treatment.consents) &&
		Objects.equals(appointments, treatment.appointments);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, name, subservice, price, duration, consents, appointments);
	}

	public Long getId() {
		return id;

	}

	public void setId(Long id) {
		this.id = id;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubservice() {
		return subservice;
	}

	public void setSubservice(String subservice) {
		this.subservice = subservice;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getConsents() {
		return consents;
	}

	public void setConsents(String consents) {
		this.consents = consents;
	}

	public List<Appointment> getAppointments (List<Appointment> appointments){
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments){
		this.appointments = appointments;
	}

	@Override
	public String toString() {
		return "Treatment{" +"id=" + id +
		", name='" +name + '\'' +
		", subservice='" +subservice + '\'' +
		", price='" + price + '\'' +
		", duration='" + duration + '\'' +
		", consents='" + consents + '\'' +
		'}';
	}
}
