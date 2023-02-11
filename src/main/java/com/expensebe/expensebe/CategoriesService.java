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
        Categories category = categoryList.stream().filter(item -> Objects.equals(item.getUserName(), userName)).findFirst().orElse(null);

        if(category == null){
            Categories cat = new Categories();
            cat.setCategories(List.of("Housing", "Transportation", "Food", "Utilities", "Healthcare", "Insurance", "Custom", "Miscellaneous"));
            cat.setUserName(userName);
            return this.saveCategories(cat);
        }
        return category;
    }

    public Categories saveCategories(Categories categories) {
        return this.categoriesRepository.save(categories);
    }

    public List<Integer> getExpensesByCategory(String userName, String month, String year){
        if(!Objects.equals(month, "13")){
            int sumByCategory;
            List<Integer> expenseByCategoryList = new ArrayList<>();
            List<Expenses> expenseList = this.expenseService.getAllExpenses(userName, month, year);
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
            System.out.println("Expense" +expenseByCategoryList);
            return expenseByCategoryList;
        }
        else{
            int sumByCategory;
            List<Integer> expenseByCategoryList = new ArrayList<>();
            List<Expenses> expenseList = this.expenseService.getAllExpenses(userName, month, year).stream().filter(expenses ->
                    Objects.equals(expenses.getDate().split("-")[0], year.toString())).collect(Collectors.toList());
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
}
