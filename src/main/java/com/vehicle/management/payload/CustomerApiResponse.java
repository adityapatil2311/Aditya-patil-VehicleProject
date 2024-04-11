package com.vehicle.management.payload;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import com.vehicle.management.dto.CustomerDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerApiResponse {
	private CustomerDto customerDto;
	private List<CustomerDto> customerDtoList;
	private Page<CustomerDto> customerDtoPage;
	private HttpStatus status;
	private String message;
	private Boolean error;
	
}
