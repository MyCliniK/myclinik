package com.myclinik.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "appointments")
public class Appointment {

	private @Id @GeneratedValue Long id;
	private Date date;
	private Date hour;
    // comprobar Time
	private Boolean done;
	private Boolean paid;

	private Appointment() {}

	public Appointment(Date date, Date hour, Boolean done, Boolean paid) {
		this.date= date;
		this.hour = hour;
		this.done = false;
		this.paid = false;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Appointment appointment = (Appointment) o;
		return Objects.equals(id, appointment.id) &&
			Objects.equals(date, appointment.date) &&
			Objects.equals(hour, appointment.hour) &&
			Objects.equals(done, appointment.done) &&
			Objects.equals(paid, appointment.paid);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, date, hour, done, paid);
	}

	public Long getAppointmentId() {
		return id;
	}

	public void setAppointmentId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getHour() {
		return hour;
	}

	public void setHour(Date hour) {
		this.hour = hour;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = true;
        // puede ser a false?Preguntar
	}

	public Boolean getPaid() {
		return paid;
	}

	public void setPaid(Boolean paid) {
		this.paid = true;
	}


	@Override
	public String toString() {
		return "Appointment{" +
		"id=" + id +
		", date='" + date + '\'' +
		", hour='" + hour + '\'' +
		", date='" + date + '\'' +
		", paid='" + paid + '\'' +
		'}';
	}
}
