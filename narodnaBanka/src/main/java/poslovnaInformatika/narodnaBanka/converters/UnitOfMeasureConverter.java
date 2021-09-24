package poslovnaInformatika.narodnaBanka.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.UnitOfMeasureDTO;
import com.example.demo.model.UnitOfMeasure;

@Component
public class UnitOfMeasureConverter {
	
	@Autowired
	public UnitOfMeasureConverter unitOfMeasureConverter;
	
	public UnitOfMeasureDTO toDTO(UnitOfMeasure unitOfMeasure) {
		UnitOfMeasureDTO dto = new UnitOfMeasureDTO();
		dto.setId(unitOfMeasure.getId());
		dto.setName(unitOfMeasure.getName());
		dto.setShort_name(unitOfMeasure.getShortName());
		return dto;
	}
	
	public UnitOfMeasure toUnitOfMeasure(UnitOfMeasureDTO unitOfMeasureDTO) {
		UnitOfMeasure u = new UnitOfMeasure();
		u.setId(unitOfMeasureDTO.getId());
		u.setName(unitOfMeasureDTO.getName());
		u.setShortName(unitOfMeasureDTO.getShort_name());
		return u;
	}

}
