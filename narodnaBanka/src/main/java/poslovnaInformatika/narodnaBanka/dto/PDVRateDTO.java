package poslovnaInformatika.narodnaBanka.dto;

import java.io.Serializable;
//import java.util.Date;
import java.sql.Date;

import poslovnaInformatika.narodnaBanka.model.PDVRate;

@SuppressWarnings("serial")
public class PDVRateDTO implements Serializable{

	private Integer pdv_rate_id;
	
	private Integer percentage;
	
	private Date date;
	
	private PDVCategoryDTO pdvCategoryDTO;
	
	public PDVRateDTO() {
		super();
	}
	
	public PDVRateDTO(int pdv_rate_id, int percentage, Date date) {
		super();
		this.pdv_rate_id = pdv_rate_id;
		this.percentage = percentage;
		this.date = date;
	}
	
	public PDVRateDTO(PDVRate pdvr) {
		this(pdvr.getPdv_rate_id(), pdvr.getPercentage(), pdvr.getDate());
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

	public void setDate(Date date) {
		this.date = date;
	}

	public PDVCategoryDTO getPdvCategoryDTO() {
		return pdvCategoryDTO;
	}

	public void setPdvCategoryDTO(PDVCategoryDTO pdvCategoryDTO) {
		this.pdvCategoryDTO = pdvCategoryDTO;
	}
	
	
}
