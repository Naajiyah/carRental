package com.accenture.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.accenture.entities.Car;
import com.accenture.exceptions.InvalidRegistrationNumberException;
import com.accenture.service.CarService;

@Controller
public class CarController {

	private CarService carService;

	public CarController(CarService carService) {
		this.carService = carService;
	}

	@GetMapping("/add")
	public String createCar() {
		return "add";
	}

	@PostMapping("/adding")
	public String home(@RequestParam("registrationNumber") String registrationNumber,
			@RequestParam("year") Integer year, @RequestParam("carModel") String modelType,
			@RequestParam("pricePerDay") double pricePerDay, Model model) {
		Car car = new Car();
		car.setAvailability(true);
		car.setModel(modelType);
		car.setPricePerDay(pricePerDay);
		car.setRegistrationNumber(registrationNumber);
		car.setYear(year);
		carService.createCar(car);
		model.addAttribute("cars", carService.getAll());
		return "home";

	}

	@GetMapping("/update")
	public String UpdateCar() {
		return "update";
	}

	@PostMapping("/updating")
	public String updateCar(@RequestParam("registrationNumber") String registrationNumber,
			@RequestParam("pricePerDay") double pricePerDay, Model model) {

		try {
			carService.updateCar(registrationNumber, pricePerDay);
		} catch (InvalidRegistrationNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("cars", carService.getAll());
		return "home";
	}

	@GetMapping({ "", "/", "/home" })
	public String home(Model model) {
		model.addAttribute("cars", carService.getAll());
		return "home";
	}

	@GetMapping("/delete/{registrationNumber}")
	public String deleteCar(@PathVariable("registrationNumber") String registrationNumber) {
		System.out.println(registrationNumber);
		carService.deleteCar(registrationNumber);
		return "home";
	}
}
