package poslovnaInformatika.narodnaBanka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import poslovnaInformatika.narodnaBanka.model.Partner;

public interface PartnerRepository extends JpaRepository<Partner, Integer> {

	Partner findByName(String partner_name);
	
	Partner findByAddress(String address);
	
	Partner findByPhoneNumber(String phone_number);
	
	Partner findByFax(String fax);
	
	Partner findByEmail(String email);
	
}
