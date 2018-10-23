package com.reference.microservices.solrsearchservice;

import java.io.Serializable;

public class CatalogueBean implements Serializable{
	private static final long serialVersionUID = 7156526077883281623L;
	 private String id;
	 private String productId;
	 private String productName;
	 private String productShortDescription;
	 private String productLongDescription;
	 private String partNumber;
	 private String unitPrice;
	 private String offerPrice;
	 private String quantityAvailable; 
	 
		public CatalogueBean() {
			
		}
		
		public CatalogueBean(String id, String productId, String productName, String productShortDescription,
				String productLongDescription, String partNumber, String unitPrice, 
				String offerPrice, String quantityAvailable) {
			super();
			this.id = id;
			this.productId = productId;
			this.productName = productName;
			this.productShortDescription = productShortDescription;
			this.productLongDescription = productLongDescription;
			this.partNumber = partNumber;
			this.unitPrice = unitPrice;
			this.offerPrice = offerPrice;
			this.quantityAvailable = quantityAvailable;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getProductId() {
			return productId;
		}

		public void setProductId(String productId) {
			this.productId = productId;
		}

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public String getProductShortDescription() {
			return productShortDescription;
		}

		public void setProductShortDescription(String productShortDescription) {
			this.productShortDescription = productShortDescription;
		}

		public String getProductLongDescription() {
			return productLongDescription;
		}

		public void setProductLongDescription(String productLongDescription) {
			this.productLongDescription = productLongDescription;
		}

		public String getPartNumber() {
			return partNumber;
		}

		public void setPartNumber(String partNumber) {
			this.partNumber = partNumber;
		}

		public String getUnitPrice() {
			return unitPrice;
		}

		public void setUnitPrice(String unitPrice) {
			this.unitPrice = unitPrice;
		}

		public String getOfferPrice() {
			return offerPrice;
		}

		public void setOfferPrice(String offerPrice) {
			this.offerPrice = offerPrice;
		}

		public String getQuantityAvailable() {
			return quantityAvailable;
		}

		public void setQuantityAvailable(String quantityAvailable) {
			this.quantityAvailable = quantityAvailable;
		}	 

}
