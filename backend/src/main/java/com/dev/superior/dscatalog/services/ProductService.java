package com.dev.superior.dscatalog.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.superior.dscatalog.dto.CategoryDTO;
import com.dev.superior.dscatalog.dto.ProductDTO;
import com.dev.superior.dscatalog.entities.Category;
import com.dev.superior.dscatalog.entities.Product;
import com.dev.superior.dscatalog.repositories.CategoryRepository;
import com.dev.superior.dscatalog.repositories.ProductRepository;
import com.dev.superior.dscatalog.services.exceptions.DatabaseException;
import com.dev.superior.dscatalog.services.exceptions.ResourceNotFoundException;

@Service // registrar a class como um componente de injeção de independencia (gerenciar
			// as injeções de dependências)
public class ProductService {

	@Autowired
	private ProductRepository repository;
	@Autowired
	private CategoryRepository categoryRepository;

	@Transactional(readOnly = true) // usa-se para o metodo de somente leitura ->( readOnly = true)
	public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {
		Page<Product> list = repository.findAll(pageRequest);
		return list.map(X -> new ProductDTO(X));

	}

	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Optional<Product> obj = repository.findById(id); // optional _> optional -> evita trabalhar com valor nulo
		Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new ProductDTO(entity, entity.getCategories());
	}

	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ProductDTO(entity);

	}

	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {

		try {
			Product entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new ProductDTO(entity);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);

		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integraty viaolation");
		}
	}

	private void copyDtoToEntity(ProductDTO dto, Product entity) {

		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setDate(dto.getDate());
		entity.setImgUrl(dto.getImgUrl());
		entity.setPrice(dto.getPrice());

		entity.getCategories().clear();

		for (CategoryDTO catDto : dto.getCategories()) {
			Category category = categoryRepository.getOne(catDto.getId());
			
			entity.getCategories().add(category);

		}
	}
}
