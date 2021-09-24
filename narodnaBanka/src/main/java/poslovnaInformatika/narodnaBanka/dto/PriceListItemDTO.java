package poslovnaInformatika.narodnaBanka.dto;

import java.io.Serializable;

import poslovnaInformatika.narodnaBanka.model.PriceListItem;
import poslovnaInformatika.narodnaBanka.model.Pricelist;
import poslovnaInformatika.narodnaBanka.model.Services;



@SuppressWarnings("serial")
public class PriceListItemDTO implements Serializable{

	private Long price_list_item_id;
	
	private Double price;
	
	private PricelistDTO pricelist;
	
	private ServicesDTO services;
	
	public PriceListItemDTO() {
		super();
	}
	
	public PriceListItemDTO(Long price_list_item_id, double price,PricelistDTO pricelist, ServicesDTO services) {
		super();
		this.price_list_item_id = price_list_item_id;
		this.price = price;
		this.pricelist = pricelist;
		this.services = services;
	}
	
	public PriceListItemDTO(PriceListItem pi) {
		this(pi.getPrice_list_item_id(), pi.getPrice(), pi.getPricelist(), pi.getServices());
	}

	
	public PriceListItemDTO(Long price_list_item_id2, Double price2, Pricelist pricelist2, Services services2) {
		// TODO Auto-generated constructor stub
	}

	public Long getPrice_list_item_id() {
		return price_list_item_id;
	}

	public void setPrice_list_item_id(Long price_list_item_id) {
		this.price_list_item_id = price_list_item_id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public PricelistDTO getPricelist() {
		return pricelist;
	}

	public void setPricelist(PricelistDTO pricelist) {
		this.pricelist = pricelist;
	}

	public ServicesDTO getServices() {
		return services;
	}

	public void setServices(ServicesDTO services) {
		this.services = services;
	}
	
	
	
	
}
