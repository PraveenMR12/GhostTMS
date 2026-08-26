package com.io.ghosttms.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Shipment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long shipmentID;
	@OneToMany
	List<References> references;
	
	private LocalDateTime pickupDate;
	private LocalDateTime deliveryDate;
	
	private String status;

	@OneToMany
	private List<AuditLogs> logs;
	
	@OneToMany
	private List<Item> items;

}