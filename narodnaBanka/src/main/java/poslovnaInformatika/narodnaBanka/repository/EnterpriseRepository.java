package poslovnaInformatika.narodnaBanka.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import poslovnaInformatika.narodnaBanka.model.Enterprise;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
	Enterprise findByNameEnterprise(String nameEnterprise);
	Enterprise findByAddress(String address);
	Enterprise findByPhone(String phone);
	Enterprise findByFax(String fax);
	
	List<Enterprise> findAll();
	
	

}


