package poslovnaInformatika.narodnaBanka.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="cities")
public class City {

	private static final long serialVersionUID = -5652339993553733589L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="city_id", unique=true, nullable=false)
	private Integer city_id;
	
	@Column(name="ptt", unique=false, nullable=false)
	private Integer ptt;
	
	@Column(name="city_name", unique=false, nullable=false)
	private String name;
	
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="city")
	private Set<Enterprise> enterprises = new HashSet<Enterprise>();
	
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="city")
	private Set<Partner> partners = new HashSet<Partner>();
	
	public City() {
		
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public Set<Enterprise> getEnterprises() {
		return enterprises;
	}

	public void setEnterprises(Set<Enterprise> enterprises) {
		this.enterprises = enterprises;
	}

	public Set<Partner> getPartners() {
		return partners;
	}

	public void setPartners(Set<Partner> partners) {
		this.partners = partners;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
