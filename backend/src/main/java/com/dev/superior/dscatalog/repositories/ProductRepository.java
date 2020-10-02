package com.dev.superior.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.dev.superior.dscatalog.entities.Product;

// CAMADA DE ACESSO A DADOS
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	

}
