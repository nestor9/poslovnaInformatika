package poslovnaInformatika.narodnaBanka.dto;

import java.io.Serializable;
import java.util.Date;

import poslovnaInformatika.narodnaBanka.model.InvoiceItem;

@SuppressWarnings("serial")
public class InvoiceItemDTO implements Serializable{

	private Integer id;

	private  double quantity;

	private  double discount;

	private  double unitPrice;

	private  double pdvBase;

	private  double pdvAmount;

	private  double totalAmount;

	private ServicesDTO service;
	
	private Date date_invoice;
	
	private Date date_currency;
	
	private Integer partner_id;

	private Integer service_id;
	
	private Integer enterprise_id;
	
	private InvoiceDTO invoiceDTO;
	
	
	public InvoiceItemDTO() {
		super();
	}

	public Integer getPartner_id() {
		return partner_id;
	}

	public void setPartner_id(Integer partner_id) {
		this.partner_id = partner_id;
	}

	public InvoiceItemDTO(double quantity, double discount,double unit_price, 
			double pdvBase,double pdvAmount,double total_amount,Integer service_id, Date date_invoice, Date date_currency,Integer partner_id, Integer enterprise_id,
		InvoiceDTO invoiceDTO	) {
		this.quantity=quantity;
		this.discount=discount;
		this.unitPrice=unit_price;
		this.pdvBase=pdvBase;
		this.pdvAmount=pdvAmount;
		this.totalAmount=total_amount;
		this.service_id=service_id;
		this.date_invoice=date_invoice;
		this.date_currency=date_currency;
		this.partner_id=partner_id;
		this.enterprise_id=enterprise_id;
		this.invoiceDTO = invoiceDTO;


	}
	public InvoiceItemDTO(double quantity, double discount,double unit_price,double pdvBase,double pdvAmount,double total_amount,Integer service_id  ) {
		this.quantity=quantity;
		this.discount=discount;
		this.unitPrice=unit_price;
		this.pdvBase=pdvBase;
		this.pdvAmount=pdvAmount;
		this.totalAmount=total_amount;
		this.service_id=service_id;
	}

	
	public InvoiceItemDTO(Integer id, double quantity, double discount,double unit_price, double pdvBase,double pdvAmount,
			double total_amount,ServicesDTO service,InvoiceDTO invoiceDTO  ) {
		super();
		this.id = id;
		this.quantity=quantity;
		this.discount=discount;
		this.unitPrice=unit_price;
		this.pdvBase=pdvBase;
		this.pdvAmount=pdvAmount;
		this.totalAmount=total_amount;
		this.service=service;
		this.invoiceDTO = invoiceDTO;
	}
	
	public InvoiceItemDTO(InvoiceItem u) {
		this(u.getId(),u.getQuantity(), u.getDiscount(), u.getUnitPrice(), u.getPDVBase(), u.getPDVAmount(), u.getTotalAmount(),
				new ServicesDTO(u.getServices()), new InvoiceDTO(u.getInvoice()));
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getPdvBase() {
		return pdvBase;
	}

	public void setPdvBase(double pdvBase) {
		this.pdvBase = pdvBase;
	}

	public double getPdvAmount() {
		return pdvAmount;
	}

	public void setPdvAmount(double pdvAmount) {
		this.pdvAmount = pdvAmount;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public ServicesDTO getService() {
		return service;
	}

	public void setService(ServicesDTO service) {
		this.service = service;
	}

	public Integer getService_id() {
		return service_id;
	}

	public void setService_id(Integer service_id) {
		this.service_id = service_id;
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

	public Integer getEnterprise_id() {
		return enterprise_id;
	}

	public void setEnterprise_id(Integer enterprise_id) {
		this.enterprise_id = enterprise_id;
	}

	public InvoiceDTO getInvoiceDTO() {
		return invoiceDTO;
	}

	public void setInvoiceDTO(InvoiceDTO invoiceDTO) {
		this.invoiceDTO = invoiceDTO;
	}

	
	

}
