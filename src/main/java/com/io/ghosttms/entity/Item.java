package com.io.ghosttms.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "OId_generator")
	@SequenceGenerator(name = "OId_generator",
			sequenceName = "OId_generator",
			allocationSize = 10,
			initialValue = Integer.MAX_VALUE)
	private Long OId;
	private String itemId;
	private String itemDescription;
	private double height;
	private double width;
	private double length;
	private double weight;
	private int quantity;
	
	private String itemClass;
	
	@ManyToOne
	private Shipment shipment;
	

}
