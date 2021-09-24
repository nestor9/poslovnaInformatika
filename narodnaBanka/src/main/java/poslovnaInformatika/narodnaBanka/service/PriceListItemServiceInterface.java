package poslovnaInformatika.narodnaBanka.service;

import java.util.List;

import poslovnaInformatika.narodnaBanka.model.PriceListItem;
import poslovnaInformatika.narodnaBanka.model.Pricelist;

public interface PriceListItemServiceInterface {
	
	PriceListItem findOne(Long price_list_item_id);
	
	PriceListItem save(PriceListItem priceListItem);
	
	void remove(Long price_list_item_id);
	
	List<PriceListItem> findAll();
	
	PriceListItem findByCena(Double price);
	
	List<PriceListItem> findByPricelist(Pricelist pricelist);

}
