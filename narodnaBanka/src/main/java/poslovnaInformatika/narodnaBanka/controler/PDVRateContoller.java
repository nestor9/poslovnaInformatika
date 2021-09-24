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

import poslovnaInformatika.narodnaBanka.converters.PDVRateConverter;
import poslovnaInformatika.narodnaBanka.dto.PDVRateDTO;
import poslovnaInformatika.narodnaBanka.model.PDVRate;
import poslovnaInformatika.narodnaBanka.service.PDVRateServiceInterface;

@RestController
@RequestMapping(value="salesystem/pdvRates")
public class PDVRateContoller {
	
	@Autowired
	private PDVRateServiceInterface pdvRateServiceInterface;
	
	@Autowired
	PDVRateConverter pdvRateConverter;
	
	@GetMapping(value="/all")
	public ResponseEntity<List<PDVRateDTO>> getRates() {
		List<PDVRate> rates = pdvRateServiceInterface.findAll();
		List<PDVRateDTO> ratesDTO = new ArrayList<PDVRateDTO>();
		for (PDVRate r : rates) {
			ratesDTO.add(pdvRateConverter.toDTO(r));
		}
		return new ResponseEntity<List<PDVRateDTO>>(ratesDTO, HttpStatus.OK);
		
	}
	
	@GetMapping(value="/{pdv_rate_id}")
	public ResponseEntity<PDVRateDTO> getRate(@PathVariable("pdv_rate_id") Integer pdv_rate_id){
		PDVRate rate = pdvRateServiceInterface.findOne(pdv_rate_id);
		if(rate == null) {
			return new ResponseEntity<PDVRateDTO>(HttpStatus.NOT_FOUND);
		}
		PDVRateDTO rateDTO = pdvRateConverter.toDTO(rate);
		return new ResponseEntity<PDVRateDTO>(rateDTO, HttpStatus.OK);
	}
	
	@PostMapping(consumes="application/json", value="/add")
	public ResponseEntity<PDVRateDTO> saveRate(@RequestBody PDVRateDTO rDTO){
		PDVRate r = pdvRateConverter.toPdvRate(rDTO);
		PDVRate pr = pdvRateServiceInterface.save(r);
		PDVRateDTO pdvRateDTO = pdvRateConverter.toDTO(pr);
		return new ResponseEntity<PDVRateDTO>(pdvRateDTO , HttpStatus.CREATED);
	}

	@PutMapping(value="/{pdv_rate_id}", consumes="application/json")
	public ResponseEntity<PDVRateDTO> updateRate(@RequestBody PDVRateDTO rDTO, @PathVariable("pdv_rate_id") Integer pdv_rate_id){
		PDVRate rate = pdvRateServiceInterface.findOne(pdv_rate_id);
		if (rate == null) {
			return new ResponseEntity<PDVRateDTO>(HttpStatus.BAD_REQUEST);
		}				
		PDVRate r = pdvRateServiceInterface.save(pdvRateConverter.toPdvRate(rDTO));
		PDVRateDTO pdvRateDTO = pdvRateConverter.toDTO(r);
		return new ResponseEntity<PDVRateDTO>(pdvRateDTO, HttpStatus.OK);	
	}
	
	@DeleteMapping(value="/{pdv_rate_id}")
	public ResponseEntity<Void> deleteRate(@PathVariable("pdv_rate_id") Integer pdv_rate_id){
		PDVRate rate = pdvRateServiceInterface.findOne(pdv_rate_id);
		if (rate != null){
			pdvRateServiceInterface.remove(pdv_rate_id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
}
