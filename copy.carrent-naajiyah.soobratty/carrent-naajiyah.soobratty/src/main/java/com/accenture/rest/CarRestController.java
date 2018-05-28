package com.accenture.rest;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.entities.Car;
import com.accenture.repositories.CarRepository;
import com.accenture.service.CarService;

@RestController
@RequestMapping("/car")
public class CarRestController {
	private final CarService carService;
	private final CarRepository carRepository;

	public CarRestController(CarService carService, CarRepository carRepository) {
		this.carService = carService;
		this.carRepository = carRepository;
	}

	@GetMapping()
	public Iterable<Car> getAllCars() {
		return carService.getAll();
	}
	
	@PostMapping()
	public Car addCar(
			@RequestParam("registrationNumber") String registrationNumber,
			@RequestParam("year") Integer year,
			@RequestParam("carModel") String modelType,
			@RequestParam("pricePerDay") double pricePerDay
		) {
		Car car = new Car();
		car.setAvailability(true);
		car.setModel(modelType);
		car.setPricePerDay(pricePerDay);
		car.setRegistrationNumber(registrationNumber);
		car.setYear(year);
		carService.createCar(car);
		return car;
	}
	
	@PutMapping("/id")
	public Car updateCar(
		@PathParam("id") Long id,
		@RequestParam("registrationNumber") String registrationNumber,
		@RequestParam("year") Integer year,
		@RequestParam("model") String model,
		@RequestParam("pricePerDay") double pricePerDay,
		@RequestParam("availbility") boolean availability
	){
		Car car = carRepository.findById(id);
		if(car != null) {
			car.setRegistrationNumber(registrationNumber);
			car.setYear(year);
			car.setModel(model);
			car.setPricePerDay(pricePerDay);
			car.setAvailability(availability);
			carRepository.save(car);
		}
		return car;
	}
	
	@DeleteMapping("/{id}")
	public void deleteCar(@PathParam("id") Long id) {
		carService.deleteById(id);
	}
}
