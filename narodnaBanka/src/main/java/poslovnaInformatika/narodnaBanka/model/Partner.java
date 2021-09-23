package poslovnaInformatika.narodnaBanka.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="partners")
public class Partner implements Serializable{
	
	private static final long serialVersionUID = -5652339993553733589L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="partner_id", unique=true, nullable=false)
	private Integer partner_id;
	
	@Column(name="name", unique=false, nullable=false)
	private String name;
	
	@Column(name="address", unique=false, nullable=false)
	private String address;
	
	@Column(name="phone_number", unique=false, nullable=false)
	private String phoneNumber;
	
	@Column(name="fax", unique=false, nullable=false)
	private String fax;
	
	@Column(name="email", unique=false, nullable=false)
	private String email;
	
//	@Column(name="type_of_partner", unique=false, nullable=false )
//	@Enumerated(EnumType.ORDINAL)
//	private TypeOfPartner type_of_partner;
	
	@ManyToOne
	@JoinColumn(name="city_id", referencedColumnName="city_id", nullable=false)
	 private City city;
	
	@ManyToOne
	@JoinColumn(name="enterprise_id", referencedColumnName="enterprise_id", nullable=false)
	 private Enterprise enterprise;
	
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="partner")
	private Set<Invoice> invoices = new HashSet<Invoice>();
	
	public Partner() {
		
	}

	public Integer getPartner_id() {
		return partner_id;
	}

	public void setPartner_id(Integer partner_id) {
		this.partner_id = partner_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//	public TypeOfPartner getType_of_partner() {
//		return type_of_partner;
//	}
//
//	public void setType_of_partner(TypeOfPartner type_of_partner) {
//		this.type_of_partner = type_of_partner;
//	}

	
	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
//    <h1 class="logo"><a href="index.html" alt="Logo"></a>Salesystem NAJ</h1>
	
}
