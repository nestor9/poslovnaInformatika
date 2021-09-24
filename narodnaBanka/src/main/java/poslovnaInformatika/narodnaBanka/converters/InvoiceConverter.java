package poslovnaInformatika.narodnaBanka.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import poslovnaInformatika.narodnaBanka.dto.InvoiceDTO;
import poslovnaInformatika.narodnaBanka.model.Invoice;

@Component
public class InvoiceConverter {
	@Autowired
	public InvoiceConverter invoiceConverter;
	
	
	@Autowired
	EnterpriseConverter enterpriseConverter;
	
	@Autowired
	EnterpriseServiceInterface enterpriseServiceInterface;
	
	public InvoiceDTO toDTO(Invoice invoice) {
		InvoiceDTO dto = new InvoiceDTO();
		dto.setId(invoice.getId());
		dto.setDate_currency(invoice.getDateCurrency());
		dto.setDate_invoice(invoice.getDate());
		dto.setInvoice_number(invoice.getNnumber());
		dto.setStatus(invoice.getStatus());
		dto.setTotal_amount(invoice.getTotal_amount());
		dto.setTotal_base(invoice.getTotal_base());
		dto.setTotal_pdv(invoice.getTotalPdv());
		dto.setEnterpriseDTO(enterpriseConverter.toDTO(invoice.getEnterprise()));
		return dto;
	}
	
	public Invoice toInvoice(InvoiceDTO invoiceDTO) {
		Invoice i = new Invoice();
		
		System.out.println("id" + invoiceDTO.getEnterpriseDTO().getEnterprise_id());
		
		
		i.setId(invoiceDTO.getId());
		i.setDate(invoiceDTO.getDate_invoice());
		i.setDate_currency(invoiceDTO.getDate_currency());
		i.setNumber(invoiceDTO.getInvoice_number());
		i.setStatus(invoiceDTO.getStatus());
		i.setTotal_amount(invoiceDTO.getTotal_amount());
		i.setTotal_base(invoiceDTO.getTotal_base());
		i.setTotalPdv(invoiceDTO.getTotal_base());
		
		i.setEnterprise(enterpriseServiceInterface.findOne(invoiceDTO.getEnterpriseDTO().getEnterprise_id()));
		
		
		
		return i;
	}
}


