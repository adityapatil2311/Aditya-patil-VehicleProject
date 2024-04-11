package com.vehicle.management.service;

import java.io.IOException;
import java.util.List;

import com.lowagie.text.DocumentException;
import com.vehicle.management.dto.VehicleDto;
import com.vehicle.management.payload.ApiResponse;

import jakarta.servlet.http.HttpServletResponse;

public interface VehicleService {

	ApiResponse<VehicleDto> saveVehicle(VehicleDto vehicleDto);

	ApiResponse<VehicleDto> getVehicleById(long id);

	List<VehicleDto> getAllVehicles(Integer pageNumber, Integer pageSize);

	ApiResponse<VehicleDto> updateVehicle(long id, VehicleDto vehicleDto);

	ApiResponse<VehicleDto> deleteVehicle(long id);

	String getVehiclebyTemplet();

	public void export(HttpServletResponse response, VehicleDto vehicleDto) throws DocumentException, IOException;

	// List<VehicleDto> getAllvehiclelist(VehicleDto vehicleDto);
//	public UserDetailsService userDetailsService();
//	

}
