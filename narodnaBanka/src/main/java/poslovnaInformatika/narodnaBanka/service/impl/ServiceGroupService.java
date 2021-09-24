package poslovnaInformatika.narodnaBanka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poslovnaInformatika.narodnaBanka.model.Enterprise;
import poslovnaInformatika.narodnaBanka.model.PDVCategory;
import poslovnaInformatika.narodnaBanka.model.ServiceGroup;
import poslovnaInformatika.narodnaBanka.repository.ServiceGroupRepository;
import poslovnaInformatika.narodnaBanka.service.ServiceGroupServiceInterface;

@Service
public class ServiceGroupService implements ServiceGroupServiceInterface {

	@Autowired
	private ServiceGroupRepository repository;
	
	@Override
	public ServiceGroup findOne(Integer id) {
		return repository.getOne(id);
	}

	@Override
	public ServiceGroup save(ServiceGroup serviceGroup) {
		return repository.save(serviceGroup);
	}

	@Override
	public void remove(Integer id) {
		repository.deleteById(id);
	}

	@Override
	public ServiceGroup findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public ServiceGroup findByPDVCategory(PDVCategory p) {
		return repository.findByPDVCategory(p);
	}

	@Override
	public ServiceGroup findByEnterprise(Enterprise p) {
		return repository.findByEnterprise(p);
	}

	@Override
	public List<ServiceGroup> findAll() {
		return repository.findAll();
	}
	
	


	

	
}
