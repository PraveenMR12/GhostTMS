package com.io.ghosttms.util;

import java.time.LocalDateTime;
import java.util.List;

import com.io.ghosttms.entity.*;

import jakarta.persistence.Embedded;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
public class ShipmentDto {


	private String shipmentID;
	
	List<Reference> references;
	
	private LocalDateTime pickupDate;
	private LocalDateTime deliveryDate;

	private SNLocation billToLocation;
	private SNLocation shipFromLocation;
	private SNLocation shipToLocation;

	private String status;

	private List<Items> items;

	private Rates rates;

}
