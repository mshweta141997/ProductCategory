package com.example.ProductCategory.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import in.nimpainfotech.NimpaInfotech.CustomException.DataNotAvailable;
import in.nimpainfotech.NimpaInfotech.dao.ProductDao;
import in.nimpainfotech.NimpaInfotech.entity.Product;

@RestController
@RequestMapping("/product")
public class ProductController {

	
	@Autowired
	private ProductDao productDao;
	
	@RequestMapping(value = "addProduct", method = RequestMethod.POST)
	public void addProuct(@RequestBody Product productBean) throws Exception {
		
		if(productBean.getProductName()==null && productBean.getProductPrice()==null) {
			throw new Exception("Product Bean is empty");
		}
		
		productDao.save(productBean);
		System.out.println("Product has been added successfully");
	}
	
	@RequestMapping(value = "/updateProduct/{id}", method = RequestMethod.PUT)
	public void updateProuct(@RequestBody Product productBean, @PathVariable("id") Integer pid) throws Exception {
		
	    Optional<Product> product = productDao.findById(pid);
		
	    product.get().setProductName(productBean.getProductName());
	    product.get().setProductPrice(productBean.getProductPrice());
		productDao.save(productBean);
		System.out.println("Product has been updated successfully");
	}
	@RequestMapping(value = "/deleteProduct/{id}", method = RequestMethod.DELETE)
	public void deleteProuct(@PathVariable("id") Integer pid)   {
		
		try {
		     productDao.deleteById(pid);
		     System.out.println("Product has been deleted successfully");
		}
		catch (DataNotAvailable e) {
		    throw new DataNotAvailable("no record found");
		}
		
		
		
	}
}
