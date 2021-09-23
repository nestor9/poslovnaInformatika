package poslovnaInformatika.narodnaBanka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import poslovnaInformatika.narodnaBanka.model.PriceListItem;
import poslovnaInformatika.narodnaBanka.model.Pricelist;

public interface PriceListItemRepository extends JpaRepository<PriceListItem, Long>{

	PriceListItem findByPrice(double price);
	
	List<PriceListItem> findAll();
	
	List<PriceListItem> findByPricelist(Pricelist pricelist);
}
