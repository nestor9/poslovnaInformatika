package poslovnaInformatika.narodnaBanka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import poslovnaInformatika.narodnaBanka.model.PDVCategory;

public interface PDVCategoryRepository extends JpaRepository<PDVCategory, Integer> {

	PDVCategory findByName(String name);
	
//	PDVCategory findByServiceGroup(ServiceGroup s);

//	PDVCategory findByPDVRates(PDVRate p);

}
