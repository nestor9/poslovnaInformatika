package poslovnaInformatika.narodnaBanka.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ServiceGroupDTO implements Serializable{

	private Integer id;

	private String  name;

	private PDVCategoryDTO PDVCategory;

	private EnterpriseDTO firm;

	public ServiceGroupDTO() {
		super();
	}

/*	public ServiceGroupDTO(String name, PDVCategoryDTO PDVCategory, EnterpriseDTO firm) {
		this.name=name;
		this.PDVCategory=PDVCategory;
		this.firm=firm;
	}
	
	public ServiceGroupDTO(int id, String name, PDVCategoryDTO PDVCategory, EnterpriseDTO firm) {
		super();
		this.id = id;
		this.name=name;
		this.PDVCategory=PDVCategory;
		this.firm=firm;
	}
	
	public ServiceGroupDTO(ServiceGroup s) {
		this(s.getId(), s.getName(), new PDVCategoryDTO(s.getPDVCategory()), new EnterpriseDTO(s.getEnterprise()));
	}

	*/
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PDVCategoryDTO getPDVCategory() {
		return PDVCategory;
	}

	public void setPDVCategory(PDVCategoryDTO pDVCategory) {
		PDVCategory = pDVCategory;
	}

	public EnterpriseDTO getFirm() {
		return firm;
	}

	public void setFirm(EnterpriseDTO firm) {
		this.firm = firm;
	}




	
	

}

