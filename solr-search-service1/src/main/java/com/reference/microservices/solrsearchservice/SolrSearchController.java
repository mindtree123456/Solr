package com.reference.microservices.solrsearchservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.reference.microservices.solrsearchservice.CatalogueBean;

@RestController
class SolrSearchController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private Environment environment;
	
	//@Cacheable(value = "CatalogueBean", key = "#productName")
	@GetMapping("/product-detail-solr/productName/{productName}")
	public CatalogueBean retreiveProductDetailSolr(@PathVariable String productName) {
		
		SolrDocumentList docList =null;
		//String urlString = "http://win10-devops.southindia.cloudapp.azure.com:8983/solr/catalogue";
		String urlString =  environment.getProperty("solr.url");
		logger.info("urlString in SolrSearchController is --- >>>>>>>>  "+urlString);
		HttpSolrClient solr = new HttpSolrClient.Builder(urlString).build();
		solr.setParser(new XMLResponseParser());
		SolrQuery query = new SolrQuery();
		//query.set("q", "*:*");
		query.set("q", "productName:"+productName);
		query.set("wt", "json");
		
		//List<CatalogueBean> catalogueBeanList = new ArrayList<>();
		CatalogueBean catalogueBean = new CatalogueBean();
		try {
			logger.info("befor query --- >>>  ");
			QueryResponse response = solr.query(query);
			logger.info("after query --- >>>>>>>>  ");
			docList = response.getResults();
			if(null!=docList && docList.size()>0) {
				for (SolrDocument doc : docList) {
					//CatalogueBean catalogueBean =  new CatalogueBean();
					catalogueBean.setId(" "+doc.getFieldValue("id"));
					catalogueBean.setOfferPrice(" "+doc.getFieldValue("offerPrice"));
					catalogueBean.setPartNumber(" "+doc.getFieldValue("partNumber"));
					catalogueBean.setProductId(" "+doc.getFieldValue("productId"));
					catalogueBean.setProductLongDescription(" "+doc.getFieldValue("productLongDescription"));
					catalogueBean.setProductName(" "+doc.getFieldValue("productName"));
					catalogueBean.setProductShortDescription(" "+doc.getFieldValue("productShortDescription"));
					catalogueBean.setQuantityAvailable(" "+doc.getFieldValue("quantityAvailable"));
					catalogueBean.setUnitPrice(" "+doc.getFieldValue("unitPrice"));
					//catalogueBeanList.add(catalogueBean);
				     
				}
			}

		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return catalogueBean;

}	
	
	
	@GetMapping("/product-detail-list/productName/{productName}")
	public List<CatalogueBean> retreiveProductDetailList(@PathVariable String productName) {
	
		logger.info("hitting  ---product-detail-solr-list  >>>>>>>>  ");
	String [] productNameArray= new String [100];
	if(null!=productName && productName.length()>0 && productName.contains(",")) {
		productNameArray = productName.split(",");
	}else {
		productNameArray = new String[]{productName};
	}

	List<CatalogueBean> catalogueBeanList = new ArrayList<>();
	SolrDocumentList docList =null;
	//String urlString = "http://win10-devops.southindia.cloudapp.azure.com:8983/solr/catalogue";
	String urlString =  environment.getProperty("solr.url");
	logger.info("urlString in SolrSearchController is --- >>>>>>>>  "+urlString);
	HttpSolrClient solr = new HttpSolrClient.Builder(urlString).build();
	solr.setParser(new XMLResponseParser());
	SolrQuery query = new SolrQuery();
	//query.set("q", "*:*");
	 for(String pName:productNameArray) {
		query.set("q", "productName:"+pName);
		query.set("wt", "json"); 
		try {
			logger.info("befor query --- >>>  ");
			QueryResponse response = solr.query(query);
			logger.info("after query --- >>>>>>>>  ");
			docList = response.getResults();
			if(null!=docList && docList.size()>0) {
				SolrDocument doc=docList.get(0);
				CatalogueBean catalogueBean =  new CatalogueBean();
				catalogueBean.setId(" "+doc.getFieldValue("id"));
				catalogueBean.setOfferPrice(" "+doc.getFieldValue("offerPrice"));
				catalogueBean.setPartNumber(" "+doc.getFieldValue("partNumber"));
				catalogueBean.setProductId(" "+doc.getFieldValue("productId"));
				catalogueBean.setProductLongDescription(" "+doc.getFieldValue("productLongDescription"));
				catalogueBean.setProductName(" "+doc.getFieldValue("productName"));
				catalogueBean.setProductShortDescription(" "+doc.getFieldValue("productShortDescription"));
				catalogueBean.setQuantityAvailable(" "+doc.getFieldValue("quantityAvailable"));
				catalogueBean.setUnitPrice(" "+doc.getFieldValue("unitPrice"));
				catalogueBeanList.add(catalogueBean);
			}

		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}				
	 }
	
	return catalogueBeanList;
	
	}
	
	
}
