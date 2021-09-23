package poslovnaInformatika.narodnaBanka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import poslovnaInformatika.narodnaBanka.model.UnitOfMeasure;

public interface UnitOfMeasureRepository extends JpaRepository<UnitOfMeasure, Integer>{

	UnitOfMeasure findByName(String name);
	
	UnitOfMeasure findByShortName(String sh_name);
	

}
