package poslovnaInformatika.narodnaBanka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poslovnaInformatika.narodnaBanka.model.Services;
import poslovnaInformatika.narodnaBanka.repository.ServicesRepository;
import poslovnaInformatika.narodnaBanka.service.ServicesServiceInterface;

@Service
public class ServicesService implements ServicesServiceInterface {
	
	@Autowired
	private ServicesRepository servicesRepository;

	@Override
	public Services findOne(Integer services_id) {
		return servicesRepository.getOne(services_id);
	}

	@Override
	public List<Services> findAll() {
		// TODO Auto-generated method stub
		return servicesRepository.findAll();
	}

	@Override
	public Services save(Services services) {
		// TODO Auto-generated method stub
		return servicesRepository.save(services);
	}

	@Override
	public void remove(Integer services_id) {
		servicesRepository.deleteById(services_id);
	}

	@Override
	public Services findByName(String name) {
		// TODO Auto-generated method stub
		return servicesRepository.findByName(name);
	}

	@Override
	public Services findByDescription(String description) {
		// TODO Auto-generated method stub
		return servicesRepository.findByDescription(description);
	}

}
