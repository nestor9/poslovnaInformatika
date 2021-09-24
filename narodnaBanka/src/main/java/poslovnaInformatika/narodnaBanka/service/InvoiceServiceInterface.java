package poslovnaInformatika.narodnaBanka.service;

import java.util.List;

import poslovnaInformatika.narodnaBanka.model.Enterprise;
import poslovnaInformatika.narodnaBanka.model.Invoice;

public interface InvoiceServiceInterface {
	
	Invoice findOne(Integer invoice_id);
	
	Invoice save(Invoice invoice);
	
	Invoice remove(Integer invoice_id);
	
	List<Invoice> findAll();
	
	List<Invoice> findByEnterprise(Enterprise enterprise);
}
