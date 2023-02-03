package com.expensebe.expensebe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseCategoryService {

    @Autowired
    final CategoriesService categoriesService;
    final ExpenseService expenseService;

    public ExpenseCategoryService(CategoriesService categoriesService, ExpenseService expenseService) {
        this.categoriesService = categoriesService;
        this.expenseService = expenseService;
    }

    public ExpenseCategory getExpensesByCategory(String userName, String month, String year){
        ExpenseCategory expenseCategory = new ExpenseCategory();
        expenseCategory.categoryList = this.categoriesService.getCategoryList(userName).categories;
        expenseCategory.expenseByCategory = this.categoriesService.getExpensesByCategory(userName, month, year);
        return expenseCategory;
    }
}
