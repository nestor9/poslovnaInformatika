package poslovnaInformatika.narodnaBanka.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.PDVCategoryDTO;
import com.example.demo.model.PDVCategory;

@Component
public class PDVCategoryConverter {

	@Autowired
	public PDVCategoryConverter pdvCategoryConverter;
	
	public PDVCategoryDTO toDTO(PDVCategory pdvCategory) {
		PDVCategoryDTO dto = new PDVCategoryDTO();
		dto.setId(pdvCategory.getId());
		dto.setName(pdvCategory.getName());
		return dto;
	}
	
	public PDVCategory toPDVCategory(PDVCategoryDTO pdvCategoryDTO) {
		PDVCategory pdv = new PDVCategory();
		pdv.setId(pdvCategoryDTO.getId());
		pdv.setName(pdvCategoryDTO.getName());
		return pdv;
	}
}
