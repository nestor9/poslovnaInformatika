package poslovnaInformatika.narodnaBanka.service;

import java.util.List;

import poslovnaInformatika.narodnaBanka.model.Enterprise;
import poslovnaInformatika.narodnaBanka.model.PDVCategory;
import poslovnaInformatika.narodnaBanka.model.ServiceGroup;

public interface ServiceGroupServiceInterface {

	ServiceGroup findOne(Integer id);
	
	ServiceGroup save(ServiceGroup serviceGroup);
	
	void remove(Integer id);
	
	ServiceGroup findByName(String name);
	
	ServiceGroup findByPDVCategory(PDVCategory p);

	ServiceGroup findByEnterprise(Enterprise p);
	
	List<ServiceGroup> findAll();
}
