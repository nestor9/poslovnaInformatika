package poslovnaInformatika.narodnaBanka.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PartnerDTO implements Serializable{
	
	private Integer partner_id;
	
	private String partner_name;
	
	private String address;
	
	private String phone_number;
	
	private String fax;
	
	private String email;
	
	private CityDTO cityDTO;
	
	private EnterpriseDTO enterpriseDTO;
	
	public PartnerDTO(){
		super();
	}
	
//	public PartnerDTO(String partner_name) {
//		this.partner_name=partner_name;
//	}
//	
//	public PartnerDTO(int partner_id, String partner_name, String address, String phone_number, String fax, String email) {
//		super();
//		this.partner_id = partner_id;
//		this.partner_name = partner_name;
//		this.address = address;
//		this.phone_number = phone_number;
//		this.fax = fax;
//		this.email = email;
//	}
//	
//	public PartnerDTO(Partner p) {
//		this(p.getPartner_id(), p.getName(), p.getAddress(), p.getPhoneNumber(), p.getFax(), p.getEmail());
//	}

	public Integer getPartner_id() {
		return partner_id;
	}

	public void setPartner_id(Integer partner_id) {
		this.partner_id = partner_id;
	}

	public String getPartner_name() {
		return partner_name;
	}

	public void setPartner_name(String partner_name) {
		this.partner_name = partner_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
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

	public CityDTO getCityDTO() {
		return cityDTO;
	}

	public void setCityDTO(CityDTO cityDTO) {
		this.cityDTO = cityDTO;
	}

	public EnterpriseDTO getEnterpriseDTO() {
		return enterpriseDTO;
	}

	public void setEnterpriseDTO(EnterpriseDTO enterpriseDTO) {
		this.enterpriseDTO = enterpriseDTO;
	}
	
	
	
}
