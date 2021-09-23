package poslovnaInformatika.narodnaBanka.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import poslovnaInformatika.narodnaBanka.model.BusinessYear;

public interface BusinessYearRepository extends JpaRepository<BusinessYear, Integer> {
	
	@Query(value = "SELECT b FROM BusinessYear b where :currentDate BETWEEN b.dateFrom and b.dateTo")
	BusinessYear getYear(@Param("currentDate")Date currentDate);
	
	//

}
