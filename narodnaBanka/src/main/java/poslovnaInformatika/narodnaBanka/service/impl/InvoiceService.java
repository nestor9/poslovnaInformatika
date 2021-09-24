package poslovnaInformatika.narodnaBanka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poslovnaInformatika.narodnaBanka.model.Enterprise;
import poslovnaInformatika.narodnaBanka.model.Invoice;
import poslovnaInformatika.narodnaBanka.repository.InvoiceRepository;
import poslovnaInformatika.narodnaBanka.service.InvoiceServiceInterface;

@Service
public class InvoiceService implements InvoiceServiceInterface{

	@Autowired
	InvoiceRepository invoiceRepository;
	
	@Override
	public Invoice findOne(Integer invoice_id) {
		return invoiceRepository.getOne(invoice_id);
	}

	@Override
	public Invoice save(Invoice invoice) {
		return invoiceRepository.save(invoice);
	}

	@Override
	public List<Invoice> findAll() {
		return invoiceRepository.findAll();
	}

	@Override
	public Invoice remove(Integer invoice_id) {
		 invoiceRepository.deleteById(invoice_id);
		 return new Invoice();
	}

	@Override
	public List<Invoice> findByEnterprise(Enterprise enterprise) {
		return invoiceRepository.findByEnterprise(enterprise);
	}

}
