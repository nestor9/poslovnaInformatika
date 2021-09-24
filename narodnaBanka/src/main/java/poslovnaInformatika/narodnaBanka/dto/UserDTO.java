package poslovnaInformatika.narodnaBanka.dto;

import java.io.Serializable;

public class UserDTO implements Serializable{
	
	private Integer user_id;
	
	private String username;
	
	private String password;
	
	private EnterpriseDTO enterpriseDTO;
	
	public UserDTO() {
		super();
	}
	
	public UserDTO(Integer user_id, String username,String password,EnterpriseDTO enterpriseDTO) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.enterpriseDTO = enterpriseDTO;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public EnterpriseDTO getEnterpriseDTO() {
		return enterpriseDTO;
	}

	public void setEnterpriseDTO(EnterpriseDTO enterpriseDTO) {
		this.enterpriseDTO = enterpriseDTO;
	}

}
