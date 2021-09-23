package poslovnaInformatika.narodnaBanka.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="invoiceItems")
public class InvoiceItem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private Integer id;
	
	@Column(name="quantity", unique=false, nullable=false)
	private  double quantity;
	
	@Column(name="discount", unique=false, nullable=false)
	private  double discount;

	@Column(name="unit_price", unique=false, nullable=false)
	private  double unitPrice;

	//pdv stopa veza ili atribut ? 
	
	@Column(name="PDVBase", unique=false, nullable=false)
	private  double PDVBase;

	@Column(name="PDVAmount", unique=false, nullable=false)
	private  double PDVAmount;

	@Column(name="total_amount", unique=false, nullable=false)
	private  double totalAmount;

	@ManyToOne 
	@JoinColumn(name="invoice_id", referencedColumnName="id", nullable=false)
	private Invoice invoice;
	
	@ManyToOne
	@JoinColumn(name="service_id", referencedColumnName="service_id", nullable=false)
	private Services services;

	public InvoiceItem() {
		
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

	public double getPDVBase() {
		return PDVBase;
	}

	public void setPDVBase(double pDVBase) {
		PDVBase = pDVBase;
	}

	public double getPDVAmount() {
		return PDVAmount;
	}

	public void setPDVAmount(double pDVAmount) {
		PDVAmount = pDVAmount;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Services getServices() {
		return services;
	}

	public void setServices(Services services) {
		this.services = services;
	}

	
}
