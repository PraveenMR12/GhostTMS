package com.io.ghosttms.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Rates {


    private String carrierSCAC;
    private String carrierContract;
    private String mode;

    private double totalCharge;
    private double lineHaulCharge;
    private double fuelCharge;

}
