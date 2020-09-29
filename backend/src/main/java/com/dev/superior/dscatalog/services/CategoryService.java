package com.dev.superior.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.superior.dscatalog.dto.CategoryDTO;
import com.dev.superior.dscatalog.entities.Category;
import com.dev.superior.dscatalog.repositories.CategoryRepository;
import com.dev.superior.dscatalog.services.exceptions.EntityNotFoundException;

@Service  // registrar a class como um componente de injeção de independencia (gerenciar as injeções de dependências)
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	@Transactional(readOnly = true) // usa-se para o metodo de somente leitura ->( readOnly = true)
	public List<CategoryDTO> findAll() {
		
		List<Category> list = repository.findAll();
		
		return list.stream().map(X -> new CategoryDTO(X)).collect(Collectors.toList()); // função lambda
		
	}

	@Transactional(readOnly = true) 
	public CategoryDTO findById(Long id) {
		Optional<Category> obj = repository.findById(id); // optional _>  optional -> evita trabalhar com valor nulo
		Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
		return new CategoryDTO(entity);
	}
	
}
