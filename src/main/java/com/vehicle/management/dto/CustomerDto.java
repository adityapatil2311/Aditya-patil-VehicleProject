package com.vehicle.management.dto;

import com.vehicle.management.entity.AuditEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
	private Integer customerId;
	private String name;
	private String Address;
	private Long Contact;
	private Boolean isActive;
	private Boolean isDeleted;
	private AuditEntity auditEntity;
	private String qrCode;
	private byte[] qrCodeImage;

}
