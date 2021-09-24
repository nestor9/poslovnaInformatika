package poslovnaInformatika.narodnaBanka.service;

import java.util.List;

import poslovnaInformatika.narodnaBanka.model.Partner;

public interface PartnerServiceInteface {
	
	Partner findOne(Integer partner_id);
	
	Partner save(Partner partner);
	
	void remove(Integer partner_id);
	
	List<Partner> findAll();

	
}
