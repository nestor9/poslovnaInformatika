package poslovnaInformatika.narodnaBanka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poslovnaInformatika.narodnaBanka.model.User;
import poslovnaInformatika.narodnaBanka.repository.UserRepository;
import poslovnaInformatika.narodnaBanka.service.UserServiceInterface;

@Service
public class UserService implements UserServiceInterface{
	
	
	


	@Autowired
	private UserRepository repository;
	
	@Override
	public List<User> findAll() {
		return repository.findAll();
	}
	
	@Override
	public User findByUsernameAndPassword(String username, String password) {
		return repository.findByUsernameAndPassword(username, password);
	}

	@Override
	public User save(User u) {
		return repository.save(u);
	}

	@Override
	public void remove(Integer id) {
		 repository.deleteById(id);
	}
	
	

//	@Override
//	public User findByEnterprise_Enterprise_id(Integer enterprise_id) {
//		return repository.findByEnterprise_Enterprise_id(enterprise_id);
//	}

	@Override
	public User findOne(Integer user_id) {
		return repository.getOne(user_id);
	}

	@Override
	public User findByUsername(String username) {
		return repository.findByUsername(username);
	}
	
	

}
