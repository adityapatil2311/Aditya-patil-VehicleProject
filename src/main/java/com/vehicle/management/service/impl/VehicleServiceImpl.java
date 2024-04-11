package com.vehicle.management.service.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.vehicle.management.dto.VehicleDto;
import com.vehicle.management.entity.CustomerMaster;
import com.vehicle.management.entity.Vehicle;
import com.vehicle.management.exception.ResourceNotFoundException;
import com.vehicle.management.mapper.VehicleMapper;
import com.vehicle.management.payload.ApiResponse;
import com.vehicle.management.repository.VehicleRepository;
import com.vehicle.management.service.VehicleService;

import jakarta.servlet.http.HttpServletResponse;

@Service
@Component
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private VehicleMapper vehicleMapper;

	@Autowired
	private RestTemplate restTemplate;

	Logger logger = LoggerFactory.getLogger(VehicleServiceImpl.class);

	@Override

	public ApiResponse<VehicleDto> saveVehicle(VehicleDto vehicleDto) {
		try {

			Vehicle vehicle = vehicleMapper.mapVehicleDtoToVehicle(vehicleDto);
			CustomerMaster customerMaster = new CustomerMaster();
			customerMaster.setCustomerId(vehicleDto.getCustomerDto().getCustomerId());
			vehicle.setCustomerMaster(customerMaster);
			VehicleDto vehicleDto1 = vehicleMapper.mapVehicleToVehicleDto(vehicleRepository.save(vehicle));

			logger.trace("starting add vehicle trace");
			logger.info("vehicle added successfully");

			return new ApiResponse<VehicleDto>(vehicleDto1, null, null, "vehicle added successfully", HttpStatus.OK,

					false);

		} catch (Exception e) {
			logger.error("alredy exist");
			return new ApiResponse<VehicleDto>(null, null, null, "alredy exist", HttpStatus.NOT_FOUND, true);

		}

	}

	// @Cacheable(cacheNames = "Vehicle")
	@Override
	public ApiResponse<VehicleDto> getVehicleById(long id) {
		// logger.info("from cache");

		try {
			Vehicle vehicle = vehicleRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("vehicle", "id", id));
			VehicleDto vehicleDto = vehicleMapper.mapVehicleToVehicleDto(vehicle);

			logger.info("vehicle data from database");

			// System.out.println("from data base");
			return new ApiResponse<VehicleDto>(vehicleDto, null, null, "Vehicle data", HttpStatus.OK, false);

		} catch (ResourceNotFoundException ex) {
			logger.error("vehicle id not present ");
			return new ApiResponse<VehicleDto>(null, null, null, ex.getMessage(), HttpStatus.NOT_FOUND, true);
		} catch (Exception e) {
			logger.error("vehicle not found");
			return new ApiResponse<VehicleDto>(null, null, null, "Internal Server Error", HttpStatus.NOT_FOUND, true);
		}

	}

	@Override
	public List<VehicleDto> getAllVehicles(Integer pageNumber, Integer pageSize) {

		Pageable p = PageRequest.of(pageNumber, pageSize);
		Page<Vehicle> pagepost = vehicleRepository.findAll(p);
		List<Vehicle> vehicle = pagepost.getContent();

		logger.info("all vehicle are present");

		return vehicleMapper.mapVehicleListToVehicleDtoList(vehicle);
	}

	@Override
	// @CachePut(cacheNames = "updateVehicle")

	public ApiResponse<VehicleDto> updateVehicle(long id, VehicleDto vehicleDto) {

		try {

			Vehicle existingvehicle = vehicleRepository.findById(id).orElse(null);

			existingvehicle.setOwnername(vehicleDto.getOwnername());
			existingvehicle.setBrand(vehicleDto.getBrand());
			existingvehicle.setRegistrationExpires(vehicleDto.getRegistrationExpires());
			existingvehicle.setCreationtime(vehicleDto.getCreationtime());
			// existingvehicle.setModifiedby(vehicleDto.getModifiedby());

			VehicleDto vehicleDto2 = vehicleMapper.mapVehicleToVehicleDto(vehicleRepository.save(existingvehicle));
			logger.info("vehicle update succefully");
			return new ApiResponse<VehicleDto>(vehicleDto2, null, null, "vehicle update sccessfully", HttpStatus.OK,
					false);
		} catch (ResourceNotFoundException ex) {
			return new ApiResponse<VehicleDto>(null, null, null, ex.getMessage(), HttpStatus.NOT_FOUND, false);
		} catch (Exception e1) {

			logger.error("vehicle not present");
			return new ApiResponse<VehicleDto>(null, null, null, "vehicle  not present", HttpStatus.NOT_FOUND, true);
		}
	}

	@Override
	// @CacheEvict(cacheNames = "vehicle")

	public ApiResponse<VehicleDto> deleteVehicle(long id) {

		try {
			vehicleRepository.deleteById(id);
			logger.info("vehicle delete succefully");
			return new ApiResponse<VehicleDto>(null, null, null, "vehicle delete succefully", HttpStatus.OK, false);

		} catch (Exception e) {

			return new ApiResponse<VehicleDto>(null, null, null, "vehicle not found ", HttpStatus.NOT_FOUND, true);

		}

	}

	@Override

	public String getVehiclebyTemplet() {

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			ResponseEntity<String> templateData = restTemplate.exchange("https://jsonplaceholder.typicode.com/todos/1",
					HttpMethod.GET, entity, String.class, headers);

			logger.info("templet are present");
			return templateData.getBody();
		} catch (Exception e) {

			logger.error("not present");
			e.printStackTrace();

		}
		return "rest templet";

	}

	@Override
	public void export(HttpServletResponse response, VehicleDto vehicleDto) throws DocumentException, IOException {
		// String filepath = "C:\\Pdf";

		try {
			Document document = new Document(PageSize.A4);

			PdfWriter.getInstance(document, response.getOutputStream());

			document.open();

			Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			fontTitle.setStyle(18);

			Paragraph paragraph = new Paragraph("This is title", fontTitle);
			paragraph.setAlignment(paragraph.ALIGN_CENTER);

			Font fontTableHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
			Font fontTableCell = FontFactory.getFont(FontFactory.HELVETICA);

			PdfPTable table = new PdfPTable(10);
			table.addCell(new PdfPCell(new Phrase("ID", fontTableHeader)));
			table.addCell(new PdfPCell(new Phrase("Registration Number", fontTableHeader)));
			table.addCell(new PdfPCell(new Phrase("Owner Name", fontTableHeader)));
			table.addCell(new PdfPCell(new Phrase("Brand", fontTableHeader)));
			table.addCell(new PdfPCell(new Phrase("Registration Expires", fontTableHeader)));
			table.addCell(new PdfPCell(new Phrase("Is Active", fontTableHeader)));
			table.addCell(new PdfPCell(new Phrase("Created By", fontTableHeader)));
			table.addCell(new PdfPCell(new Phrase("Creation Time", fontTableHeader)));
			table.addCell(new PdfPCell(new Phrase("Modified By", fontTableHeader)));
			table.addCell(new PdfPCell(new Phrase("Modified Time", fontTableHeader)));

			List<Vehicle> vehicles = vehicleRepository.findAll();

			for (Vehicle vehicle : vehicles) {
				// Add table rows
				table.addCell(String.valueOf(vehicle.getId()));
				table.addCell(vehicle.getVehicleRegistrationNumber());
				table.addCell(vehicle.getOwnername());
				table.addCell(vehicle.getBrand());
				table.addCell(String.valueOf(vehicle.getRegistrationExpires()));
				table.addCell(String.valueOf(vehicle.isActive()));
				// table.addCell(vehicle.getCreatedby());
				table.addCell(String.valueOf(vehicle.getCreationtime()));
				// table.addCell(vehicle.getModifiedby());
				// table.addCell(String.valueOf(vehicle.getModifiedtime()));
			}

			document.add(paragraph);
			document.add(table);
			// document.add(paragraph2);
			document.close();

		} catch (Exception e) {
			logger.error("pdf not present");

			e.printStackTrace();

		}

	}
	/*
	 * @Override //@Cacheable(cacheNames = "Vehicle") public VehicleDto
	 * getVehiclebyid(long id) { Vehicle
	 * vehicle=vehicleRepository.findById(id).orElse(null);
	 * 
	 * VehicleDto savevehicle=vehicleMapper.mapVehicleToVehicleDto(vehicle);
	 * System.out.println("from data base");
	 * 
	 * return savevehicle; }
	 */
//
//	@Override
//	public UserDetailsService userDetailsService() {
//		UserDetails user = User.builder().username("Haresh").password("abc").roles("Admin").build();
//		return new InMemoryUserDetailsManager(user);
//	}

//	@Override
//	public List<VehicleDto> getAllvehiclelist(VehicleDto vehicleDto) {
//
//		Session s = new Configuration().configure().buildSessionFactory().openSession();
//
//		CriteriaBuilder cb = s.getCriteriaBuilder();
//		CriteriaQuery<VehicleDto> cq = cb.createQuery(VehicleDto.class);
//		Root<VehicleDto> root = cq.from(VehicleDto.class);
//		cq.select(root);
//		
//		Query<VehicleDto> query=s.createQuery(cq);
//		List<VehicleDto> result=query.getResultList();
//		
//		System.out.println(result);
//		
//		return result;
//	}

}
