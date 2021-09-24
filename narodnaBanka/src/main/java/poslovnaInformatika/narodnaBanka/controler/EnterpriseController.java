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

import poslovnaInformatika.narodnaBanka.converters.EnterpriseConverter;
import poslovnaInformatika.narodnaBanka.dto.EnterpriseDTO;
import poslovnaInformatika.narodnaBanka.model.Enterprise;
import poslovnaInformatika.narodnaBanka.service.EnterpriseServiceInterface;

@RestController
@RequestMapping(value="salesystem/enterprises")
public class EnterpriseController {
	
	@Autowired 
	private EnterpriseServiceInterface ent;
	
	@Autowired
	EnterpriseConverter enterpriseConverter;
		
	@GetMapping(value="/all")
	public ResponseEntity<List<EnterpriseDTO>> getEnterprise() {
		List<Enterprise> enterprises = ent.findAll();
		List<EnterpriseDTO> entDTO = new ArrayList<EnterpriseDTO>();
		for (Enterprise e : enterprises) {
			entDTO.add(enterpriseConverter.toDTO(e));
		}
		return new ResponseEntity<List<EnterpriseDTO>>(entDTO, HttpStatus.OK);
	}
	
	@GetMapping(value="/{enterprise_id}")
	public ResponseEntity<EnterpriseDTO> getEnterprise(@PathVariable("enterprise_id") Long enterprise_id){
		Enterprise enterprise = ent.findOne(enterprise_id);
		if(enterprise == null) {
			return new ResponseEntity<EnterpriseDTO>(HttpStatus.NOT_FOUND);
		}
		EnterpriseDTO entDTO = enterpriseConverter.toDTO(enterprise);
		return new ResponseEntity<EnterpriseDTO>(entDTO, HttpStatus.OK);
	}
	
	@PostMapping(consumes = "application/json", value="/add")
	public ResponseEntity<EnterpriseDTO> saveEnterprise(@RequestBody EnterpriseDTO eDTO){
		Enterprise e = ent.save(enterpriseConverter.toEnterprise(eDTO));
		EnterpriseDTO enterpriseDTO = enterpriseConverter.toDTO(e);
		return new ResponseEntity<EnterpriseDTO>(enterpriseDTO, HttpStatus.CREATED);
	}
	
	@PutMapping(value="/{enterprise_id}", consumes="appliction/json")
	public ResponseEntity<EnterpriseDTO> updateEnterprise(@RequestBody EnterpriseDTO eDTO, @PathVariable("enterprise_id") Long enterprise_id ){
		Enterprise enterprise = ent.findOne(enterprise_id);
		if (enterprise == null) {
			return new ResponseEntity<EnterpriseDTO>(HttpStatus.BAD_REQUEST);
		}
		Enterprise e = ent.save(enterpriseConverter.toEnterprise(eDTO));
		EnterpriseDTO entDTO = enterpriseConverter.toDTO(e);
		return new ResponseEntity<EnterpriseDTO>(entDTO, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{enterprise_id}")
	public ResponseEntity<Void> deletePartner(@PathVariable("enterprise_id") Long enterprise_id){
		Enterprise enterprise = ent.findOne(enterprise_id);
		if (enterprise != null) {
			ent.remove(enterprise_id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

}
