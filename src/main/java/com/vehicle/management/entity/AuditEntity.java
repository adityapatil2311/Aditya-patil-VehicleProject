package com.vehicle.management.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuditEntity implements Serializable {

	private Integer createdBy;

	private Integer modifiedBy;

	private LocalDateTime createdDate;

	private LocalDateTime modifiedDate;
}
