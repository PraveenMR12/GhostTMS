package com.io.ghosttms.entity;


import jakarta.persistence.*;
import lombok.Data;



@Data
@Entity
public class Carrier {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "OId_generator")
	@SequenceGenerator(name = "OId_generator",
			sequenceName = "OId_generator",
			allocationSize = 10,
			initialValue = Integer.MAX_VALUE)
	private Long OId;
	private String carrierID;
	private String carrierName;
	private String carrierScac;
	private String tenderType;
	

}
