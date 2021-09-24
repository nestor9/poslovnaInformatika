package poslovnaInformatika.narodnaBanka.dto;

import java.io.Serializable;
import java.sql.Date;



public class PricelistDTO implements Serializable {
		
		private static final long serialVersionUID = 4174362857862253015L;
		
		private Long pricelist_id;
		private Date date_from;
		private Integer percentage;
		private Double totalPrice;
//		private int enterprise_id;
		private EnterpriseDTO enterpriseDTO;
		
		
		
//		public PricelistDTO(int pricelist_id, Date date_from, int enterprise_id) {
//			super();
//			this.pricelist_id = pricelist_id;
//			this.date_from = date_from;
//			this.enterprise_id = enterprise_id;
//		}

		public EnterpriseDTO getEnterpriseDTO() {
			return enterpriseDTO;
		}

		public void setEnterpriseDTO(EnterpriseDTO enterpriseDTO) {
			this.enterpriseDTO = enterpriseDTO;
		}

		public Long getPricelist_id() {
			return pricelist_id;
		}

		public void setPricelist_id(Long pricelist_id) {
			this.pricelist_id = pricelist_id;
		}

		public Date getDate_from() {
			return date_from;
		}

		public void setDate_from(Date date_from) {
			this.date_from = date_from;
		}

		public Integer getPercentage() {
			return percentage;
		}

		public void setPercentage(Integer percentage) {
			this.percentage = percentage;
		}

		public Double getTotalPrice() {
			return totalPrice;
		}

		public void setTotalPrice(Double totalPrice) {
			this.totalPrice = totalPrice;
		}

//		public int getEnterprise_id() {
//			return enterprise_id;
//		}
//
//		public void setEnterprise_id(int enterprise_id) {
//			this.enterprise_id = enterprise_id;
//		}

		
		
}
