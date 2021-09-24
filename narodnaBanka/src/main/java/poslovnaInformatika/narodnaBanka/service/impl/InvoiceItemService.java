package poslovnaInformatika.narodnaBanka.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;



import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import poslovnaInformatika.narodnaBanka.model.InvoiceItem;
import poslovnaInformatika.narodnaBanka.repository.InvoiceItemRepository;
import poslovnaInformatika.narodnaBanka.service.InvoiceItemServiceInterface;

@Service
public class InvoiceItemService implements InvoiceItemServiceInterface{

	@Autowired
	private InvoiceItemRepository repository;
	
	@Override
	public InvoiceItem findOne(Integer id) {
		return repository.getOne(id);
	}

	@Override
	public InvoiceItem save(InvoiceItem i) {
		return repository.save(i);
	}

	@Override
	public void remove(Integer id) {
		repository.deleteById(id);		
	}

	@Override
	public InvoiceItem findByQuantity(String name) {
		return repository.findByQuantity(name);
	}

	@Override
	public InvoiceItem findByDiscount(double d) {
		return repository.findByDiscount(d);
	}

	@Override
	public InvoiceItem findByUnit_price(double u) {
		return null;
	}

	@Override
	public InvoiceItem findByPDV_base(double pdv_b) {
		return null;
	}

	@Override
	public InvoiceItem findByPDV_amount(double pdv_a) {
		return repository.findByPDVAmount(pdv_a);
	}

	@Override
	public InvoiceItem findByTotal_amount(double a) {
		return repository.findByTotalAmount(a);
	}

//	@Override
//	public InvoiceItem findByService(Services service) {
//		return repository.findByService(service);
//	}

	@Override
	public List<InvoiceItem> findAll() {
		return repository.findAll();
	}

	@Override
	public ResponseEntity report(Integer id) {
	String connectionUrl = "jdbc:mysql://localhost:3306/salesystem?serverTimezone=UTC";
		
		JasperPrint jp;
		ByteArrayInputStream bis;
		try {
			File file = new File("src\\main\\resources\\IzvestajFaktura.jasper");
			InputStream is = new FileInputStream(file);
			Map<String, Object> param = new HashMap();
			param.put("Param", id);
			Connection conn = DriverManager.getConnection(connectionUrl , "root", "osaroot");
			//JasperPrint jp = JasperFillManager.fillReport(getClass().getResource("src\\main\\resources\\IzvestajFaktura.jasper").openStream(),
		//			null, conn);
			jp = JasperFillManager.fillReport(is,null, conn);
			bis = new ByteArrayInputStream(JasperExportManager.exportReportToPdf(jp));
			 
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=izvestaj_"+id +".pdf");

			return ResponseEntity
		       		.ok()
		       		.headers(headers)
		       		.contentType(MediaType.APPLICATION_PDF)
		       		.body(new InputStreamResource(bis));
		} catch (JRException | IOException | SQLException e) {
			e.printStackTrace();
			throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND, "Greska", e);
		}
	}
	}

	



