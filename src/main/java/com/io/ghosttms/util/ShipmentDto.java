package com.io.ghosttms.util;

import java.time.LocalDateTime;
import java.util.List;

import com.io.ghosttms.entity.AuditLogs;
import com.io.ghosttms.entity.Item;
import com.io.ghosttms.entity.References;

import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
public class ShipmentDto {

	
	List<References> references;
	
	private LocalDateTime pickupDate;
	private LocalDateTime deliveryDate;
	
	private String status;
	
	private List<AuditLogs> logs;
	
	@OneToMany
	private List<Item> items;
}
