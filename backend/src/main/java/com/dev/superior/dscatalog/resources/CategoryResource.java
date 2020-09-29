package com.dev.superior.dscatalog.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.superior.dscatalog.entities.Category;
import com.dev.superior.dscatalog.services.CategoryService;

// IMPLEMENTA O CONTROLADOR REST -> Resources

@RestController // transforma a classe no resoucr REST
@RequestMapping(value = "/categories") //
public class CategoryResource {
	
	@Autowired
	private CategoryService service;

	@GetMapping
	public ResponseEntity<List<Category>> findAll() {
		
		List<Category> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
		
		
	}
}


