package com.io.ghosttms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.io.ghosttms.entity.Shipment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long>{

    @Query(value = "SELECT shipment_seq.NEXTVAL FROM dual",
            nativeQuery = true)
    Long getNextShipmentSequence();

}
