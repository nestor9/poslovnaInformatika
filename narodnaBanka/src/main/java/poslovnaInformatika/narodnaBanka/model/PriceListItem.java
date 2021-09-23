package poslovnaInformatika.narodnaBanka.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="priceListItems")
public class PriceListItem implements Serializable {
	
	private static final long serialVersionUID = -5652339993553733589L;


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="price_list_item_id", unique=true, nullable=false)
	private Long price_list_item_id;
	
	@Column(name="price", unique=false, nullable=false)
	private Double price;
	
	@ManyToOne
	@JoinColumn(name="pricelist_id", referencedColumnName="pricelist_id", nullable=false)
	 private Pricelist pricelist;
	
	@ManyToOne
	@JoinColumn(name="service_id", referencedColumnName="service_id", nullable=false)
	 private Services services;
	
	public PriceListItem() {
		
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

	public Pricelist getPricelist() {
		return pricelist;
	}

	public void setPricelist(Pricelist pricelist) {
		this.pricelist = pricelist;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Services getServices() {
		return services;
	}

	public void setServices(Services services) {
		this.services = services;
	}
	
	
}
