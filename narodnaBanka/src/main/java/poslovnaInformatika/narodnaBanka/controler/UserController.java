package poslovnaInformatika.narodnaBanka.controler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poslovnaInformatika.narodnaBanka.converters.EnterpriseConverter;
import poslovnaInformatika.narodnaBanka.converters.UserConverter;
import poslovnaInformatika.narodnaBanka.dto.UserDTO;
import poslovnaInformatika.narodnaBanka.model.User;
import poslovnaInformatika.narodnaBanka.service.UserServiceInterface;

@RestController
@RequestMapping(value="salesystem/users")
public class UserController {

	@Autowired
	private UserServiceInterface service;
	
	@Autowired
	UserConverter userConverter;
	
	@Autowired
	EnterpriseConverter enterpriseConverter;
	
	@PostMapping(consumes="application/json")
	public ResponseEntity<UserDTO> login(@RequestBody UserDTO userDTO){
		User u = service.findByUsernameAndPassword(userDTO.getUsername(),userDTO.getPassword());
		if (u==null) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND); 
		}else {
			UserDTO uDTO = new UserDTO();
			uDTO.setUsername(u.getUsername());
			uDTO.setEnterpriseDTO(enterpriseConverter.toDTO(u.getEnterprise()));
//			uDTO.setEnterprise_id(u.getEnterprise().getEnterprise_id());
			return new ResponseEntity<UserDTO>(uDTO, HttpStatus.OK);
		}
	}
	
	@PostMapping(value="/add",consumes="application/json")
	public ResponseEntity<UserDTO> register(@RequestBody UserDTO uDTO){
		User u = userConverter.toUser(uDTO);
		User us = service.save(u);
		UserDTO userDTO = userConverter.toDTO(us);
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
	}
		
	@GetMapping(value="/all")
	public ResponseEntity<List<UserDTO>> getUsers(){
		List<User> users = service.findAll();
		List<UserDTO> usersDTO = new ArrayList<UserDTO>();
		for (User u : users) {
			usersDTO.add(userConverter.toDTO(u));
		}
		return new ResponseEntity<List<UserDTO>>(usersDTO, HttpStatus.OK);
	}
	
	@PutMapping(value="/{id}", consumes = "application/json")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO uDTO, @PathVariable("id") Integer id){
		User user = service.findOne(id);
		if (user == null) {
			return new ResponseEntity<UserDTO>(HttpStatus.CREATED);
		}
		User u = service.save(userConverter.toUser(uDTO));
		UserDTO userDTO = userConverter.toDTO(u);
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.CREATED);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id){
		User user = service.findOne(id);
		if (user!=null) {
			service.remove(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}
