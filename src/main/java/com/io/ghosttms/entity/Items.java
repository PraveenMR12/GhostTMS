package com.io.ghosttms.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Items {

    private String itemId;
    private String itemDescription;
    private double height;
    private double width;
    private double length;
    private double weight;
    private int quantity;

    private String itemClass;
}
