package poslovnaInformatika.narodnaBanka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import poslovnaInformatika.narodnaBanka.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer>{
	
	City findByPtt(String ptt);
	
	City findByName(String city_name);
}
