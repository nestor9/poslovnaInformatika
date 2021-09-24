package poslovnaInformatika.narodnaBanka.dto;

import java.io.Serializable;

import poslovnaInformatika.narodnaBanka.model.Enterprise;
@SuppressWarnings("serial")
public class EnterpriseDTO implements Serializable {

	
	private Long enterprise_id;
	private String nameEnterprise;
	private String address;
	private String phone;
	private String fax;
	private CityDTO cityDTO;
	/*
	public EnterpriseDTO(Long enterprise_id, String nameEnterprise, String address, String phone, String fax) {
		super();
		this.enterprise_id = enterprise_id;
		this.nameEnterprise = nameEnterprise;
		this.address = address;
		this.phone = phone;
		this.fax = fax;
	}
	public EnterpriseDTO(Enterprise enterprise) {
		this(enterprise.getEnterprise_id(),enterprise.getNameEnterprise(), enterprise.getAddress(),enterprise.getPhone(),enterprise.getFax());
	}
	
	public EnterpriseDTO() {
		
	}
	*/
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
	public CityDTO getCityDTO() {
		return cityDTO;
	}
	public void setCityDTO(CityDTO cityDTO) {
		this.cityDTO = cityDTO;
	}
	
	
	
}


