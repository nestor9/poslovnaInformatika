package poslovnaInformatika.narodnaBanka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poslovnaInformatika.narodnaBanka.model.City;
import poslovnaInformatika.narodnaBanka.repository.CityRepository;
import poslovnaInformatika.narodnaBanka.service.CityServiceInteface;

@Service
public class CityService implements CityServiceInteface{

	@Autowired
	CityRepository cityRepository;

	@Override
	public City findOne(Integer city_id) {
		return cityRepository.getOne(city_id);
	}

	@Override
	public City save(City city) {
		return cityRepository.save(city);
	}

	@Override
	public void remove(Integer city_id) {
		cityRepository.deleteById(city_id);
	}

	@Override
	public List<City> findAll() {
		return cityRepository.findAll();
	}
	
	
}
