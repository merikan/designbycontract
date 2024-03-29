package com.dbc.mm.service.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbc.mm.model.Category;
import com.dbc.mm.repository.CategoryRepository;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll(sortByLastNameAsc());
	}
	
	@Override
	public Category findById(Long id) {
		return categoryRepository.findOne(id);
	}
	
	private Sort sortByLastNameAsc() {
        return new Sort(Sort.Direction.ASC, "id");
    }

}
