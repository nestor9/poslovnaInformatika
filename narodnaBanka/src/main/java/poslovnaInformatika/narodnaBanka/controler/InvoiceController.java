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

import poslovnaInformatika.narodnaBanka.converters.InvoiceConverter;
import poslovnaInformatika.narodnaBanka.dto.InvoiceDTO;
import poslovnaInformatika.narodnaBanka.model.Enterprise;
import poslovnaInformatika.narodnaBanka.model.Invoice;
import poslovnaInformatika.narodnaBanka.service.EnterpriseServiceInterface;
import poslovnaInformatika.narodnaBanka.service.InvoiceServiceInterface;
import poslovnaInformatika.narodnaBanka.service.PartnerServiceInteface;
import poslovnaInformatika.narodnaBanka.service.ServicesServiceInterface;

@RestController
@RequestMapping(value="salesystem/invoices")
public class InvoiceController {

	@Autowired
	private InvoiceServiceInterface service;
	@Autowired
	private InvoiceConverter invo;
	
	@Autowired
	private EnterpriseServiceInterface enterpriseService;
	
	@Autowired
	private PartnerServiceInteface partnerService;
	
	@Autowired
	private ServicesServiceInterface serviceInterface;

	
	@GetMapping
	public ResponseEntity<List<InvoiceDTO>> getInvoice() {
	List<Invoice> items = service.findAll();
	List<InvoiceDTO> itemsDTO = new ArrayList<InvoiceDTO>();
	for (Invoice u : items) {
		itemsDTO.add(invo.toDTO(u));
	}
	return new ResponseEntity<List<InvoiceDTO>>(itemsDTO, HttpStatus.OK);

	}
	
	@GetMapping(value="/enterprise/{id}")
	public ResponseEntity<List<InvoiceDTO>> getInvoiceEnterprise(@PathVariable("id") Integer id) {
	Enterprise enterprise = enterpriseService.findOne(new Long(id));
	List<Invoice> items = service.findByEnterprise(enterprise);
	List<InvoiceDTO> itemsDTO = new ArrayList<InvoiceDTO>();
	for (Invoice u : items) {
		itemsDTO.add(invo.toDTO(u));
	}
	return new ResponseEntity<List<InvoiceDTO>>(itemsDTO, HttpStatus.OK);

	}

	@GetMapping(value="/{id}")
	public ResponseEntity<InvoiceDTO> getInvoice(@PathVariable("id") Integer id){
		Invoice item = service.findOne(id);
		if(item == null){
			return new ResponseEntity<InvoiceDTO>(HttpStatus.NOT_FOUND);
		}
		InvoiceDTO itemDTO = invo.toDTO(item);
		return new ResponseEntity<InvoiceDTO>(itemDTO, HttpStatus.OK);
	}
	
	@PostMapping(consumes="application/json")
	public ResponseEntity<InvoiceDTO> saveItem(@RequestBody InvoiceDTO uDTO){
		Invoice item = new Invoice();
		item.setId(uDTO.getId());
		item.setDate_currency(uDTO.getDate_currency());
		item.setDate(uDTO.getDate_invoice());
		item.setNumber(uDTO.getInvoice_number());
		item.setStatus(uDTO.getStatus());
		item.setTotal_amount(uDTO.getTotal_amount());
		item.setTotal_base(uDTO.getTotal_base());
		item.setTotalPdv(uDTO.getTotal_pdv());
		
		//item.setUsluga(uDTO.getUsluga());
		service.save(item);
		return new ResponseEntity<InvoiceDTO>(uDTO, HttpStatus.CREATED);	
	}
	
	@PutMapping(value="/{id}", consumes="application/json")
	public ResponseEntity<InvoiceDTO> updateI(@RequestBody InvoiceDTO uDTO, @PathVariable("id") Integer id){
		System.out.println(id);
		Invoice item = service.findOne(id);
		if (item == null) {
			System.out.println("not found");
			return new ResponseEntity<InvoiceDTO>(HttpStatus.BAD_REQUEST);
		}				
		item.setDate_currency(uDTO.getDate_currency());
		item.setDate(uDTO.getDate_invoice());
		item.setStatus(uDTO.getStatus());
		item.setPartner(partnerService.findOne(uDTO.getPartner_id()));
		//this is calculated
//		item.setTotal_amount(uDTO.getTotal_amount());
//		item.setTotal_base(uDTO.getTotal_base());
//		item.setTotalPdv(uDTO.getTotal_pdv());
		service.save(item);
		InvoiceDTO itemDTO = invo.toDTO(item);
		return new ResponseEntity<InvoiceDTO>(itemDTO, HttpStatus.OK);	
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deleteItem(@PathVariable("id") Integer id){
		Invoice item = service.findOne(id);
		if (item != null){
			service.remove(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
}