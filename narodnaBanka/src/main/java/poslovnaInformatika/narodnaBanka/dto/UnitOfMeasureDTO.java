package poslovnaInformatika.narodnaBanka.dto;

import poslovnaInformatika.narodnaBanka.model.UnitOfMeasure;

public class UnitOfMeasureDTO {

	private Integer id;

	private String name;

	private String  short_name;

	public UnitOfMeasureDTO() {
		super();
	}

	public UnitOfMeasureDTO(String name, String short_name) {
		this.name=name;
		this.short_name=short_name;
	}
	
	public UnitOfMeasureDTO(int id, String name, String short_name) {
		super();
		this.id = id;
		this.name=name;
		this.short_name=short_name;
	}
	public UnitOfMeasureDTO(UnitOfMeasure u) {
		this(u.getId(), u.getName(), u.getShortName());
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

	public String getShort_name() {
		return short_name;
	}

	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}
	
}
