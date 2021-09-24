package poslovnaInformatika.narodnaBanka.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.PriceListItemDTO;
import com.example.demo.model.PriceListItem;
import com.example.demo.servis.PricelistServiceInterface;
import com.example.demo.servis.ServicesServiceInterface;

@Component
public class PricelistItemConverter {
	
	@Autowired
	public PricelistItemConverter pricelistItemConverter;
	
	@Autowired
	PricelistConverter pricelistConverter;
	
	@Autowired
	PricelistServiceInterface pricelistService;
	
	@Autowired
	ServicesConverter servicesConverter;
	
	@Autowired
	ServicesServiceInterface serviceInterface;
	
	public PriceListItemDTO toDTO(PriceListItem priceListItem) {
		PriceListItemDTO dto = new PriceListItemDTO();
		dto.setPrice_list_item_id(priceListItem.getPrice_list_item_id());
		dto.setPrice(priceListItem.getPrice());
		dto.setServices(servicesConverter.toDTO(priceListItem.getServices()));
		dto.setPricelist(pricelistConverter.toDTO(priceListItem.getPricelist()));
		return dto;
	}
	
	public PriceListItem toPriceListItem(PriceListItemDTO priceListItemDTO) {
		PriceListItem p = new PriceListItem();
		if(priceListItemDTO.getPrice_list_item_id()!=null) {
		p.setPrice_list_item_id(priceListItemDTO.getPrice_list_item_id());
		}
		p.setPrice(priceListItemDTO.getPrice());
		p.setServices(serviceInterface.findOne(priceListItemDTO.getServices().getServices_id()));
		p.setPricelist(pricelistService.findOne(priceListItemDTO.getPricelist().getPricelist_id()));
		return p;
	}
	

}
