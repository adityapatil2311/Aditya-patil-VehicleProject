package com.vehicle.management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.vehicle.management.dto.CustomerDto;
import com.vehicle.management.entity.CustomerMaster;
import com.vehicle.management.mapper.CustomerMapper;
import com.vehicle.management.payload.CustomerApiResponse;
import com.vehicle.management.repository.CustomerRepository;
import com.vehicle.management.service.CustomerService;
import com.vehicle.management.util.QRCodeGenerator;

import lombok.extern.slf4j.Slf4j;

@Service
@Component
@Slf4j
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	
	@Autowired
	private CustomerMapper customerMapper;

	@Override
	public CustomerApiResponse saveAllCustomerData(CustomerDto customerDto) {
		try {
			CustomerMaster customerMasterDbDetails = customerRepository.findByCustomerName(customerDto.getName());
			if (customerMasterDbDetails == null) {
				log.info("<<Start>> saveAllCustomerData called <<Start>>");
				log.info("CustomerDto" + customerDto);
				CustomerMaster customer = customerMapper.mapCustomerDtoToCustomerEntity(customerDto);
				String UniqueQRCode=QRCodeGenerator.createUniqueNoForQrCode(customer);
				customer.setQrCode(UniqueQRCode);
				customer.setQrCodeImage(QRCodeGenerator.getQRCodeImage(UniqueQRCode, 1, 1));
				
				customer.setIsDeleted(false);
				customer.setIsActive(true);
				log.info("CustomerMaster" + customer);

				CustomerMaster customer2 = customerRepository.save(customer);
				CustomerDto customerDto2 = customerMapper.mapCustomerEntityToCustomerDto(customer2);

				log.info("<<End>> addCustomerData called <<End>>");
				return new CustomerApiResponse(customerDto2, null, null, HttpStatus.CREATED,
						"customerData add successfully", false);
			} else {
				return new CustomerApiResponse(null, null, null, HttpStatus.NOT_FOUND,
						"customerName" + customerDto.getName() + "Already Exist", true);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("customer addData JDBCConnectionException: ", e);
		} catch (Exception e) {
			log.error("Exception in saveAllCustomerData service", e);
		}
		return new CustomerApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error",true);
	}
	
	@Override
	public CustomerApiResponse getAllCustomerData() {
		try {
			log.info("customerMaster getAll endpoint");
			final List<CustomerMaster> customerMasterFetchFromDb = (List<CustomerMaster>) customerRepository
					.findAllCustomerMaster();
			log.info("customerMapper getAll response: " + customerMasterFetchFromDb);
			if (!customerMasterFetchFromDb.isEmpty() && customerMasterFetchFromDb != null) {
				return new CustomerApiResponse(null,
						customerMapper.mapCustomerEntityListToCustomerDtoList(customerMasterFetchFromDb), null,
						HttpStatus.FOUND, "CustomerMaster list found successfully", true);
			} else {
				return new CustomerApiResponse(null, null, null, HttpStatus.NOT_FOUND, "customer List Not Found",
						false);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("Customer getAll HDBCConnection: ", e);
		} catch (final Exception e) {
			log.error("CustomerMaster getAll Exception: ", e);
		}
		return new CustomerApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", false);
	}

	@Override
	public CustomerApiResponse updateCustomerData(CustomerDto customerDto) {
		try {
			log.info("<<Start>> updateCustomerData called <<Start>>");
			Optional<CustomerMaster> customer = customerRepository.findCustomerMasterById(customerDto.getCustomerId());
			if (customer.isPresent()) {
				CustomerMaster existingCustomerMaster = customerMapper.mapCustomerDtoToCustomerEntity(customerDto);
				existingCustomerMaster.setIsActive(true);
				existingCustomerMaster.setIsDeleted(false);
				CustomerMaster updateEmployee = customerRepository.save(existingCustomerMaster);
				log.info("<<end>> updateCustomer <<end>>");
				return new CustomerApiResponse(customerMapper.mapCustomerEntityToCustomerDto(updateEmployee), null,
						null, HttpStatus.OK, "Customer Details update successfully", true);
			} else {
				return new CustomerApiResponse(null, null, null, HttpStatus.NOT_FOUND, "Customer Id not found", true);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("Customer addData JDBCConnectionException: ", e);
		} catch (Exception e) {
			log.error("Exception in updateCustomer server", e);
		}
		return new CustomerApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", true);
	}

	@Override
	public CustomerApiResponse deleteCustomerData(Integer id) {
		log.info("<<Start>>In Customer deleteCustomerById method<<Start>>");
		try {
			log.info("In Customer deleteCustomerById method ID: " + id);
			final Optional<CustomerMaster> customerIdFetchedFromDb = customerRepository.findCustomerMasterById(id);
			log.info(" deleteCustomerById response: " + customerIdFetchedFromDb);
			if (customerIdFetchedFromDb.isPresent()) {
				CustomerMaster customerFromDb = customerIdFetchedFromDb.get();
				customerFromDb.setIsDeleted(true);
				customerFromDb.setIsActive(false);
				customerRepository.save(customerFromDb);
				log.info("Updated Customer with id: " + id + ", active set to false");
				final CustomerDto customerDtoToSend = customerMapper.mapCustomerEntityToCustomerDto(customerFromDb);
				log.info("Sending updated customerIdFetchedFromDb response: " + customerDtoToSend);
				return new CustomerApiResponse(customerDtoToSend, null, null, HttpStatus.OK, " Deleted Successfully",
						true);
			} else {
				return new CustomerApiResponse(null, null, null, HttpStatus.NOT_FOUND, "Customer Doesn't Exists",
						false);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("Customer deleteById JDBCConnectionException:", e);
		} catch (final Exception e) {
			log.error("Customer deleteDepartmentById Exception: ", e);
		}
		return new CustomerApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", true);
	}

	@Override
	public CustomerApiResponse getByIdCustomerData(Integer id) {
		log.info("<<Start>>In Customer getById method<<Start>>");
		try {
			final Optional<CustomerMaster> customerFetchedFromDb = customerRepository.findCustomerMasterById(id);
			log.info("customer getById response: " + customerFetchedFromDb);
			if (customerFetchedFromDb.isPresent()) {
				final CustomerDto customerDto = customerMapper
						.mapCustomerEntityToCustomerDto(customerFetchedFromDb.get());
				log.info("Sending customerFetchedFromDb response: " + customerDto);
				return new CustomerApiResponse(customerDto, null, null, HttpStatus.OK,
						"customerId Fetched Successfully", true);
			} else {
				return new CustomerApiResponse(null, null, null, HttpStatus.NOT_FOUND, "customerId Doesn't Exists",
						false);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("customer getAll JDBCConnectionException:", e);
		} catch (final Exception e) {
			log.error("customer getById Exception: ", e);
		}
		return new CustomerApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", false);
	}

	@Override
	public CustomerApiResponse findAllPagination(Optional<Integer> pageNum, Optional<Integer> pageSize) {
		log.info("<<Start>> getAllPagination <<Start>>");
		try {
			final Page<CustomerMaster> customerList = customerRepository
					.findAllPagination(PageRequest.of(pageNum.orElse(0), pageSize.orElse(3)));
			log.info("customerList" + customerList);
			if (customerList != null && !customerList.isEmpty()) {
				final Page<CustomerDto> customerDto = customerMapper.customerEntityListToCustomerDtoList(customerList);
				return new CustomerApiResponse(null, null, customerDto, HttpStatus.FOUND, "customer List Found", true);
			} else {
				return new CustomerApiResponse(null, null, null, HttpStatus.NOT_FOUND, "customer List Not Found",
						false);
			}
		} catch (final org.hibernate.exception.JDBCConnectionException e) {
			log.error("database connectivity error", e);
		} catch (Exception e) {
			log.error("Exception occurs in getAllPagination method", e);
		}
		return new CustomerApiResponse(null, null, null, HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", true);
	}
}
