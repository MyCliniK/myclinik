package com.myclinik.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.time.LocalDateTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Objects;

@Entity
@Table(name = "appointments")
public class Appointment {

	@GeneratedValue(strategy=GenerationType.AUTO, generator="appointments_id_seq")
	private @Id Long id;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime appointmentDate;
	private Boolean done;
	private Boolean paid;
	
    @ManyToOne
    @JoinColumn(name = "client")
	@OnDelete(action = OnDeleteAction.CASCADE)
    private Client client;

	public Appointment() {}

	public Appointment(LocalDateTime appointmentDate, Boolean done, Boolean paid, Client client) {
		this.appointmentDate= appointmentDate;
		this.done = done;
		this.paid = paid;
		this.client = client;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Appointment appointment = (Appointment) o;
		return Objects.equals(id, appointment.id) &&
			Objects.equals(appointmentDate, appointment.appointmentDate) &&
			Objects.equals(done, appointment.done) &&
			Objects.equals(paid, appointment.paid) &&
			Objects.equals(client, appointment.client);
			
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, appointmentDate, done, paid, client);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDateTime appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

	public Boolean getPaid() {
		return paid;
	}

	public void setPaid(Boolean paid) {
		this.paid = paid;
	}

	public Client getClient(){
		return client;
	}

	public void setClient(Client client){
		this.client = client;
	}

	@Override
	public String toString() {
		return "Appointment{" +
		"appointmentId=" + id +
		", appointmentDate='" + appointmentDate + '\'' +
		", done='" + done + '\'' +
		", paid='" + paid + '\'' +
		", client='" + client + '\'' +
		'}';
	}
}
