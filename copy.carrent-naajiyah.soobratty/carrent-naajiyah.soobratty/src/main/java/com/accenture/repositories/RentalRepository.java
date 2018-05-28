package com.accenture.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.entities.Car;
import com.accenture.entities.Rental;

public interface RentalRepository extends JpaRepository<Rental,Long> {
	
	public List<Car> findByStartDateBetween(Date startDate, Date endDate);
	
	

}
