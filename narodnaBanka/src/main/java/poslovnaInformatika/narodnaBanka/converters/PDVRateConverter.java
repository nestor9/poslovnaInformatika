package poslovnaInformatika.narodnaBanka.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.PDVRateDTO;
import com.example.demo.model.PDVRate;
import com.example.demo.servis.PDVCategoryServiceInterface;

@Component
public class PDVRateConverter {
	
	@Autowired
	public PDVRateConverter pdvRateConverter;
	
	@Autowired
	PDVCategoryConverter pdvCategoryConverter;
	
	@Autowired
	PDVCategoryServiceInterface pdvCategoryServiceInterface;
	
	public PDVRateDTO toDTO(PDVRate pdvRate) {
		PDVRateDTO dto = new PDVRateDTO();
		dto.setPdv_rate_id(pdvRate.getPdv_rate_id());
		dto.setPercentage(pdvRate.getPercentage());
		dto.setDate(pdvRate.getDate());
		dto.setPdvCategoryDTO(pdvCategoryConverter.toDTO(pdvRate.getPDVCategory()));
		return dto;
	}
	
	public PDVRate toPdvRate(PDVRateDTO pdvRateDTO) {
		PDVRate p = new PDVRate();
		p.setPdv_rate_id(pdvRateDTO.getPdv_rate_id());
		p.setPercentage(pdvRateDTO.getPercentage());
		p.setDateOf(pdvRateDTO.getDate());
		p.setPDVCategory(pdvCategoryServiceInterface.findOne(pdvRateDTO.getPdvCategoryDTO().getId()));
		return p;
	}

}
