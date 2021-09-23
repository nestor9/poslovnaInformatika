package poslovnaInformatika.narodnaBanka.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="invoices")
public class Invoice {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private Integer id;
	
	@Column(name="number", unique=false, nullable=false)
	private  Integer number;
	
	@Column(name="date_invoice", unique=false, nullable=false)
	private  Date date;
	
	@Column(name="date_currency", unique=false, nullable=false)
	private Date  dateCurrency;
	
	@Column(name="total_base", unique=false, nullable=false)
	private Double  total_base;
	
	@Column(name="total_pdv", unique=false, nullable=false)
	private Double  totalPdv;
	
	@Column(name="total_amount", unique=false, nullable=false)
	private Double  total_amount;
	
	@Column(name="status", unique=false, nullable=false)
	private String  status;
	
	@ManyToOne
	@JoinColumn(name="enterprise_id", referencedColumnName="enterprise_id", nullable=false)
	 private Enterprise enterprise;
	
	@ManyToOne
	@JoinColumn(name="partner_id", referencedColumnName="partner_id", nullable=false)
	 private Partner partner;
	
	@ManyToOne
	@JoinColumn(name="idYear", referencedColumnName = "idYear", nullable = false)
	private BusinessYear businessYear;

	
	
public Invoice() {
	
}

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public Integer getNnumber() {
	return number;
}

public void setNumber(Integer number) {
	this.number = number;
}

public Date getDate() {
	return date;
}

public void setDate(Date date) {
	this.date = date;
}

public Date getDateCurrency() {
	return dateCurrency;
}

public void setDate_currency(Date dateCurrency) {
	this.dateCurrency = dateCurrency;
}

public Double getTotal_base() {
	return total_base;
}

public void setTotal_base(Double total_base) {
	this.total_base = total_base;
}

public Double getTotalPdv() {
	return totalPdv;
}

public void setTotalPdv(Double totalPdv) {
	this.totalPdv = totalPdv;
}

public Double getTotal_amount() {
	return total_amount;
}

public void setTotal_amount(Double total_amount) {
	this.total_amount = total_amount;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public Enterprise getEnterprise() {
	return enterprise;
}

public void setEnterprise(Enterprise enterprise) {
	this.enterprise = enterprise;
}

public Partner getPartner() {
	return partner;
}

public void setPartner(Partner partner) {
	this.partner = partner;
}

public BusinessYear getBusinessYear() {
	return businessYear;
}

public void setBusinessYear(BusinessYear businessYear) {
	this.businessYear = businessYear;
}

public Integer getNumber() {
	return number;
}

public void setDateCurrency(Date dateCurrency) {
	this.dateCurrency = dateCurrency;
}



}
