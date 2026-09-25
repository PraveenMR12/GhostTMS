package com.io.ghosttms.serviceimpl;

import com.io.ghosttms.entity.AuditLogs;
import com.io.ghosttms.repository.AuditLogsRepository;
import com.io.ghosttms.service.ShipmentService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.io.ghosttms.entity.Shipment;
import com.io.ghosttms.repository.ShipmentRepository;
import com.io.ghosttms.util.ShipmentDto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class ShipmentServiceImpl implements ShipmentService {

	private final ShipmentRepository shipmentRepo;
	private final AuditLogsRepository logRepo;
	private final ModelMapper model;

	public ShipmentServiceImpl(ShipmentRepository shipmentRepo, AuditLogsRepository logRepo, ModelMapper model){
        this.shipmentRepo = shipmentRepo;
        this.logRepo = logRepo;
        this.model = model;
	}

	@Transactional
	public ShipmentDto createShipment(ShipmentDto shipmentDto) {

		if(shipmentDto.getShipmentID()==null){

			shipmentDto.setShipmentID(shipmentIDGenerator());
		}


		Shipment shipment = shipmentRepo.save(model.map(shipmentDto, Shipment.class));
		shipment.setCreateDate(LocalDateTime.now(ZoneId.of("Asia/Kolkata")));
		AuditLogs log = new AuditLogs(
				"Shipment created",
				"username",
				shipment.getCreateDate(),
				"Shipment");
		logRepo.save(log);
		shipment.getLogs().add(log);
		
		return model.map(shipment, ShipmentDto.class);
	}

    private String shipmentIDGenerator() {
        return "SN"+shipmentRepo.getNextShipmentSequence();
    }

	@Override
	public ShipmentDto updateShipment(ShipmentDto shipment) {
		return null;
	}

	@Override
	public void deleteShipment(ShipmentDto shipment) {

	}

	@Override
	public ShipmentDto getShipment(ShipmentDto shipment) {
		return null;
	}

	@Override
	public List<ShipmentDto> getShipmentByFilter(ShipmentDto shipment) {
		return List.of();
	}


}
