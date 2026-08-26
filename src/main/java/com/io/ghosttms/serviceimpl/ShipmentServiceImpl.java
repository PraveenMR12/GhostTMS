package com.io.ghosttms.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.io.ghosttms.entity.Shipment;
import com.io.ghosttms.repository.ShipmentRepository;
import com.io.ghosttms.util.ShipmentDto;

@Service
public class ShipmentServiceImpl {
	
	@Autowired
	private ShipmentRepository shipmentRepo;
	private ModelMapper model;
	
	public ShipmentDto createShipment(ShipmentDto shipmentDto) {
		
		Shipment shipment = shipmentRepo.save(model.map(shipmentDto, Shipment.class));
		
		
		return model.map(shipment, ShipmentDto.class);
	}
	
	
	

}
