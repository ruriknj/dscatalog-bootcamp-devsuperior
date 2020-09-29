package com.dev.superior.dscatalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.superior.dscatalog.entities.Category;
import com.dev.superior.dscatalog.repositories.CategoryRepository;

@Service  // registrar a class como um componente de injeção de independencia (gerenciar as injeções de dependências)
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;

	public List<Category> findAll() {
		
		return repository.findAll();
		
	}
	
}
