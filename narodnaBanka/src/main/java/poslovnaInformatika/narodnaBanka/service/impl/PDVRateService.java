package poslovnaInformatika.narodnaBanka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poslovnaInformatika.narodnaBanka.model.PDVRate;
import poslovnaInformatika.narodnaBanka.repository.PDVRateRepository;
import poslovnaInformatika.narodnaBanka.service.PDVRateServiceInterface;

@Service
public class PDVRateService implements PDVRateServiceInterface{
	
	@Autowired
	PDVRateRepository pdvRateRepository;

	@Override
	public PDVRate findOne(Integer pdv_rate_id) {
		return pdvRateRepository.getOne(pdv_rate_id);
	}

	@Override
	public PDVRate save(PDVRate pdvRate) {
		return pdvRateRepository.save(pdvRate);
	}

	@Override
	public void remove(Integer pdv_rate_id) {
		pdvRateRepository.deleteById(pdv_rate_id);
	}

	@Override
	public List<PDVRate> findAll() {
		return pdvRateRepository.findAll();
	}
	
	

}
