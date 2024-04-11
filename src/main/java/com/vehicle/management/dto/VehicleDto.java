package com.vehicle.management.dto;

import java.time.LocalDateTime;

import com.vehicle.management.entity.AuditEntity;

import jakarta.persistence.Id;
import lombok.Data;


@Data

public class VehicleDto {

	@Id
	private long id;	
	private String vehicleRegistrationNumber;
	private String ownername;
	private String brand;
	private LocalDateTime registrationExpires;
	private boolean isActive;
	private String createdby;
	private LocalDateTime creationtime;
	private String modifiedby;
	private LocalDateTime modifiedtime;
	private AuditEntity auditEntity;
	private CustomerDto customerDto;
	//private LocalDateTime startDate;
	//private LocalDateTime endDate;
	
	
	

}
