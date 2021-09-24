package poslovnaInformatika.narodnaBanka.service;

import java.util.List;

import poslovnaInformatika.narodnaBanka.model.User;

public interface UserServiceInterface {
	
	User findByUsernameAndPassword(String username, String password);
	
	User save(User u);
	
	void remove(Integer id);
	
	//User findByEnterprise_Enterprise_id(Integer enterprise_id);
	
	User findByUsername(String username);
	
	List<User> findAll();

	User findOne(Integer user_id);

}
