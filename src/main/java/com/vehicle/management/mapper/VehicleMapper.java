package com.vehicle.management.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.vehicle.management.dto.VehicleDto;
import com.vehicle.management.entity.Vehicle;

@Mapper(componentModel="spring")
public interface VehicleMapper {
	
	
	
	Vehicle mapVehicleDtoToVehicle(VehicleDto vehicledto);
	
	VehicleDto mapVehicleToVehicleDto(Vehicle vehicle);
	
	List<VehicleDto> mapVehicleListToVehicleDtoList(List<Vehicle> vehicle);
	
	
	

}
