package poslovnaInformatika.narodnaBanka.model;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="enterprises")
public class Enterprise implements Serializable {

	private static final long serialVersionUID = -7823756286553586902L;

	@Id
	@GeneratedValue(strategy=IDENTITY)
	@Column(name="enterprise_id", unique=true, nullable=false)
	private Long enterprise_id;
	
	@Column(name="name_enterprise",unique=false,nullable=false)
	private String nameEnterprise;
	
	@Column(name="address", unique=false, nullable=false)
	private String address;
	
	@Column(name="phone", unique=false, nullable=false)
	private String phone;
	
	@Column(name="fax", unique=false, nullable=false)
	private String fax;
	
	@ManyToOne
	@JoinColumn(name="city_id", referencedColumnName="city_id", nullable=false)
	 private City city;
	
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="enterprise")
	private Set<User> users = new HashSet<User>();

	
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="enterprise")
	private Set<ServiceGroup> groups = new HashSet<ServiceGroup>();
	
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="enterprise")
	private Set<Partner> partners = new HashSet<Partner>();
	
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="enterprise")
	private Set<Invoice> invoices = new HashSet<Invoice>();
	
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="enterprise")
	private Set<Pricelist> pricelists = new HashSet<Pricelist>();

	public Enterprise(Long enterprise_id, String nameEnterprise, String address, String phone, String fax, City city) {
		super();
		this.enterprise_id = enterprise_id;
		this.nameEnterprise = nameEnterprise;
		this.address = address;
		this.phone = phone;
		this.fax = fax;
		this.city = city;
	}

	public Enterprise() {
		// TODO Auto-generated constructor stub
	}

	public Long getEnterprise_id() {
		return enterprise_id;
	}

	public void setEnterprise_id(Long enterprise_id) {
		this.enterprise_id = enterprise_id;
	}

	public String getNameEnterprise() {
		return nameEnterprise;
	}

	public void setNameEnterprise(String nameEnterprise) {
		this.nameEnterprise = nameEnterprise;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	

	public Set<Pricelist> getPricelists() {
		return pricelists;
	}

	public void setPricelists(Set<Pricelist> pricelists) {
		this.pricelists = pricelists;
	}

	public Set<ServiceGroup> getGroups() {
		return groups;
	}

	public void setGroups(Set<ServiceGroup> groups) {
		this.groups = groups;
	}

	public Set<Partner> getPartners() {
		return partners;
	}

	public void setPartners(Set<Partner> partners) {
		this.partners = partners;
	}

	public Set<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(Set<Invoice> invoices) {
		this.invoices = invoices;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
	
}
