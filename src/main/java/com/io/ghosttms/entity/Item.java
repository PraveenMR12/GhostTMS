package com.io.ghosttms.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
