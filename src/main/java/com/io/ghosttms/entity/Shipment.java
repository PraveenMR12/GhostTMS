package com.io.ghosttms.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Shipment {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "OId_generator")
	@SequenceGenerator(name = "OId_generator",
			sequenceName = "OId_generator",
			allocationSize = 10,
			initialValue = Integer.MAX_VALUE)
	@Column(name = "OId")
	private Long OId;

	@Column(unique = true)
	private String shipmentID;

	@ElementCollection
	@CollectionTable(
			name = "reference",
			joinColumns = @JoinColumn(name = "OId")
	)
	List<Reference> references = new ArrayList<>();

	private LocalDateTime createDate;
	
	private LocalDateTime pickupDate;
	private LocalDateTime deliveryDate;

	@Embedded
	private SNLocation billToLocation;
	@Embedded
	private SNLocation shipFromLocation;
	@Embedded
	private SNLocation shipToLocation;
	
	private String status;

	@OneToMany
	private List<AuditLogs> logs = new ArrayList<>();

	@ElementCollection
	@CollectionTable(
			name = "items",
			joinColumns = @JoinColumn(name = "OId")
	)
	private List<Items> items = new ArrayList<>();

	@Embedded
	private Rates rates;

}