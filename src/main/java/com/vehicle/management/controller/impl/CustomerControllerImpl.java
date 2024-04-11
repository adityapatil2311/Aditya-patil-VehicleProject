package com.vehicle.management.controller.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vehicle.management.controller.CustomerController;
import com.vehicle.management.dto.CustomerDto;
import com.vehicle.management.payload.CustomerApiResponse;
import com.vehicle.management.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerControllerImpl implements CustomerController {

	
	@Autowired
	private CustomerService customerService;

	@Override
	public ResponseEntity<CustomerApiResponse> addAllData(CustomerDto customerDto) {
		log.error("Exception in addAllCustomer controller");
		ResponseEntity<CustomerApiResponse> responseEntity = new ResponseEntity<>(
				customerService.saveAllCustomerData(customerDto), HttpStatus.OK);
		log.info("<<END>> addAllCustomers <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<CustomerApiResponse> getAllData(Optional<Integer> pageNum, Optional<Integer> pageSize) {
		log.error("Exception in getAllCustomers controller");
		ResponseEntity<CustomerApiResponse> responseEntity = new ResponseEntity<>(
				customerService.findAllPagination(pageNum, pageSize), HttpStatus.OK);
		log.info("<<END>> getAllCustomers <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<CustomerApiResponse> updateCustomers(CustomerDto customerDto) {
		log.error("Exception in updateCustomers controller");
		ResponseEntity<CustomerApiResponse> responseEntity = new ResponseEntity<>(
				customerService.updateCustomerData(customerDto), HttpStatus.OK);
		log.info("<<END>> updateCustomers <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<CustomerApiResponse> deleteData(Integer id) {
		log.error("Exception in deleteCustomers controller");
		ResponseEntity<CustomerApiResponse> responseEntity = new ResponseEntity<>(customerService.deleteCustomerData(id),
				HttpStatus.OK);
		log.info("<<END>> deleteCustomers <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<CustomerApiResponse> getById(Integer id) {
		log.error("Exception in getByIdCustomers controller");
		ResponseEntity<CustomerApiResponse> responseEntity = new ResponseEntity<>(
				customerService.getByIdCustomerData(id), HttpStatus.OK);
		log.info("<<END>> getByIdCustomers <<END>>");
		return responseEntity;
	}

	@Override
	public ResponseEntity<CustomerApiResponse> getAllCustomerData() {
		log.error("Exception in getAllCustomers controller");
		ResponseEntity<CustomerApiResponse> responseEntity = new ResponseEntity<>(customerService.getAllCustomerData(),
				HttpStatus.OK);
		log.info("<<END>> getAllCustomers <<END>>");
		return responseEntity;
	}
}
