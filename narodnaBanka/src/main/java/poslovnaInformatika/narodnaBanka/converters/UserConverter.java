package poslovnaInformatika.narodnaBanka.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import poslovnaInformatika.narodnaBanka.dto.UserDTO;
import poslovnaInformatika.narodnaBanka.model.User;
import poslovnaInformatika.narodnaBanka.service.EnterpriseServiceInterface;

@Component
public class UserConverter {
	
	@Autowired
	public UserConverter userConverter;
	
	@Autowired
	EnterpriseConverter enterpriseConverter;
	
	@Autowired
	EnterpriseServiceInterface enterpriseServiceInterface;
	
	public UserDTO toDTO(User user) {
		UserDTO dto = new UserDTO();
		dto.setUser_id(user.getUser_id());
		dto.setUsername(user.getUsername());
		dto.setPassword(user.getPassword());
		dto.setEnterpriseDTO(enterpriseConverter.toDTO(user.getEnterprise()));
		return dto;
	}
	
	public User toUser(UserDTO userDTO) {
		User u = new User();
		u.setUser_id(userDTO.getUser_id());
		u.setUsername(userDTO.getUsername());
		u.setPassword(userDTO.getPassword());
		u.setEnterprise(enterpriseServiceInterface.findOne(userDTO.getEnterpriseDTO().getEnterprise_id()));
		return u;
	}

}
