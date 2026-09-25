package com.io.ghosttms.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class SNLocation {

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
