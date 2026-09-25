package com.io.ghosttms.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
@Entity
public class AuditLogs {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long auditLogID;
	private String data;
	private String username;
	private LocalDateTime dateAndTime;
	private String object;

	public AuditLogs(String data, String username, LocalDateTime createDate, String object) {
	}
}
