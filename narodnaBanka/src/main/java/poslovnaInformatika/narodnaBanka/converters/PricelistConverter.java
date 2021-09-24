package poslovnaInformatika.narodnaBanka.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.demo.dto.PricelistDTO;
import com.example.demo.model.Pricelist;
import com.example.demo.servis.EnterpriseServiceInterface;

@Component
public class PricelistConverter {
	
	@Autowired
	public PricelistConverter pricelistConverter;
	
	
	
	@Autowired
	EnterpriseConverter enterpriseConverter;
	
	@Autowired
	EnterpriseServiceInterface enterpriseServiceInterface;
	
	public PricelistDTO toDTO(Pricelist pricelist) {
		PricelistDTO dto = new PricelistDTO();
		dto.setPricelist_id(pricelist.getPricelist_id());
		dto.setDate_from(pricelist.getDateFrom());
		dto.setPercentage(pricelist.getPercentage());
		dto.setTotalPrice(pricelist.getTotalPrice());
		dto.setEnterpriseDTO(enterpriseConverter.toDTO(pricelist.getEnterprise()));
		return dto;
	}
	
	public Pricelist toPricelist(PricelistDTO pricelistDTO) {
		Pricelist p = new Pricelist();
		
		System.out.println("date" + pricelistDTO.getDate_from());
		System.out.println("id" + pricelistDTO.getEnterpriseDTO().getEnterprise_id());
		
		p.setPricelist_id(pricelistDTO.getPricelist_id());
		p.setDateFrom(pricelistDTO.getDate_from());
		if (pricelistDTO.getPercentage() == null || pricelistDTO.getPercentage().toString() == "") {
			p.setPercentage(0);
		}
		p.setPercentage(pricelistDTO.getPercentage());
		if(pricelistDTO.getTotalPrice() == null || pricelistDTO.getTotalPrice().toString() == "") {
			p.setTotalPrice(0.0);
		}
		p.setTotalPrice(pricelistDTO.getTotalPrice());
		p.setEnterprise(enterpriseServiceInterface.findOne(pricelistDTO.getEnterpriseDTO().getEnterprise_id()));
		
		
		
		return p;
	}
}
