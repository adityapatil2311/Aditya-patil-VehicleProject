package com.vehicle.management.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vehicle.management.dto.CustomerDto;
import com.vehicle.management.payload.CustomerApiResponse;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("customers")
@SecurityRequirement(name = "jwtauth")
public interface CustomerController {

	@PostMapping("/saveData")
	public ResponseEntity<CustomerApiResponse> addAllData(@RequestBody CustomerDto customerDto);

	@GetMapping("/getallPagination")
	public ResponseEntity<CustomerApiResponse> getAllData(
			@RequestParam(name = "pageNum", required = false) Optional<Integer> pageNum,
			@RequestParam(name = "pageSize", required = false) Optional<Integer> pageSize);

	@PutMapping("/update")
	public ResponseEntity<CustomerApiResponse> updateCustomers(@RequestBody CustomerDto customerDto);

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<CustomerApiResponse> deleteData(@PathVariable("id") Integer id);

	@GetMapping("/getById/{id}")
	public ResponseEntity<CustomerApiResponse> getById(@PathVariable("id") Integer id);

	@GetMapping("/getAll")
	public ResponseEntity<CustomerApiResponse> getAllCustomerData();
}
