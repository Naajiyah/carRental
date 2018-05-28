package com.accenture.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.accenture.entities.Car;
import com.accenture.entities.Rental;
import com.accenture.repositories.CarRepository;
import com.accenture.repositories.RentalRepository;

public class RentalService {
	
	@Autowired
	private RentalRepository rentalrepository;
	
	private CarRepository carRepository;
	
	private Rental rental;
	
	public List <Car> carRentedSpecificPeriod(Date startDate,Date endDate){
		List <Car> ls=rentalrepository.findByStartDateBetween(startDate, endDate);
		return ls;
	}
	
	public double totalAmountRentedCar() {
		List<Car> ls=carRepository.findByAvailability(false);
		double total_amount=0.0;
		
		for(Car car:ls){
			double amount=0.0;
			List<Rental>rentals = car.getRental();
			for (Rental rental : rentals) {
				Date startDate=rental.getStartDate();
				Date endDate=rental.getEndDate();
			
				long diff = startDate.getTime()-endDate.getTime();
				long no_of_days = diff / (24 * 60 * 60 * 1000);
				amount += no_of_days*car.getPricePerDay();
				
			}
			total_amount+=amount;
		}
		return total_amount;
			
		}
	}
	


