package poslovnaInformatika.narodnaBanka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import poslovnaInformatika.narodnaBanka.model.Services;

public interface ServicesRepository extends JpaRepository<Services, Integer> {
	Services findByName(String name);
	Services findByDescription(String description);
	Services findByGoods(Boolean goods);
	

}
