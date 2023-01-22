package com.expensebe.expensebe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CategoriesService {

    @Autowired
    final CategoriesRepository categoriesRepository;
    final ExpenseService expenseService;

    public CategoriesService(CategoriesRepository categoriesRepository, ExpenseService expenseService) {
        this.categoriesRepository = categoriesRepository;
        this.expenseService = expenseService;
    }

    public Categories getCategoryList(String userName) {
        List<Categories> categoryList = this.categoriesRepository.findAll();
        Categories category = categoryList.stream().filter(item -> Objects.equals(item.getUserName(), userName)).findFirst().get();
        return category;
    }

    public Categories saveCategories(Categories categories) {
        return this.categoriesRepository.save(categories);
    }

    public List<Integer> getExpensesByCategory(String userName, Integer month, Integer year){
        int sumByCategory;
        List<Integer> expenseByCategoryList = new ArrayList<>();
        List<Expenses> expenseList = this.expenseService.getAllExpenses().stream().filter(expenses -> Objects.equals(expenses.getDate().split("-")[0], year.toString()) && Objects.equals(expenses.getDate().split("-")[1], month.toString())).collect(Collectors.toList());
        Categories categories = getCategoryList(userName);
        for(String categoryItem : categories.getCategories()){
            sumByCategory = 0;
            for (Expenses expenses : expenseList){
                 if (Objects.equals(expenses.getCategory(), categoryItem)){
                     sumByCategory += expenses.getAmount();
                 }
            }
            expenseByCategoryList.add(categories.getCategories().indexOf(categoryItem), sumByCategory);
        }
        return expenseByCategoryList;
    }
}
