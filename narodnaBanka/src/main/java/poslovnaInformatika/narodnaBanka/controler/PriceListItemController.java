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

import poslovnaInformatika.narodnaBanka.converters.PricelistItemConverter;
import poslovnaInformatika.narodnaBanka.dto.PriceListItemDTO;
import poslovnaInformatika.narodnaBanka.model.PriceListItem;
import poslovnaInformatika.narodnaBanka.model.Pricelist;
import poslovnaInformatika.narodnaBanka.service.PriceListItemServiceInterface;
import poslovnaInformatika.narodnaBanka.service.PricelistServiceInterface;

@RestController
@RequestMapping(value="salesystem/priceListItems")
public class PriceListItemController {
	
	@Autowired
	private PriceListItemServiceInterface priceListItemServiceInterface;
	
	@Autowired
	PricelistItemConverter pricelistItemConverter;
	
	@Autowired
	private PricelistServiceInterface pricelistService;
	
	@GetMapping
	public ResponseEntity<List<PriceListItemDTO>> getItems(){
		List<PriceListItem> items = priceListItemServiceInterface.findAll();
		List<PriceListItemDTO> itemsDTO = new ArrayList<PriceListItemDTO>();
		for (PriceListItem pi : items) {
			itemsDTO.add(pricelistItemConverter.toDTO(pi));
		}
		return new ResponseEntity<List<PriceListItemDTO>>(itemsDTO, HttpStatus.OK);
	}

	@GetMapping(value="/{price_list_item_id}")
	public ResponseEntity<PriceListItemDTO> getItem(@PathVariable("price_list_item_id") Long price_list_item_id){
		PriceListItem item = priceListItemServiceInterface.findOne(price_list_item_id);
		if(item == null) {
			return new ResponseEntity<PriceListItemDTO>(HttpStatus.NOT_FOUND);
		}
		PriceListItemDTO itemDTO = pricelistItemConverter.toDTO(item);
		return new ResponseEntity<PriceListItemDTO>(itemDTO, HttpStatus.OK);
	}
	
	@PostMapping(value="/add",consumes="application/json")
	public ResponseEntity<PriceListItemDTO> saveItem(@RequestBody PriceListItemDTO piDTO){
		System.out.println(piDTO.getServices().getServices_id());
		PriceListItem p = priceListItemServiceInterface.save(pricelistItemConverter.toPriceListItem(piDTO));
		Pricelist pricelist = pricelistService.findOne(piDTO.getPricelist().getPricelist_id());
		Double newTotalPrice = pricelist.getTotalPrice() + piDTO.getPrice();
		Double unitPrice = newTotalPrice/100;
		Double decount = unitPrice * pricelist.getPercentage();
		Double newPrice = newTotalPrice - decount;
		pricelist.setTotalPrice(newPrice);
		pricelistService.save(pricelist);
		PriceListItemDTO pDTO = pricelistItemConverter.toDTO(p);
		return new ResponseEntity<PriceListItemDTO>(pDTO, HttpStatus.CREATED);
	}
	
	@PutMapping(value="/{price_list_item_id}", consumes="application/json")
	public ResponseEntity<PriceListItemDTO> updateItem(@RequestBody PriceListItemDTO piDTO, @PathVariable("price_list_item_id") Long price_list_item_id){
		PriceListItem item = priceListItemServiceInterface.findOne(price_list_item_id);
		if (item == null) {
			return new ResponseEntity<PriceListItemDTO>(HttpStatus.BAD_REQUEST);
		}				
		PriceListItem p = priceListItemServiceInterface.save(pricelistItemConverter.toPriceListItem(piDTO));
		PriceListItemDTO itemDTO = pricelistItemConverter.toDTO(p);
		return new ResponseEntity<PriceListItemDTO>(itemDTO, HttpStatus.OK);	
	}
	
	@DeleteMapping(value="/{price_list_item_id}")
	public ResponseEntity<Void> deleteItem(@PathVariable("price_list_item_id") Long price_list_item_id){
		PriceListItem item = priceListItemServiceInterface.findOne(price_list_item_id);
		if (item != null){
			priceListItemServiceInterface.remove(price_list_item_id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
}
