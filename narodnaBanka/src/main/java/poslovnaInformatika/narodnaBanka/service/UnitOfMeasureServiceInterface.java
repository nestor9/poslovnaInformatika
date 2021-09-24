package poslovnaInformatika.narodnaBanka.service;

import java.util.List;

import poslovnaInformatika.narodnaBanka.model.UnitOfMeasure;

public interface UnitOfMeasureServiceInterface {

	UnitOfMeasure findOne(Integer id);

	UnitOfMeasure save(UnitOfMeasure u);
	
	void remove(Integer id);
	
	UnitOfMeasure findByName(String name);
	
	UnitOfMeasure findByShort_name(String sh_name);
	
	List<UnitOfMeasure> findAll();
}
