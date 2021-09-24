package poslovnaInformatika.narodnaBanka.controler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poslovnaInformatika.narodnaBanka.converters.ServicesConverter;
import poslovnaInformatika.narodnaBanka.dto.ServicesDTO;
import poslovnaInformatika.narodnaBanka.model.Services;
import poslovnaInformatika.narodnaBanka.service.ServiceGroupServiceInterface;
import poslovnaInformatika.narodnaBanka.service.ServicesServiceInterface;
import poslovnaInformatika.narodnaBanka.service.UnitOfMeasureServiceInterface;

@RestController
@RequestMapping(value="salesystem/services")
public class ServicesController {
	
	@Autowired
	private ServicesServiceInterface serviceServiceInterface;
	
	@Autowired
	ServicesConverter servicesConverter;
	
	@Autowired
	private ServiceGroupServiceInterface groupService;
	
	@Autowired
	private UnitOfMeasureServiceInterface unitService;
	
//	http://localhost:8080/salesystem/services/all
	
	@GetMapping
	public ResponseEntity<List<ServicesDTO>> getServices(){
		List<Services> services = serviceServiceInterface.findAll();
		List<ServicesDTO> servicesDTO = new ArrayList<ServicesDTO>();
		for(Services s : services) {
			servicesDTO.add(servicesConverter.toDTO(s));
	}
		return new ResponseEntity<List<ServicesDTO>>(servicesDTO, HttpStatus.OK);

}
	
	@GetMapping(value="/all")
	public ResponseEntity<List<ServicesDTO>> getServicesAll(){
		List<Services> services = serviceServiceInterface.findAll();
		List<ServicesDTO> servicesDTO = new ArrayList<ServicesDTO>();
		for(Services s : services) {
			servicesDTO.add(servicesConverter.toDTO(s));
	}
		return new ResponseEntity<List<ServicesDTO>>(servicesDTO, HttpStatus.OK);

	}
	
	@GetMapping(value="/{services_id}")
	public ResponseEntity<ServicesDTO> getService(@PathVariable("services_id") Integer services_id){
		Services services = serviceServiceInterface.findOne(services_id);
		if(services == null) {
			return new ResponseEntity<ServicesDTO>(HttpStatus.NOT_FOUND);
		}
	ServicesDTO servicesDTO = new ServicesDTO(services);
		return new ResponseEntity<ServicesDTO>(servicesDTO, HttpStatus.OK);
	}
	
	@PostMapping(consumes="application/json")
	public ResponseEntity<ServicesDTO> saveServices(@RequestBody ServicesDTO siDTO){
		Services services = new Services();
		services.setDescription(siDTO.getDescription());
		services.setGoods(siDTO.getGoods());
		services.setName(siDTO.getName());
		services.setServiceGroup(groupService.findOne(siDTO.getServiceGroup().getId()));
       services.setUnit(unitService.findOne(siDTO.getUnitOfMeasure().getId()));
		serviceServiceInterface.save(services);
		return new ResponseEntity<ServicesDTO>(siDTO, HttpStatus.CREATED);
	}
	
	@PutMapping(value="/{services_id}", consumes="application/json")
	public ResponseEntity<ServicesDTO> updateService(@RequestBody ServicesDTO siDTO, @PathVariable("services_id") Integer services_id){
		Services services = serviceServiceInterface.findOne(services_id);
		if (services == null) {
			return new ResponseEntity<ServicesDTO>(HttpStatus.BAD_REQUEST);
		}
		services.setDescription(siDTO.getDescription());
		services.setGoods(siDTO.getGoods());
		services.setName(siDTO.getName());
		services.setServiceGroup(groupService.findOne(siDTO.getServiceGroup().getId()));
       services.setUnit(unitService.findOne(siDTO.getUnitOfMeasure().getId()));
		serviceServiceInterface.save(services);
		ServicesDTO serviceDTO = new ServicesDTO(services);
		return new ResponseEntity<ServicesDTO>(serviceDTO, HttpStatus.OK);	
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deleteItem(@PathVariable("id") Integer id){
		Services services = serviceServiceInterface.findOne(id);
		if (services != null){
			serviceServiceInterface.remove(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
}
