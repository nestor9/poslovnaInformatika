package poslovnaInformatika.narodnaBanka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import poslovnaInformatika.narodnaBanka.model.Enterprise;
import poslovnaInformatika.narodnaBanka.model.PDVCategory;
import poslovnaInformatika.narodnaBanka.model.ServiceGroup;


public interface ServiceGroupRepository  extends JpaRepository<ServiceGroup,Integer> {
	
	ServiceGroup findByName(String name);
	
	ServiceGroup findByPDVCategory(PDVCategory p);

	ServiceGroup findByEnterprise(Enterprise p);
}
