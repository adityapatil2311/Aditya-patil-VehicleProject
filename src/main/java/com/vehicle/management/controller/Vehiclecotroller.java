//package com.vehicle.management.controller;
//
//import java.util.List;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.vehicle.management.dto.VehicleDto;
//import com.vehicle.management.payload.ApiResponse;
//@RestController
//@RequestMapping("/api")
//public interface Vehiclecotroller {
//	
//	//public ApiResponse<VehicleDto> saveVehicle(@RequestBody VehicleDto vehicleDto);
//	
//	public ApiResponse<VehicleDto> getVehicle(@PathVariable("id") long id);
//	
//	public ResponseEntity<List<VehicleDto>> getAllVehicle(
//			@RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
//			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize
//			    );
//	public ApiResponse<VehicleDto> updateVehicles(@PathVariable("id") long id, @RequestBody VehicleDto vehicleDto);
//	
//	public ApiResponse<VehicleDto> deleteVehicle(@PathVariable("id") long id);
//
//	public String getVehicleTemp() ;
//	
//	
//
//}
