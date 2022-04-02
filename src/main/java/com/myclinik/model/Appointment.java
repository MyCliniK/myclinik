package com.myclinik.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Objects;

@Entity
@Table(name = "appointments")
public class Appointment {

	@GeneratedValue(strategy=GenerationType.AUTO, generator="id_seq")
	private @Id Long appointmentId;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime appointmentDate;
	private Boolean done;
	private Boolean paid;

	public Appointment() {}

	public Appointment(LocalDateTime appointmentDate, Boolean done, Boolean paid) {
		this.appointmentDate= appointmentDate;
		this.done = done;
		this.paid = paid;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Appointment appointment = (Appointment) o;
		return Objects.equals(appointmentId, appointment.appointmentId) &&
			Objects.equals(appointmentDate, appointment.appointmentDate) &&
			Objects.equals(done, appointment.done) &&
			Objects.equals(paid, appointment.paid);
	}

	@Override
	public int hashCode() {

		return Objects.hash(appointmentId, appointmentDate, done, paid);
	}

	public Long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
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


	@Override
	public String toString() {
		return "Appointment{" +
		"appointmentId=" + appointmentId +
		", appointmentDate='" + appointmentDate + '\'' +
		", done='" + done + '\'' +
		", paid='" + paid + '\'' +
		'}';
	}
}
