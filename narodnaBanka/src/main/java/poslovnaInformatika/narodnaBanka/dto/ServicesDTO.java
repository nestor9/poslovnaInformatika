package poslovnaInformatika.narodnaBanka.dto;

import java.io.Serializable;

import poslovnaInformatika.narodnaBanka.model.ServiceGroup;
import poslovnaInformatika.narodnaBanka.model.Services;
import poslovnaInformatika.narodnaBanka.model.UnitOfMeasure;

public class ServicesDTO implements Serializable {

	private static final long serialVersionUID = -8939650811392798052L;
	
	private int services_id;
	
	private String name;
	
	private String description;
	
	private Boolean goods;
	
	private ServiceGroupDTO serviceGroup;
	
	private UnitOfMeasureDTO unitOfMeasure;
	
	public ServicesDTO() {
		
	}
	
	public ServicesDTO(int services_id, String name, String description, Boolean goods, ServiceGroupDTO serviceGroup, UnitOfMeasureDTO unitOfMeasure) {
		super();
		this.services_id = services_id;
		this.name = name;
		this.description = description;
		this.goods = goods;
		this.serviceGroup = serviceGroup;
		this.unitOfMeasure = unitOfMeasure;
	}
	public ServicesDTO(Services s) {
		this(s.getService_id(), s.getName(), s.getDescription(), s.getGoods(), s.getServiceGroup(), s.getUnitOfMeasure());
	}
	public ServicesDTO(Long service_id, String name2, String description2, Boolean goods2, ServiceGroup serviceGroup2,
			UnitOfMeasure unitOfMeasure2) {
		// TODO Auto-generated constructor stub
	}
	public ServicesDTO(Integer service_id, String name2, String description2, Boolean goods2,
			ServiceGroup serviceGroup2, UnitOfMeasure unitOfMeasure2) {
		// TODO Auto-generated constructor stub
	}

	public int getServices_id() {
		return services_id;
	}
	public void setServices_id(int services_id) {
		this.services_id = services_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getGoods() {
		return goods;
	}
	public void setGoods(Boolean goods) {
		this.goods = goods;
	}
	public ServiceGroupDTO getServiceGroup() {
		return serviceGroup;
	}
	public void setServiceGroup(ServiceGroupDTO serviceGroup) {
		this.serviceGroup = serviceGroup;
	}
	public UnitOfMeasureDTO getUnitOfMeasure() {
		return unitOfMeasure;
	}
	public void setUnitOfMeasure(UnitOfMeasureDTO unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}
	
}
