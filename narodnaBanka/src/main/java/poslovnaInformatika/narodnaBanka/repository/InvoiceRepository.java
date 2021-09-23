package poslovnaInformatika.narodnaBanka.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import poslovnaInformatika.narodnaBanka.model.Enterprise;
import poslovnaInformatika.narodnaBanka.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

	Invoice findByNumber(Integer number_invoice);
	Invoice findByDate(Date date_invoice);
	Invoice findByDateCurrency(Date date_currency);
	Invoice findByTotalPdv(Double total_pdv);
	
	List<Invoice> findByEnterprise(Enterprise enterprise);

}
