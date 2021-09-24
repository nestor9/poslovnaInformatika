package poslovnaInformatika.narodnaBanka.dto;

import java.util.Date;

import poslovnaInformatika.narodnaBanka.model.Invoice;

public class InvoiceDTO {

	private Integer id;
	private Integer invoice_number;
	private Date date_invoice;
	private Date date_currency;
	private Double total_base;
	private Double total_amount;
	private Double total_pdv;
	private String status;
	private EnterpriseDTO enterpriseDTO;
	private BussinesYearDTO bussinesYearDTO;
	private String enterpriseName;
	private Integer partner_id;
	private Integer service_id;

	public InvoiceDTO() {
		
	}
	public InvoiceDTO(Integer id, Integer invoice_number, Date date_invoice, Date date_currency, Double total_base,
			Double total_amount, Double total_pdv, String status, BussinesYearDTO bussinesYearDTO, String enterpriseName,Integer partner_id) {
		super();
		this.id = id;
		this.invoice_number = invoice_number;
		this.date_invoice = date_invoice;
		this.date_currency = date_currency;
		this.total_base = total_base;
		this.total_amount = total_amount;
		this.total_pdv = total_pdv;
		this.status=status;
		this.bussinesYearDTO = bussinesYearDTO;
		this.enterpriseName = enterpriseName;
		this.partner_id=partner_id;
	}
	public InvoiceDTO(Invoice item) {
		this(item.getId(), item.getNumber(), item.getDate(), item.getDateCurrency(), item.getTotal_base(), item.getTotal_amount(),
				item.getTotalPdv(), item.getStatus(),new BussinesYearDTO(item.getBusinessYear()),
				item.getEnterprise().getNameEnterprise(), item.getPartner().getPartner_id());
		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getInvoice_number() {
		return invoice_number;
	}
	public void setInvoice_number(Integer invoice_number) {
		this.invoice_number = invoice_number;
	}
	public Date getDate_invoice() {
		return date_invoice;
	}
	public void setDate_invoice(Date date_invoice) {
		this.date_invoice = date_invoice;
	}
	public Date getDate_currency() {
		return date_currency;
	}
	public void setDate_currency(Date date_currency) {
		this.date_currency = date_currency;
	}
	public Double getTotal_base() {
		return total_base;
	}
	public void setTotal_base(Double total_base) {
		this.total_base = total_base;
	}
	public Double getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(Double total_amount) {
		this.total_amount = total_amount;
	}
	public Double getTotal_pdv() {
		return total_pdv;
	}
	public void setTotal_pdv(Double total_pdv) {
		this.total_pdv = total_pdv;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public EnterpriseDTO getEnterpriseDTO() {
		return enterpriseDTO;
	}
	public void setEnterpriseDTO(EnterpriseDTO enterpriseDTO) {
		this.enterpriseDTO = enterpriseDTO;
	}
	public BussinesYearDTO getBussinesYearDTO() {
		return bussinesYearDTO;
	}
	public void setBussinesYearDTO(BussinesYearDTO bussinesYearDTO) {
		this.bussinesYearDTO = bussinesYearDTO;
	}
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	public Integer getPartner_id() {
		return partner_id;
	}
	public void setPartner_id(Integer partner_id) {
		this.partner_id = partner_id;
	}
	
	
	
}
