package com.vehicle.management.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import com.vehicle.management.dto.CustomerDto;
import com.vehicle.management.entity.CustomerMaster;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

	CustomerMaster mapCustomerDtoToCustomerEntity(CustomerDto customerDto);

	CustomerDto mapCustomerEntityToCustomerDto(CustomerMaster customer);

	List<CustomerDto> mapCustomerEntityListToCustomerDtoList(List<CustomerMaster> customer);

	default Page<CustomerDto> customerEntityListToCustomerDtoList(Page<CustomerMaster> customer) {
		return customer.map(this::mapCustomerEntityToCustomerDto);
	}
}
