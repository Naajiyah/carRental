package com.accenture.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.accenture.entities.Car;
import com.accenture.exceptions.InvalidRegistrationNumberException;
import com.accenture.repositories.CarRepository;

@Service
public class CarService {

	private CarRepository carrepository;

	public CarService(CarRepository carrepository) {
		this.carrepository = carrepository;
	}

	public Iterable<Car> getAll() {
		return carrepository.findAll();
	}

	public void createCar(Car car) {
		carrepository.save(car);
	}

	public void updateCar(String registrationNumber, double pricePerDay) throws InvalidRegistrationNumberException {
		Car car = carrepository.findByRegistrationNumber(registrationNumber);
		if (car == null) {
			throw new InvalidRegistrationNumberException("Registration Number not valid");
		}
		car.setPricePerDay(pricePerDay);
		carrepository.save(car);

	}

	public void deleteCar(String registrationNumber) {
		Car car = carrepository.findByRegistrationNumber(registrationNumber);
		carrepository.delete(car);
	}

	public void deleteById(Long id) {
		carrepository.deleteById(id);
	}
	
	public Car findByRegisNum(String regisNum) {
		Car car = carrepository.findByRegistrationNumber(regisNum);
		return car;
	}

	public List<Car> availableToRent() {
		List<Car> ls = carrepository.findByAvailability(true);
		return ls;

	}

}
