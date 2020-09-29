package com.dev.superior.dscatalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.superior.dscatalog.entities.Category;
import com.dev.superior.dscatalog.repositories.CategoryRepository;

@Service  // registrar a class como um componente de injeção de independencia (gerenciar as injeções de dependências)
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	@Transactional(readOnly = true) // usa-se para o metodo de somente leitura ->( readOnly = true)
	public List<Category> findAll() {
		
		return repository.findAll();
		
	}
	
}
