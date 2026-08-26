package com.io.ghosttms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.io.ghosttms.util.CarrierDto;

@Controller
@RequestMapping(path = "/Carrier")
public class CarrierController {
	
	
	@PostMapping(path = "/create")
	public String createCarrier(CarrierDto carrier) {
		
		return null;
	}
	
	@PutMapping(path = "/update")
	public String updateCarrier(CarrierDto carrier) {
		
		return null;
	}

}
