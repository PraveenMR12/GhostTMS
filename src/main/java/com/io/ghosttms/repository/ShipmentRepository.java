package com.io.ghosttms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.io.ghosttms.entity.Shipment;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long>{

}
