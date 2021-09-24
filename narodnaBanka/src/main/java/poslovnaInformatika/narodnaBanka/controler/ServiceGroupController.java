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

import poslovnaInformatika.narodnaBanka.converters.ServiceGroupConverter;
import poslovnaInformatika.narodnaBanka.dto.ServiceGroupDTO;
import poslovnaInformatika.narodnaBanka.model.ServiceGroup;
import poslovnaInformatika.narodnaBanka.service.EnterpriseServiceInterface;
import poslovnaInformatika.narodnaBanka.service.PDVCategoryServiceInterface;
import poslovnaInformatika.narodnaBanka.service.ServiceGroupServiceInterface;

@RestController
@RequestMapping(value="salesystem/serviceGroups")
public class ServiceGroupController {

	@Autowired
	private ServiceGroupServiceInterface service;
	
	@Autowired 
	private ServiceGroupConverter converter;
	
	@Autowired
	private PDVCategoryServiceInterface pdvCategoryService;
	
	@Autowired
	private EnterpriseServiceInterface enterpriseService;
	
	@GetMapping
	public ResponseEntity<List<ServiceGroupDTO>> getGroups() {
	List<ServiceGroup> groups = service.findAll();
	List<ServiceGroupDTO> groupsDTO = new ArrayList<ServiceGroupDTO>();
	for (ServiceGroup u : groups) {
		groupsDTO.add(converter.toDTO(u));
	}
	return new ResponseEntity<List<ServiceGroupDTO>>(groupsDTO, HttpStatus.OK);

	}
	@GetMapping(value="/{id}")
	public ResponseEntity<ServiceGroupDTO> getGroup(@PathVariable("id") Integer id){
		ServiceGroup group = service.findOne(id);
		if(group == null){
			return new ResponseEntity<ServiceGroupDTO>(HttpStatus.NOT_FOUND);
		}
		//{group}
		ServiceGroupDTO groupDTO = new ServiceGroupDTO();
		return new ResponseEntity<ServiceGroupDTO>(groupDTO, HttpStatus.OK);
	}
	
	@PostMapping(consumes="application/json")
	public ResponseEntity<ServiceGroupDTO> saveGroup(@RequestBody ServiceGroupDTO uDTO){
		ServiceGroup group = new ServiceGroup();
		group.setName(uDTO.getName());
		//System.out.println(uDTO.getPDVCategory().getId());
		group.setEnterprise(enterpriseService.findOne(uDTO.getFirm().getEnterprise_id()));
		group.setPDVCategory(pdvCategoryService.findOne(uDTO.getPDVCategory().getId()));
		service.save(group);
		return new ResponseEntity<ServiceGroupDTO>(uDTO, HttpStatus.CREATED);	
	}
	
	@PutMapping(value="/{id}", consumes="application/json")
	public ResponseEntity<ServiceGroupDTO> updateGroup(@RequestBody ServiceGroupDTO uDTO, @PathVariable("id") Integer id){
		ServiceGroup group = service.findOne(id);
		if (group == null) {
			return new ResponseEntity<ServiceGroupDTO>(HttpStatus.BAD_REQUEST);
		}				
		group.setName(uDTO.getName());
		group.setPDVCategory(pdvCategoryService.findOne(uDTO.getPDVCategory().getId()));
		service.save(group);
		
		ServiceGroupDTO pdvDTO = new ServiceGroupDTO();
		return new ResponseEntity<ServiceGroupDTO>(pdvDTO, HttpStatus.OK);	
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deleteGroup(@PathVariable("id") Integer id){
		ServiceGroup group = service.findOne(id);
		if (group != null){
			service.remove(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
}
