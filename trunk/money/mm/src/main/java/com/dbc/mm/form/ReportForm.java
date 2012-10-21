package com.dbc.mm.form;

import java.util.List;

import com.dbc.mm.model.Category;
import com.dbc.mm.vo.ReportCategory;


public class ReportForm {
	
	private List<ReportCategory> categories;
	private List<Category> allCategories;
	public List<ReportCategory> getCategories() {
		return categories;
	}

	public List<Category> getAllCategories() {
		return allCategories;
	}

	public void setAllCategories(List<Category> allCategories) {
		this.allCategories = allCategories;
	}

	public void setCategories(List<ReportCategory> categories) {
		this.categories = categories;
	}

}
