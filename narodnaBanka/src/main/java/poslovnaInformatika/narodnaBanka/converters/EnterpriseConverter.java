package poslovnaInformatika.narodnaBanka.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import poslovnaInformatika.narodnaBanka.dto.EnterpriseDTO;
import poslovnaInformatika.narodnaBanka.model.Enterprise;
import poslovnaInformatika.narodnaBanka.service.CityServiceInteface;

@Component
public class EnterpriseConverter {
	
	@Autowired
	public EnterpriseConverter enterpriseConverter;

	@Autowired
	CityConverter cityConverter;
	
	@Autowired
	CityServiceInteface cityServiceInterface;
	
	public EnterpriseDTO toDTO(Enterprise enterprise) {
		EnterpriseDTO dto = new EnterpriseDTO();
		dto.setEnterprise_id(enterprise.getEnterprise_id());
		dto.setNameEnterprise(enterprise.getNameEnterprise());
		dto.setAddress(enterprise.getAddress());
		dto.setPhone(enterprise.getPhone());
		dto.setFax(enterprise.getFax());
		dto.setCityDTO(cityConverter.toDTO(enterprise.getCity()));
		return dto;
	}	
		public Enterprise toEnterprise(EnterpriseDTO enterpriseDTO) {
			Enterprise e = new Enterprise();
			//if(enterpriseDTO.getEnterprise_id()!= null) {
				//e.setEnterprise_id(enterpriseDTO.getEnterprise_id());
			//}
			e.setEnterprise_id(enterpriseDTO.getEnterprise_id());
			e.setNameEnterprise(enterpriseDTO.getNameEnterprise());
			e.setAddress(enterpriseDTO.getNameEnterprise());
			e.setPhone(enterpriseDTO.getPhone());
			e.setFax(enterpriseDTO.getFax());
			e.setCity(cityServiceInterface.findOne(enterpriseDTO.getCityDTO().getCity_id()));
			return e;	
	}
}
