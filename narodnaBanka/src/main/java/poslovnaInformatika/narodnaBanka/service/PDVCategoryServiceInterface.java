package poslovnaInformatika.narodnaBanka.service;

import java.util.List;

import poslovnaInformatika.narodnaBanka.model.PDVCategory;

public interface PDVCategoryServiceInterface {

	PDVCategory findOne(Integer id);
	
	PDVCategory save(PDVCategory p);
	
	void remove(Integer p);

	PDVCategory findByName(String name);
	
//	PDVCategory findByServiceGroup(ServiceGroup s);

//	PDVCategory findByPDVRate(PDVRate p);

	List<PDVCategory> findAll();
}
