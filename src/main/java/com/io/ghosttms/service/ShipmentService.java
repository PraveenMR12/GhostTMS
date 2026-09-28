package com.io.ghosttms.service;

import com.io.ghosttms.entity.Shipment;
import com.io.ghosttms.util.ShipmentDto;

import java.util.List;

public interface ShipmentService {

ShipmentDto createShipment(ShipmentDto shipment);

ShipmentDto updateShipment(ShipmentDto shipment);

void deleteShipment(ShipmentDto shipment);

ShipmentDto getShipment(ShipmentDto shipment);

List<ShipmentDto> getShipmentByFilter(ShipmentDto shipment);

}
