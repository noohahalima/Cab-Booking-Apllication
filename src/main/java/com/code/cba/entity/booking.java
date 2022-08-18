package com.code.cba.entity;

import java.io.Serializable;

import javassist.bytecode.ByteArray;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="booking")
public class booking {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="bookingId")
	 int bookingId;

	@Column(name="startLocation")
	 String startLocation;
	
	@Column(name="endLocation")
	 String endLocation;
	

	
	@Column(name="startTime")
	 float startTime;
	
	//relationship will be with cab
		@OneToOne
		@JoinColumn(name="CabId")
		Cab cab;
		
		
	
		//relationship will be with driver
		@OneToOne
		@JoinColumn(name="driverId")
		Driver driver;

		public int getBookingId() {
			return bookingId;
		}

		public void setBookingId(int bookingId) {
			this.bookingId = bookingId;
		}

		public String getStartLocation() {
			return startLocation;
		}

		public void setStartLocation(String startLocation) {
			this.startLocation = startLocation;
		}

		public String getEndLocation() {
			return endLocation;
		}

		public void setEndLocation(String endLocation) {
			this.endLocation = endLocation;
		}

		public float getStartTime() {
			return startTime;
		}

		public void setStartTime(float startTime) {
			this.startTime = startTime;
		}

		public Cab getCab() {
			return cab;
		}

		public void setCab(Cab cab) {
			this.cab = cab;
		}

		public Driver getDriver() {
			return driver;
		}

		public void setDriver(Driver driver) {
			this.driver = driver;
		}

		public booking() {
			
		
		}
		
		
	

}