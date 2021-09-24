package poslovnaInformatika.narodnaBanka.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CityDTO implements Serializable{

	private Integer city_id;
	
	private Integer ptt;
	
	private String city_name;
	
//	public CityDTO() {
//		super();
//	}
//	
//	public CityDTO(String city_name) {
//		this.city_name=city_name;
//	}
//	
//	public CityDTO(int city_id, int ptt, String city_name) {
//		super();
//		this.city_id = city_id;
//		this.ptt = ptt;
//		this.city_name = city_name;
//	}
//	
//	public CityDTO(City c) {
//		this(c.getCity_id(), c.getPtt(), c.getName());
//	}

	public Integer getCity_id() {
		return city_id;
	}

	public void setCity_id(Integer city_id) {
		this.city_id = city_id;
	}

	public Integer getPtt() {
		return ptt;
	}

	public void setPtt(Integer ptt) {
		this.ptt = ptt;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	
	
}


