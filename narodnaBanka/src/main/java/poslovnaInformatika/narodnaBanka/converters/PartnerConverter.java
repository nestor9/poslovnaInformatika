package poslovnaInformatika.narodnaBanka.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.demo.dto.PartnerDTO;
import com.example.demo.model.Partner;
import com.example.demo.servis.CityServiceInteface;
import com.example.demo.servis.EnterpriseServiceInterface;

@Component
public class PartnerConverter {
	
	@Autowired
	public PartnerConverter partnerConverter;
	
	@Autowired
	EnterpriseConverter enterpriseConverter;
	
	@Autowired
	EnterpriseServiceInterface enterpriseServiceInterface;
	
	@Autowired
	CityConverter cityConverter;
	
	@Autowired
	CityServiceInteface cityServiceInteface;
	
	public PartnerDTO toDTO(Partner partner) {
		PartnerDTO dto = new PartnerDTO();
		dto.setPartner_id(partner.getPartner_id());
		dto.setPartner_name(partner.getName());
		dto.setAddress(partner.getAddress());
		dto.setPhone_number(partner.getPhoneNumber());
		dto.setFax(partner.getFax());
		dto.setEmail(partner.getEmail());
		dto.setCityDTO(cityConverter.toDTO(partner.getCity()));
		dto.setEnterpriseDTO(enterpriseConverter.toDTO(partner.getEnterprise()));
		return dto;
	}
	
	public Partner toPartner(PartnerDTO partnerDTO) {
		Partner p = new Partner();
		p.setPartner_id(partnerDTO.getPartner_id());
		p.setName(partnerDTO.getPartner_name());
		p.setAddress(partnerDTO.getAddress());
		p.setPhoneNumber(partnerDTO.getPhone_number());
		p.setFax(partnerDTO.getFax());
		p.setEmail(partnerDTO.getEmail());
		p.setCity(cityServiceInteface.findOne(partnerDTO.getCityDTO().getCity_id()));
		p.setEnterprise(enterpriseServiceInterface.findOne(partnerDTO.getEnterpriseDTO().getEnterprise_id()));
		return p;
	}

}
