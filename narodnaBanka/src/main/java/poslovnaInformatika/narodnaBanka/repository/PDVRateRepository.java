package poslovnaInformatika.narodnaBanka.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import poslovnaInformatika.narodnaBanka.model.PDVRate;
import poslovnaInformatika.narodnaBanka.model.PriceListItem;

public interface PDVRateRepository extends JpaRepository<PDVRate, Integer>{
	
	PriceListItem findByPercentage(Integer percentage);
	
	PriceListItem findByDate(Date date);
}
