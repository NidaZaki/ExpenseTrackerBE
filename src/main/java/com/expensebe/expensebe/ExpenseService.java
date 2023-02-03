package com.expensebe.expensebe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    @Autowired
    final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expenses> getAllExpenses(String userName, String month, String year) {
        List<Expenses> result;
        Predicate<Expenses> expensesMonthPredicate = (Objects::nonNull);
        Predicate<Expenses> yearPredicate = (Objects::nonNull);
        Predicate<Expenses> userNamePredicate;
        if(month != null){
            expensesMonthPredicate = (expenses -> expenses.getDate().split("-")[1].equals(month));
        }
        if(year != null){
            yearPredicate = (expenses -> expenses.getDate().split("-")[0].equals(year));
        }

        if(Objects.equals(userName, "Generic")){
            result = this.expenseRepository.findAll().stream().filter(expensesMonthPredicate.and(yearPredicate)).collect(Collectors.toList());
        }
        else{
            userNamePredicate = expenses -> Objects.equals(expenses.getUserName(), userName);
            result = this.expenseRepository.findAll().stream().filter(expensesMonthPredicate.and(yearPredicate).and(userNamePredicate)).collect(Collectors.toList());
        }
        return result;
    }

    public ExpenseData getExpenseData(String month, String year, String category, String userName) {
        Predicate<Expenses> expensesMonthPredicate = (Objects::nonNull);
        Predicate<Expenses> yearPredicate = (Objects::nonNull);
        Predicate<Expenses> categoryPredicate = (Objects::nonNull);
        Predicate<Expenses> userNamePredicate;
        ExpenseData result = null;
        if (month != null) {
            expensesMonthPredicate = (expenses -> expenses.getDate().split("-")[1].equals(month));
        }
        if (year != null) {
            yearPredicate = expenses -> expenses.getDate().split("-")[0].equals(year);
        }
        if (category != null) {
            categoryPredicate = expenses -> expenses.getCategory().equals(category);
        }
        if(userName != null){
            userNamePredicate = expenses -> Objects.equals(expenses.getUserName(), userName);
            result =  ExpenseData.builder()
                    .expensesListChart(this.expenseRepository.findAll().stream().filter(yearPredicate.and(categoryPredicate).and(userNamePredicate)).collect(Collectors.toList()))
                    .expensesList(this.expenseRepository.findAll().stream().filter(expensesMonthPredicate.and(yearPredicate).and(categoryPredicate).and(userNamePredicate)).collect(Collectors.toList()))
                    .build();
        }

        if(userName == null){
            return ExpenseData.builder()
                    .expensesListChart(this.expenseRepository.findAll().stream().filter(yearPredicate.and(categoryPredicate)).collect(Collectors.toList()))
                    .expensesList(this.expenseRepository.findAll().stream().filter(expensesMonthPredicate.and(yearPredicate).and(categoryPredicate)).collect(Collectors.toList()))
                    .build();
        }
        else{
            return result;
        }
    }

    public Expenses saveExpense(Expenses expenses) {
        return this.expenseRepository.save(expenses);
    }

    public void deleteExpense(String id) {
        this.expenseRepository.deleteById(id);
    }
}
