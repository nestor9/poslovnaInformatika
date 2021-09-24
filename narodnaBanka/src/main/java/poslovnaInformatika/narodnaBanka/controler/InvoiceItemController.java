package poslovnaInformatika.narodnaBanka.controler;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

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

import poslovnaInformatika.narodnaBanka.dto.InvoiceDTO;
import poslovnaInformatika.narodnaBanka.dto.InvoiceItemDTO;
import poslovnaInformatika.narodnaBanka.model.BusinessYear;
import poslovnaInformatika.narodnaBanka.model.Invoice;
import poslovnaInformatika.narodnaBanka.model.InvoiceItem;
import poslovnaInformatika.narodnaBanka.service.BusinessYearServiceInterface;
import poslovnaInformatika.narodnaBanka.service.EnterpriseServiceInterface;
import poslovnaInformatika.narodnaBanka.service.InvoiceItemServiceInterface;
import poslovnaInformatika.narodnaBanka.service.InvoiceServiceInterface;
import poslovnaInformatika.narodnaBanka.service.PartnerServiceInteface;
import poslovnaInformatika.narodnaBanka.service.ServicesServiceInterface;

@RestController
@RequestMapping(value="salesystem/invoiceItems")
public class InvoiceItemController {

	@Autowired
	private InvoiceItemServiceInterface service;
	
	@Autowired
	private ServicesServiceInterface servicesService;
	
	@Autowired
	private InvoiceServiceInterface invoiceService;
	
	@Autowired
	private EnterpriseServiceInterface enterpriseService;
	
	@Autowired
	private PartnerServiceInteface partnerService;
	
	@Autowired
	private BusinessYearServiceInterface buisnessYearService;
	
