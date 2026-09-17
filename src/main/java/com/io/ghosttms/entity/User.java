package com.io.ghosttms.entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;




@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
			generator = "OId_generator")
	@SequenceGenerator(name = "OId_generator",
			sequenceName = "OId_generator",
			allocationSize = 10,
			initialValue = Integer.MAX_VALUE)
	private Long OId;
	private int userId;
	private String fullName;
	private String email;
	private String password;
	private long phoneNumber;
	private Date createdDate;
	private Date modifiedDate;
	@Enumerated(EnumType.STRING)
	private Role role;
	private String gender;
	
}
