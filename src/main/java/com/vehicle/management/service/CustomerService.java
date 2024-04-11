package com.vehicle.management.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vehicle.management.dto.CustomerDto;
import com.vehicle.management.payload.CustomerApiResponse;

@Service
public interface CustomerService {

	CustomerApiResponse saveAllCustomerData(CustomerDto customerDto);

	CustomerApiResponse getAllCustomerData();

	CustomerApiResponse updateCustomerData(CustomerDto customerDto);

	CustomerApiResponse deleteCustomerData(Integer id);

	CustomerApiResponse getByIdCustomerData(Integer id);

	CustomerApiResponse findAllPagination(Optional<Integer> pageNum, Optional<Integer> pageSize);
}
