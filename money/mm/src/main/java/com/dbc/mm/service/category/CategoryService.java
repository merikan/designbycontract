package com.dbc.mm.service.category;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.dbc.mm.model.Category;

@Transactional
public interface CategoryService {

	public List<Category> findAll();

	Category findById(Long id);
	

}
