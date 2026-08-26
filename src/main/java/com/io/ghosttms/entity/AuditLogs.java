package com.io.ghosttms.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class AuditLogs {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String auditLogID;
	private String data;
	private String username;
	private LocalDateTime dateAndTime;
	private String object;

}
