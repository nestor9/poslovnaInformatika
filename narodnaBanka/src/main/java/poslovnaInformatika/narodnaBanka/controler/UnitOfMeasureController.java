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

import poslovnaInformatika.narodnaBanka.dto.UnitOfMeasureDTO;
import poslovnaInformatika.narodnaBanka.model.UnitOfMeasure;
import poslovnaInformatika.narodnaBanka.service.UnitOfMeasureServiceInterface;

@RestController
@RequestMapping(value="salesystem/unitOfMeasures")
public class UnitOfMeasureController {
	
	@Autowired
	private UnitOfMeasureServiceInterface service;
	
	@GetMapping
	public ResponseEntity<List<UnitOfMeasureDTO>> getUnits() {
	List<UnitOfMeasure> units = service.findAll();
	List<UnitOfMeasureDTO> unitsDTO = new ArrayList<UnitOfMeasureDTO>();
	for (UnitOfMeasure u : units) {
		unitsDTO.add(new UnitOfMeasureDTO(u));
		}
	return new ResponseEntity<List<UnitOfMeasureDTO>>(unitsDTO, HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<UnitOfMeasureDTO> getUnit(@PathVariable("id") Integer id){
		UnitOfMeasure unit = service.findOne(id);
		if(unit == null){
			return new ResponseEntity<UnitOfMeasureDTO>(HttpStatus.NOT_FOUND);
		}
		UnitOfMeasureDTO unitDTO = new UnitOfMeasureDTO(unit);
		return new ResponseEntity<UnitOfMeasureDTO>(unitDTO, HttpStatus.OK);
	}
	
	@PostMapping(consumes="application/json")
	public ResponseEntity<UnitOfMeasureDTO> saveUnit(@RequestBody UnitOfMeasureDTO uDTO){
		UnitOfMeasure unit = new UnitOfMeasure();
		unit.setName(uDTO.getName());
		unit.setShortName(uDTO.getShort_name());
		service.save(unit);
		return new ResponseEntity<UnitOfMeasureDTO>(uDTO, HttpStatus.CREATED);	
	}
	
	@PutMapping(value="/{id}", consumes="application/json")
	public ResponseEntity<UnitOfMeasureDTO> updateUnit(@RequestBody UnitOfMeasureDTO uDTO, @PathVariable("id") Integer id){
		UnitOfMeasure unit = service.findOne(id);
		if (unit == null) {
			return new ResponseEntity<UnitOfMeasureDTO>(HttpStatus.BAD_REQUEST);
		}				
		unit.setName(uDTO.getName());
		unit.setShortName(uDTO.getShort_name());
		service.save(unit);
		UnitOfMeasureDTO unitDTO = new UnitOfMeasureDTO(unit);
		return new ResponseEntity<UnitOfMeasureDTO>(unitDTO, HttpStatus.OK);	
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deleteUnit(@PathVariable("id") Integer id){
		UnitOfMeasure unit = service.findOne(id);
		if (unit != null){
			service.remove(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	

}
