package com.accenture.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.accenture.entities.Car;

public interface CarRepository extends CrudRepository<Car, Long> {

	public Car findByRegistrationNumber(String regisNum);

	public List<Car> findByAvailability(Boolean availability);

}
