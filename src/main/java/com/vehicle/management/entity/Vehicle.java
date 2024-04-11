package com.vehicle.management.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity

@Table(name = "vehicle")
@Data
public class Vehicle {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(unique = true)
	private String vehicleRegistrationNumber;
	private String ownername;
	private String brand;
	private LocalDateTime registrationExpires;
	private boolean isActive;
	@ManyToOne
	@JoinColumn(name = "customerId")
	private CustomerMaster customerMaster;
	private LocalDateTime creationtime;
	@Embedded
	private AuditEntity auditEntity;
	
//	private String modifiedby;
//	private LocalDateTime modifiedtime;
//	private LocalDateTime startDate;
//	private LocalDateTime endDate;

}