	@GetMapping(value="/all")
	public ResponseEntity<List<InvoiceItemDTO>> getInvoiceItem() {
		List<InvoiceItem> partners = service.findAll();
		List<InvoiceItemDTO> partnersDTO = new ArrayList<InvoiceItemDTO>();
		for (InvoiceItem u : partners) {
			InvoiceItemDTO newItem = new InvoiceItemDTO(u);
			newItem.setInvoiceDTO(new InvoiceDTO(u.getInvoice()));
			partnersDTO.add(newItem);
		}
		return new ResponseEntity<List<InvoiceItemDTO>>(partnersDTO, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<InvoiceItemDTO>> getInvoiceItems() {
	List<InvoiceItem> items = service.findAll();
	List<InvoiceItemDTO> itemsDTO = new ArrayList<InvoiceItemDTO>();
	for (InvoiceItem u : items) {
		InvoiceItemDTO newItem = new InvoiceItemDTO(u);
		newItem.setInvoiceDTO(new InvoiceDTO(u.getInvoice()));
		itemsDTO.add(newItem);
	}
	return new ResponseEntity<List<InvoiceItemDTO>>(itemsDTO, HttpStatus.OK);

	}
	@GetMapping(value="/{id}")
	public ResponseEntity<InvoiceItemDTO> getInvoiceItem(@PathVariable("id") Integer id){
		InvoiceItem item = service.findOne(id);
		if(item == null){
			return new ResponseEntity<InvoiceItemDTO>(HttpStatus.NOT_FOUND);
		}
		InvoiceItemDTO itemDTO = new InvoiceItemDTO(item);
		return new ResponseEntity<InvoiceItemDTO>(itemDTO, HttpStatus.OK);
	}
	
	@Transactional
	@PostMapping(consumes="application/json")
	public ResponseEntity<Boolean> saveItem(@RequestBody List<InvoiceItemDTO> invoiceItems){
		Invoice invoice = new Invoice();		
		//naci trenutnu poslovnu godinu i izvuci fakturu koja ima najveci broj fakture, dodati +1 i setovati za novu
		//ako nema trenutne poslovne godine(ako je prosao 31.decembar, kreirati novu ovde)
		
		BusinessYear currentYear = buisnessYearService.getCurrentYear(new Date());
		if(currentYear==null) {
			//make new year
			Date date = new Date();
			BusinessYear newYear = new BusinessYear();
			//newYear.setDateFrom();
			//newYear.setDateTo();
			BusinessYear createdYear = buisnessYearService.save(newYear);
			invoice.setBusinessYear(createdYear);
		}else {
			invoice.setBusinessYear(currentYear);
		}
		List<Integer> invoiceIds = new ArrayList<Integer>();
		for(Invoice in :currentYear.getInvoices()) {
			invoiceIds.add(in.getNumber());
		}
		Collections.sort(invoiceIds);
		Integer lastId = invoiceIds.get(invoiceIds.size() - 1);
		invoice.setNumber(lastId+1);
		Double total_amount = 0.0;
		Double total_base = 0.0;
		Double total_pdv = 0.0;
		InvoiceItemDTO firstItem = invoiceItems.get(0);
		invoice.setDate_currency(firstItem.getDate_currency());
		invoice.setDate(firstItem.getDate_invoice());
		invoice.setStatus("poslato"); 
		//status fakture: otkazano, poslato, odbijeno, odobreno, placeno
		invoice.setEnterprise(enterpriseService.findOne((long) firstItem.getEnterprise_id())); //after login function add enterprise
		invoice.setPartner(partnerService.findOne(firstItem.getPartner_id()));
		for(InvoiceItemDTO uDTO : invoiceItems) {
			total_amount += uDTO.getQuantity() * uDTO.getUnitPrice();
			total_base += uDTO.getPdvBase();
			total_pdv +=20.0;
		}
		invoice.setTotal_amount(total_amount); 
		invoice.setTotal_base(total_base);
		invoice.setTotalPdv(total_pdv);
		invoiceService.save(invoice);
	
		for(InvoiceItemDTO uDTO : invoiceItems) {
			InvoiceItem item = new InvoiceItem();
			item.setDiscount(uDTO.getDiscount());
			item.setQuantity(uDTO.getQuantity());
			item.setUnitPrice(uDTO.getUnitPrice());
			item.setPDVBase(uDTO.getUnitPrice() - uDTO.getDiscount());
			item.setPDVAmount(20.0); //opsta stopa?
			item.setTotalAmount(uDTO.getQuantity() * uDTO.getUnitPrice()); 
			item.setServices(servicesService.findOne(uDTO.getService_id()));
			item.setInvoice(invoice); 
			service.save(item);
		}
		return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);	
	}
	
	@PutMapping(value="/{id}", consumes="application/json")
	public ResponseEntity<InvoiceItemDTO> updateItem(@RequestBody InvoiceItemDTO uDTO, @PathVariable("id") Integer id){
		InvoiceItem item = service.findOne(id);
		if (item == null) {
			return new ResponseEntity<InvoiceItemDTO>(HttpStatus.BAD_REQUEST);
		}				
		item.setDiscount(uDTO.getDiscount());
		item.setPDVAmount(uDTO.getPdvAmount());
		item.setPDVBase(uDTO.getPdvBase());
		item.setQuantity(uDTO.getQuantity());
		item.setTotalAmount(uDTO.getTotalAmount());
		item.setUnitPrice(uDTO.getUnitPrice());
		//item.setUsluga(uDTO.getUsluga());
		service.save(item);
		InvoiceItemDTO itemDTO = new InvoiceItemDTO(item);
		return new ResponseEntity<InvoiceItemDTO>(itemDTO, HttpStatus.OK);	
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deleteItem(@PathVariable("id") Integer id){
		InvoiceItem item = service.findOne(id);
		if (item != null){
			service.remove(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	@GetMapping(value = "/reportInvoiceItem/{id}")
	public ResponseEntity getReport(@PathVariable("id") Integer id){
		
		
		return service.report(id);
	}
}