package com.vehicle.management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vehicle.management.entity.CustomerMaster;



@Repository
public interface CustomerRepository extends JpaRepository<CustomerMaster, Integer>{

	@Query("from CustomerMaster cm where cm.isDeleted=false and cm.isActive=true ORDER BY cm.customerId DESC")
	Page<CustomerMaster> findAllPagination(Pageable page);

	@Query("from CustomerMaster cm where cm.isDeleted=false and cm.isActive=true ORDER BY cm.customerId DESC")
	List<CustomerMaster> findAllCustomerMaster();

	@Query("select cm from CustomerMaster cm where cm.customerId=?1 and cm.isDeleted=false and cm.isActive=true")
	Optional<CustomerMaster> findCustomerMasterById(Integer id);
	
	@Query("From CustomerMaster cm where cm.name=?1 and cm.isDeleted=false and cm.isActive=true")
	CustomerMaster findByCustomerName(String name);
}