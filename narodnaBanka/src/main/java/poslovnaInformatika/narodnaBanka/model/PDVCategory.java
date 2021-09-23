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
@Table(name="pdvCategories")
public class PDVCategory {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private Integer id;
	
	@Column(name="name", unique=false, nullable=false)
	private String  name;
	

	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="PDVCategory")
	private Set<ServiceGroup> serviceGroups = new HashSet<ServiceGroup>();
	

	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="PDVCategory")
	private Set<PDVRate> PDVRates = new HashSet<PDVRate>();
	
	public PDVCategory() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Set<PDVRate> getPDVRates() {
		return PDVRates;
	}

	public void setPDVRates(Set<PDVRate> PDVrates) {
		this.PDVRates = PDVrates;
	}

	public Set<ServiceGroup> getServiceGroups() {
		return serviceGroups;
	}

	public void setServiceGroups(Set<ServiceGroup> serviceGroups) {
		this.serviceGroups = serviceGroups;
	}
	
}
