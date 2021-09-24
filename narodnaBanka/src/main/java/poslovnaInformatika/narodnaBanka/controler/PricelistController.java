package poslovnaInformatika.narodnaBanka.controler;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import poslovnaInformatika.narodnaBanka.converters.PricelistConverter;
import poslovnaInformatika.narodnaBanka.dto.PricelistDTO;
import poslovnaInformatika.narodnaBanka.model.PriceListItem;
import poslovnaInformatika.narodnaBanka.model.Pricelist;
import poslovnaInformatika.narodnaBanka.service.PriceListItemServiceInterface;
import poslovnaInformatika.narodnaBanka.service.PricelistServiceInterface;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("salesystem/pricelists")
public class PricelistController {
	
	@Autowired 
	private PricelistServiceInterface pricelistService;
	
	@Autowired
	PricelistConverter pricelistConverter;
	
	@Autowired
	private PriceListItemServiceInterface pricelistItemService;
	
	@GetMapping(value="/all")
	public ResponseEntity<List<PricelistDTO>> getPricelist() {
	List<Pricelist> pricelists = pricelistService.findAll();
	List<PricelistDTO> pricelistDTO = new ArrayList<PricelistDTO>();
	for (Pricelist p : pricelists) {
		pricelistDTO.add(pricelistConverter.toDTO(p));
	}
	return new ResponseEntity<List<PricelistDTO>>(pricelistDTO, HttpStatus.OK);
	}
	
	@GetMapping(value="/{pricelist_id}")
	public ResponseEntity<PricelistDTO> getPricelist(@PathVariable("pricelist_id") Long pricelist_id){
		Pricelist pricelist = pricelistService.findOne(pricelist_id);
		if(pricelist == null){
			return new ResponseEntity<PricelistDTO>(HttpStatus.NOT_FOUND);
		}
		PricelistDTO pricelistDTO = pricelistConverter.toDTO(pricelist);
		return new ResponseEntity<PricelistDTO>(pricelistDTO,HttpStatus.OK);
	}

	@PostMapping(value="/add", consumes = "application/json")
	public ResponseEntity<PricelistDTO> save(@RequestBody PricelistDTO pDTO){
		Pricelist pr = pricelistConverter.toPricelist(pDTO);
		pr.setTotalPrice(0.0);
		Pricelist p = pricelistService.save(pr);
		PricelistDTO pricelistDTO = pricelistConverter.toDTO(p);
		return new ResponseEntity<PricelistDTO>(pricelistDTO, HttpStatus.CREATED);
	}
	
	@Transactional
	@PostMapping(value="/copy/{pricelist_id}/{percentage}", consumes = "application/json")
	public ResponseEntity<PricelistDTO> copy(@PathVariable("percentage") Integer percentage, @PathVariable("pricelist_id") Long pricelist_id){
		Pricelist pricelist = pricelistService.findOne(pricelist_id);
		Double unitPrice = pricelist.getTotalPrice()/100;
		Double decount = unitPrice * percentage;
		Double newTotalPrice = pricelist.getTotalPrice() - decount;
		List<PriceListItem> items = pricelistItemService.findByPricelist(pricelist);
		Pricelist copyPricelist = new Pricelist();
		copyPricelist.setDateFrom(pricelist.getDateFrom());
		copyPricelist.setEnterprise(pricelist.getEnterprise());
		copyPricelist.setTotalPrice(newTotalPrice);
		copyPricelist.setPercentage(percentage);
		Pricelist savedPricelist = pricelistService.save(copyPricelist);
		for(PriceListItem item : items) {
			PriceListItem copyItem = new PriceListItem();
			copyItem.setPrice(item.getPrice());
			copyItem.setPricelist(savedPricelist);
			copyItem.setServices(item.getServices());
			pricelistItemService.save(copyItem);
		}
		return new ResponseEntity<PricelistDTO>(pricelistConverter.toDTO(savedPricelist), HttpStatus.CREATED);
	}
	

	@PutMapping(value="/{id}", consumes="application/json")
	public ResponseEntity<PricelistDTO> updatePricelist(@RequestBody PricelistDTO pDTO, @PathVariable("id") Long id){
		Pricelist pricelist = pricelistService.findOne(id);
		if (pricelist == null) {
			return new ResponseEntity<PricelistDTO>(HttpStatus.NOT_FOUND);
		}				
		Pricelist p = pricelistService.save(pricelistConverter.toPricelist(pDTO));
		PricelistDTO pricelistDTO = pricelistConverter.toDTO(p);
		return new ResponseEntity<PricelistDTO>(pricelistDTO, HttpStatus.CREATED);	
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deletePricelist(@PathVariable("id") Long id){
		Pricelist pricelist = pricelistService.findOne(id);
		if (pricelist != null){
			pricelistService.remove(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
}