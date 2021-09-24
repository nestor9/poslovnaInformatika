package poslovnaInformatika.narodnaBanka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poslovnaInformatika.narodnaBanka.model.PriceListItem;
import poslovnaInformatika.narodnaBanka.model.Pricelist;
import poslovnaInformatika.narodnaBanka.repository.PriceListItemRepository;
import poslovnaInformatika.narodnaBanka.service.PriceListItemServiceInterface;

@Service
public class PriceListItemService implements PriceListItemServiceInterface{
	
	@Autowired
	PriceListItemRepository priceListItemRepository;

	@Override
	public PriceListItem findOne(Long price_list_item_id) {
		return priceListItemRepository.getOne(price_list_item_id);
	}

	@Override
	public PriceListItem save(PriceListItem priceListItem) {
		return priceListItemRepository.save(priceListItem);
	}

	@Override
	public void remove(Long price_list_item_id) {
		priceListItemRepository.deleteById(price_list_item_id);
	}

	@Override
	public List<PriceListItem> findAll() {
		return priceListItemRepository.findAll();
	}

	@Override
	public PriceListItem findByCena(Double price) {
		return priceListItemRepository.findByPrice(price);
	}

	@Override
	public List<PriceListItem> findByPricelist(Pricelist pricelist) {
		return priceListItemRepository.findByPricelist(pricelist);
	}
	
	

}
