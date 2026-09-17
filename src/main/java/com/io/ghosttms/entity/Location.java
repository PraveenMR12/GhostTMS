package com.io.ghosttms.entity;


import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "OId_generator")
    @SequenceGenerator(name = "OId_generator",
    sequenceName = "OId_generator",
    allocationSize = 10,
    initialValue = Integer.MAX_VALUE)
    private Long oId;

    private String locationCode;

    private String locationType;
    private String locationName;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String country;
    private String postalCode;

    private String contactName;
    private String phoneNumber;
    private String email;

}
