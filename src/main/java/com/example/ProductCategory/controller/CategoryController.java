package com.example.ProductCategory.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.nimpainfotech.NimpaInfotech.dao.CategoryDao;
import in.nimpainfotech.NimpaInfotech.dao.ProductDao;
import in.nimpainfotech.NimpaInfotech.entity.Category;
import in.nimpainfotech.NimpaInfotech.entity.Product;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private ProductDao productDao;
	
	
	@RequestMapping(value = "/create", method =RequestMethod.POST)
	public void createCategory(@RequestBody Category categoryBean ) {
		
		categoryDao.save(categoryBean);
	}
	
	@RequestMapping(value = "/createProduct/{catId}/{prodId}", method =RequestMethod.POST)
	public void addCategoryProduct( @PathVariable("catId") Integer catId,@PathVariable("prodId") Integer prodId ) {
		
		Category cat = categoryDao.findById(catId).get();
		Product product = productDao.findById(prodId).get();
		cat.getProductList().add(product);
	    product.setCategory(cat);
		productDao.save(product);
	}
	
	@RequestMapping(value = "/deleteCat/{catId}", method =RequestMethod.DELETE)
	public void deleteCategory(@PathVariable("catId") Integer id) {
		Category cat1 = categoryDao.findById(id).get();
		List<Product> allProducts = cat1.getProductList();
		for(Product prd : allProducts) {
			Product prd1 = productDao.findById(prd.getProductId()).get();
			prd1.setCategory(null);
		}
		
		categoryDao.deleteById(id);
	}

	@RequestMapping(value = "/updateCat/{catId}/{pId}", method =RequestMethod.PUT)
	public void updateCategory(@RequestBody Category categoryBean, @PathVariable("catId") Integer id,@PathVariable("pId") Integer pId) {
		Optional<Category> cat1 = categoryDao.findById(id);
		List<Product> allProducts = cat1.get().getProductList();
		Product prd = productDao.findById(pId).get();
		prd.setCategory(categoryBean);
		allProducts.add(prd);
        System.out.println(categoryBean.getCategoryName());
		cat1.get().setCategoryName(categoryBean.getCategoryName());
		categoryDao.save(categoryBean);
	
	}
}
