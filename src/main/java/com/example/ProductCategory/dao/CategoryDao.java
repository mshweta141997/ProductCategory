package com.example.ProductCategory.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nimpainfotech.NimpaInfotech.entity.Category;
import in.nimpainfotech.NimpaInfotech.entity.Product;

public interface CategoryDao extends JpaRepository<Category, Integer>{

	

}
