package poslovnaInformatika.narodnaBanka.dto;

import java.io.Serializable;

import poslovnaInformatika.narodnaBanka.model.PDVCategory;

@SuppressWarnings("serial")
public class PDVCategoryDTO implements Serializable{

	private Integer id;

	private String  name;

	public PDVCategoryDTO() {
		super();
	}

	public PDVCategoryDTO(String name) {
		this.name=name;
	}
	
	public PDVCategoryDTO(int id, String name) {
		super();
		this.id = id;
		this.name=name;
	}
	public PDVCategoryDTO(PDVCategory u) {
		this(u.getId(), u.getName());
	}

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
	

}
