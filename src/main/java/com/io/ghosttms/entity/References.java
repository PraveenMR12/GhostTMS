package com.io.ghosttms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class References {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long referenceID;
	String type;
	String value;

}
