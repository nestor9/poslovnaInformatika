package poslovnaInformatika.narodnaBanka.service;

import java.util.List;

import poslovnaInformatika.narodnaBanka.model.Services;

public interface ServicesServiceInterface {

	Services findOne(Integer services_id);
	
	List<Services> findAll();
	
	Services save(Services services);
	
	void remove(Integer services_id);
	
	Services findByName(String name);
	
	Services findByDescription(String description);
	
}
