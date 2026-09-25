package com.io.ghosttms.entity;


import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Reference {
    private long referenceID;
    String type;
    String value;
}
