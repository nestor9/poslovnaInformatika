package poslovnaInformatika.narodnaBanka.service;

import java.util.List;

import poslovnaInformatika.narodnaBanka.model.PDVRate;

public interface PDVRateServiceInterface {

	PDVRate findOne(Integer pdv_rate_id);
	
	PDVRate save(PDVRate pdvRate);
	
	void remove(Integer prv_rate_id);
	
	List<PDVRate> findAll();
}
