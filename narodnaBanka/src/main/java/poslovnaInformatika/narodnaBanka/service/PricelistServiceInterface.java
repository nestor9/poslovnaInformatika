package poslovnaInformatika.narodnaBanka.service;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;

import poslovnaInformatika.narodnaBanka.model.Pricelist;

public interface PricelistServiceInterface {
	
	Pricelist findByDateFrom (Date dateFrom);

	Pricelist findOne(Long pricelist_id);
	
	List<Pricelist> findAll();
	
	Pricelist save(Pricelist pricelist);
	
	void remove(Long pricelist_id);
	
//	Pricelist findByTotalPrice(Integer total_price);
	
	Page<Pricelist> findAll(int pageNo, int pageSize);
	
	Page<Pricelist> findAllByDateFrom(Date dateFrom, int pageNo, int pageSize);
}
