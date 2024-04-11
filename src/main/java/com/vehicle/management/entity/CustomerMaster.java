package com.vehicle.management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Customer")
public class CustomerMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;
	private String name;
	private String Address;
	private Long Contact;
	private Boolean isActive;
	private Boolean isDeleted;
	@Embedded
	private AuditEntity auditEntity;
	@Column(name = "qr_code")
	private String qrCode;
	
	@Column(name = "qr_code_image")
	private byte[] qrCodeImage;


}
