package poslovnaInformatika.narodnaBanka.model;

import java.io.Serializable;
//import java.util.Date;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="pdvRates")
public class PDVRate implements Serializable{
	
	private static final long serialVersionUID = -5652339993553733589L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pdv_rate_id", unique=true, nullable=false)
	private Integer pdv_rate_id;
	
	@Column(name="percentage", unique=false, nullable=false)
	private Integer percentage;
	
	@Column(name="date_of", unique=false, nullable=false)
	private Date date;
	
	@ManyToOne
	@JoinColumn(name="pdv_category_id", referencedColumnName="id", nullable=false)
	 private PDVCategory PDVCategory;
	
	public PDVRate() {
		
	}

	public Integer getPdv_rate_id() {
		return pdv_rate_id;
	}

	public void setPdv_rate_id(Integer pdv_rate_id) {
		this.pdv_rate_id = pdv_rate_id;
	}

	public Integer getPercentage() {
		return percentage;
	}

	public void setPercentage(Integer percentage) {
		this.percentage = percentage;
	}

	public Date getDate() {
		return date;
	}

	public void setDateOf(Date date) {
		this.date = date;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PDVCategory getPDVCategory() {
		return PDVCategory;
	}

	public void setPDVCategory(PDVCategory pDVCategory) {
		PDVCategory = pDVCategory;
	}
	
	
	

}
