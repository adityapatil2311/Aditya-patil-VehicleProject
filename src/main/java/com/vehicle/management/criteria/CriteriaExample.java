package com.vehicle.management.criteria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vehicle.management.entity.Vehicle;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class CriteriaExample {


	@Autowired
	private EntityManager Em;

	public List<Vehicle> findbyEntity() {

		// Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = Em.getCriteriaBuilder();
		CriteriaQuery<Vehicle> criteriaQuery = criteriaBuilder.createQuery(Vehicle.class);
		Root<Vehicle> root = criteriaQuery.from(Vehicle.class);
		criteriaQuery.select(root);

		return Em.createQuery(criteriaQuery).getResultList();

	}

	public List<Vehicle> findbyname(String ownername) {

		CriteriaBuilder criteriaBuilder = Em.getCriteriaBuilder();
		CriteriaQuery<Vehicle> criteriaQuery = criteriaBuilder.createQuery(Vehicle.class);

		Root<Vehicle> root = criteriaQuery.from(Vehicle.class);

		criteriaQuery.select(root);

		// Predicate vehicleRegistrationNumberPredicate
		// =criteriaBuilder.equal(root.get(vehicleRegistrationNumber),
		// vehicleRegistrationNumber);
		Predicate ownernamePredicate = criteriaBuilder.like(root.get("ownername"), "%" + ownername + "%");

		criteriaQuery.where(ownernamePredicate);

		TypedQuery<Vehicle> query = Em.createQuery(criteriaQuery);

		return query.getResultList();
	}

}
