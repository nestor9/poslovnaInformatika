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

import poslovnaInformatika.narodnaBanka.converters.PDVCategoryConverter;
import poslovnaInformatika.narodnaBanka.dto.PDVCategoryDTO;
import poslovnaInformatika.narodnaBanka.model.PDVCategory;
import poslovnaInformatika.narodnaBanka.service.PDVCategoryServiceInterface;

@RestController
@RequestMapping(value="salesystem/pdvCategories")
public class PDVCategoryController {

	@Autowired
	private PDVCategoryServiceInterface service;
	
	@Autowired
	PDVCategoryConverter pdvCategoryConverter;
	
	@GetMapping(value="/all")
	public ResponseEntity<List<PDVCategoryDTO>> getPDVCategories() {
	List<PDVCategory> units = service.findAll();
	List<PDVCategoryDTO> unitsDTO = new ArrayList<PDVCategoryDTO>();
	for (PDVCategory u : units) {
		unitsDTO.add(pdvCategoryConverter.toDTO(u));
	}
	return new ResponseEntity<List<PDVCategoryDTO>>(unitsDTO, HttpStatus.OK);

	}
	@GetMapping(value="/{id}")
	public ResponseEntity<PDVCategoryDTO> getPDVCategory(@PathVariable("id") Integer id){
		PDVCategory pdv = service.findOne(id);
		if(pdv == null){
			return new ResponseEntity<PDVCategoryDTO>(HttpStatus.NOT_FOUND);
		}
		PDVCategoryDTO pdvtDTO = new PDVCategoryDTO(pdv);
		return new ResponseEntity<PDVCategoryDTO>(pdvtDTO, HttpStatus.OK);
	}
	
	@PostMapping(consumes="application/json", value="/add")
	public ResponseEntity<PDVCategoryDTO> savePDVCategory(@RequestBody PDVCategoryDTO uDTO){
		PDVCategory pdv = pdvCategoryConverter.toPDVCategory(uDTO);
		PDVCategory pr = service.save(pdv);
		PDVCategoryDTO pdvCategoryDTO = pdvCategoryConverter.toDTO(pr);
		return new ResponseEntity<PDVCategoryDTO>(pdvCategoryDTO, HttpStatus.CREATED);	
	}

	@PutMapping(value="/{id}", consumes="application/json")
	public ResponseEntity<PDVCategoryDTO> updatePDVCategory(@RequestBody PDVCategoryDTO uDTO, @PathVariable("id") Integer id){
		PDVCategory pdv = service.findOne(id);
		if (pdv == null) {
			return new ResponseEntity<PDVCategoryDTO>(HttpStatus.BAD_REQUEST);
		}				
		pdv.setName(uDTO.getName());
		service.save(pdv);
		PDVCategoryDTO pdvDTO = new PDVCategoryDTO(pdv);
		return new ResponseEntity<PDVCategoryDTO>(pdvDTO, HttpStatus.OK);	
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deletePDVCategory(@PathVariable("id") Integer id){
		PDVCategory pdv = service.findOne(id);
		if (pdv != null){
			service.remove(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
}
