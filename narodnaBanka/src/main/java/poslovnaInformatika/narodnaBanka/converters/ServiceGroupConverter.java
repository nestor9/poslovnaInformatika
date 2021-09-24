package poslovnaInformatika.narodnaBanka.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.ServiceGroupDTO;
import com.example.demo.model.ServiceGroup;
import com.example.demo.servis.EnterpriseServiceInterface;
import com.example.demo.servis.PDVCategoryServiceInterface;

@Component
public class ServiceGroupConverter {
	
	@Autowired
	public ServiceGroupConverter serviceGroupConverter;
	
	@Autowired
	PDVCategoryConverter pdvCategoryConverter;
	
	@Autowired
	PDVCategoryServiceInterface pdvCategoryServiceInterface;
	
	@Autowired
	EnterpriseConverter enterpriseConverter;
	
	@Autowired
	EnterpriseServiceInterface enterpriseServiceInterface;
	
	public ServiceGroupDTO toDTO(ServiceGroup serviceGroup) {
		ServiceGroupDTO dto = new ServiceGroupDTO();
		dto.setId(serviceGroup.getId());
		dto.setName(serviceGroup.getName());
		dto.setPDVCategory(pdvCategoryConverter.toDTO(serviceGroup.getPDVCategory()));
		dto.setFirm(enterpriseConverter.toDTO(serviceGroup.getEnterprise()));
		return dto;
	}
	
	public ServiceGroup toServiceGroup(ServiceGroupDTO serviceGroupDTO) {
		ServiceGroup s = new ServiceGroup();
		s.setId(serviceGroupDTO.getId());
		s.setName(serviceGroupDTO.getName());
		s.setPDVCategory(pdvCategoryServiceInterface.findOne(serviceGroupDTO.getId()));
		return s;
	}

}
