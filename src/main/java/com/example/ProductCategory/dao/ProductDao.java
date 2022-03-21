package com.example.ProductCategory.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import in.nimpainfotech.NimpaInfotech.entity.Product;

public interface ProductDao extends JpaRepository<Product, Integer> {


}