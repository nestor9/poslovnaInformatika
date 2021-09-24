package poslovnaInformatika.narodnaBanka.service;

import java.util.List;

import poslovnaInformatika.narodnaBanka.model.City;

public interface CityServiceInteface {
	
	City findOne(Integer city_id);

	City save(City city);
	
	void remove(Integer city_id);
	
	List<City> findAll();
}
